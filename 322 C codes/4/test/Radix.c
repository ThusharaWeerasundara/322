/*
E/16/388
CO-322
Lab 4 part 2
This radix structure only stores strings with all alphabetic characters
*/

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>
#include <ctype.h> 

int nodeCount = 0;  //to count number of nodes

int getIndex(char x)    //take the child index of a given character 
{
    return (int)x - (int)'a';
} 



typedef struct linkedString //like a linked list, stores char by char in nodes. Hence set of nodes represents a word
{
    char letter;
    struct linkedString *nextletter; //points to next leter 
}linkedString;


typedef struct trienode  
{ 
    struct trienode *children[26];  //child nodes for each letter
    linkedString* linkedString;     //to store set of letters
    bool lastLetter;    //to mark an end of a word
}TrieNode; 


linkedString* createWord(char newLetter)    //to create a brand new string
{
    linkedString* word = (linkedString*) malloc(sizeof(linkedString));  //allocate space for new word
    word -> nextletter = NULL;        //has no following elements 
    word -> letter = newLetter;     //store input character in word
    return word;    
}




TrieNode* createNode()      //to create a new node in tree
{   
    TrieNode* newNode = (TrieNode*)malloc(sizeof(TrieNode));
    
    if(newNode) 
    {  
        //inirialization of a new node
        newNode -> linkedString = NULL;
        newNode -> lastLetter = false;

        newNode->lastLetter = false; 

        for (int i = 0; i < 26; i++) 
            newNode->children[i] = NULL; 
    } 

    return newNode;     //return the new node
}





linkedString* append(int inital,int end, char* str)      //appends a character to a linkedString
{
   
    linkedString* current = createWord(str[inital]);     //initial letter in current linkedString
    linkedString* newString = NULL;                      //map next linkedString to null
    linkedString* string = current;                      

    for(int i = inital + 1;i < end;i++)                 //store each letter in new nodes of linkedString
    {
        newString = createWord(str[i]);
        current->nextletter = newString;
        current = current->nextletter;
    }
    current = NULL;         //next letter is null because no letters afterwards

    return string;
}


TrieNode* rearrange(TrieNode* previousNode, TrieNode* node, linkedString* splitLetter)     //rearrange a set of nodes after insertion
{
    TrieNode* newNode = createNode();       //new node to use for connect

    linkedString* newString = splitLetter->nextletter;           //get breaking point letter
    splitLetter->nextletter = NULL;                              //split by assign next letter to null

    int index = getIndex(newString->letter);                    //get index of breaking letter

    newNode->linkedString = node->linkedString;                 //store current nodes letter in new node
    node->linkedString = newString;                             //store split letter in node
    newNode->children[index] = node;                            //connect by store node as a child of new node for connect from 1 side

    index = getIndex(newNode->linkedString->letter);            //get index of letter and put in previous node's correct child position to complete connection from both sides
    previousNode->children[index] = newNode;

    return newNode;
}



void insertWord(TrieNode* root, char* key)
{
    int level;                  //maximum height to store
    int length = strlen(key);   //get length of key
    int index; 

    TrieNode* currentNode = root;           //nodes for store an entry. nodes may be rearranged therefore need multiple nodes
    TrieNode* previousNode = NULL;
    TrieNode* newNode = NULL;
    
    linkedString* currentLetter;                //to store characters
    linkedString* previousLetter = NULL;
    currentLetter = currentNode->linkedString;  //map to linkedString in current node
   
    for(level = 0; level < length; level++)     //go till last letter in word
    {
        index = getIndex(key[level]);      //get index for each letter in word

        if(currentLetter == NULL)   //leter is absent
        {
            if(currentNode->children[index] != NULL)  //next child is present, then map to the next child node, map to next linkedString   
            {     
                previousNode = currentNode;
                currentNode = currentNode->children[index];
                previousLetter = currentNode->linkedString;
                currentLetter = currentNode->linkedString->nextletter;
            
            }
            else //if current letter is absent make a new node and do appending and storing
            {
                newNode = createNode();
                newNode->linkedString = append(level , length, key);
                newNode->lastLetter = true;     //mark as end of a word
                currentNode->children[index] = newNode;
                nodeCount++; //new node is made
                break;
            }
        }
        else  //if letter is present
        {
            if (currentLetter->letter == key[level]) //if letters are equal, previousLetter = currentLetter  
            {   
                previousLetter = currentLetter;
                currentLetter = currentLetter->nextletter;

            }
            else    //if input letter and letter in level is different we may have to rearrange         
            {
                currentNode = rearrange(previousNode,currentNode,previousLetter);    //rearrange nodes
                newNode = createNode();  //create a new node and store appended linkedString
                newNode->linkedString = append(level , length, key);
                newNode->lastLetter = true;    //mark as end of a word          
                currentNode->children[index] = newNode; //map to next child node
                break;
            }
        }
        
    }
}





int linkedStringsize(linkedString* firstLetter, char* str, int initial)   //get the size of a linkedString
{
    linkedString* currentLetter = firstLetter;  //to move through nodes store 1st letter

    while (currentLetter != NULL)               //go till the end by calculating size.
    {
        str[initial] = currentLetter->letter; 
        initial++;                              
        currentLetter = currentLetter->nextletter;
    }
    
    return initial;   //return size of the linkedString 
}



void searchSubTrees(TrieNode* root, char* lookfor, int bufferSize)      //search subtrees for suggestions
{
    TrieNode* currentNode = root;       //to store roots

    if(currentNode->lastLetter)         //if suggestion letter exists, print it 
    {   
        lookfor[bufferSize] = '\0';     //neglect garbage values if there are any
        printf("%s\n",lookfor);
    }

    for (int i = 0;i < 26;i++)          //go through all child nodes for suggestions
    {
        if(currentNode->children[i] != NULL)  //if child node does not exist, check in different subtree
        {
            int newSize = linkedStringsize(currentNode->children[i]->linkedString,lookfor,bufferSize);
            searchSubTrees(currentNode->children[i],lookfor,newSize);
        }
    }
}


int printSuggestions(TrieNode* root,char* word)     //function to print suggestions
{
    int length = strlen(word);
    int level = 0;
    int index;

    TrieNode* currentNode = root;
    linkedString* currentLetter = currentNode->linkedString;

    for (level = 0; level < length; level++)     //1st check whether whole word is present or not
    {
    	index = getIndex(word[level]);

    	if(currentLetter == NULL)    //if current letter is not present, check child node  
        {   
            currentNode = currentNode->children[index];
            
            if(currentNode == NULL)         //if current node is not present. word is not present. return -1
            {
                return -1;
            }
    		currentLetter = currentNode->linkedString->nextletter;    //else point to next letter
            
    	}
        else
        {   
    		currentLetter = currentLetter->nextletter;    //current letter is present, point to next letter           
    	}
     
    }

    while(currentLetter != NULL)        //go till find an uninitialized letter
    {
    	word[length] = currentLetter->letter;          //store letter in word
    	currentLetter = currentLetter->nextletter;     //point to next
    	length++;
    }

    word[length] = '\0';        //remove last letter to check for other possibilities for previous prefix
   
    searchSubTrees(currentNode ,word, strlen(word));    //check for sub trees for new prefix
    return 1;
}





int isWord(char word[])      //to check whether a word has invalid characters
{   

   
    for (int i = 0; i < strlen(word) ; ++i)
        {   
             
            
            if(!isalpha(word[i]))
            {   
                return 0;
            }
        }
    return 1;
}



TrieNode* readFile(char* file)    //function to read a file and store results in a Trie data structure
{   
  FILE *source = fopen(file,"r");   //opening the file in to a file pointer
  
  if(source == NULL)    //error in file opening                                                                                                           //if unable to open
    {
      printf("Source file couldn't open.Process terminated.\n");
       exit(1);
    }

    TrieNode* root = createNode();
    char store[1024];
    /* assumes no word exceeds length of 1024 */

    clock_t begin = clock();    //time of start storing
    while (fscanf(source, "%1024s", store) == 1) 
    {    
      if(isWord(store))
        {   
            int j = 0; 
            // index of haracter to be converted to lowercase 
          
             // convert ch to lowercase using toLower() 
            while (store[j]) 
            { 
            store[j] = tolower(store[j]);  
        // convert characters to lowercase using toLower()      
                 j++; 
            } 
            insertWord(root,store);   //insert only valid entries
        }        
    }
    clock_t end = clock();  //finished storing

    double time_spent = (double)(end-begin)/CLOCKS_PER_SEC;
    printf("Time spent on storing in Radix : %.8lf s\n",time_spent);     //print elapsed time

    fclose(source);     //close file
    return root;   
}




int main()
{ 
    
    int select = 0;
    
    printf("Select file(enter 1 or 2 or 3):\n(1). wordlist1000\n(2). wordlist10000\n(3). wordlist70000\nEnter: ");
    scanf("%d", &select);

    if(select < 1 || select > 3)
    {
        printf("enter 1 or 2 or 3\n");
        return -1;
    }

     TrieNode *root;
    if(select == 1)
    {
         root = readFile("wordlist1000.txt");         //create a new tree by creating a root for 1st file
    }

    if(select == 2)
    {
         root = readFile("wordlist10000.txt");         //create a new tree by creating a root for 2nd file
    }

    if(select == 3)
    {
         root = readFile("wordlist70000.txt");         //create a new tree by creating a root for 3rd file
    }

    char word[10000];   //to store user input

    printf("\nMemory usage: %ld Bytes",sizeof(*(root))*nodeCount); //memory usage for storing
    printf("\nNumber of Nodes: %d \n\n",nodeCount);  //number of nodes
    
    while(1)
    {   
        
        printf("Enter a word to search: ");     //get user input
        scanf("%s",word);


        int j = 0; 
            // index of haracter to be converted to lowercase 
           
        // convert ch to lowercase using toLower() 
            while (word[j]) 
            { 
            word[j] = tolower(word[j]);  
        // convert input string to lowercase before searching      
                 j++; 
            } 

        clock_t begin = clock();    //start searching
        int done = printSuggestions(root,word);    //print suggestions
        clock_t end = clock();

        if(done == -1)
        {
            printf("The entry is not present!\n");
        }
        
        double elapsedtime = (double)(end-begin)/CLOCKS_PER_SEC;
        printf("\nElapsed time on search: %f s\n\n",elapsedtime);   //display search time
           
    }
    
    
    return 0; 
} 
