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
    int length = strlen(key) - 1;   //get length of key
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



TrieNode* readFile()    //function to read a file and store results in a Trie data structure
{   
  FILE *source = fopen("wordlist100001txt","r");                                                                                       //opening the file in to a file pointer
  
  if(source == NULL)    //error in file opening                                                                                                           //if unable to open
    {
      printf("Source file couldn't open.Process terminated.\n");
       exit(1);
    }

    TrieNode* root = createNode();
    char store[1024];
    /* assumes no word exceeds length of 1024 */

    clock_t begin = clock();    //time of start storing
    while(1)
    {
        fgets(store,1024,source);
        insertWord(root,store);
        if(feof(source))
            break;    
    }
    clock_t end = clock();  //finished storing

    double time_spent = (double)(end-begin)/CLOCKS_PER_SEC;
    printf("Time spent on storing : %.8lf s\n",time_spent);     //print elapsed time

    fclose(source);     //close file
    return root;   
}








/*

int main()  //main function
{ 
    
    TrieNode *root1 = readFile();       //create a new tree by creating a root for 1st file

    char word[10000];   //to store user input

    printf("\nMemory usage: %ld Bytes\n\n",sizeof(*(root1))*nodeCount); //memory usage for storing

    while(1)
    {
        printf("Enter a word to search: ");     //get user input
        scanf("%s",word);

        clock_t begin = clock();    //start searching
        int done = printSuggestions(root1,word);    //print suggestions

        if(done == -1)
        {
            printf("The entry is not present!\n");
        }
        clock_t end = clock();
        double elapsedtime = (double)(end-begin)/CLOCKS_PER_SEC;
        printf("\nElapsed time on search: %f s\n\n",elapsedtime);
           
    }
    
    
    return 0; 
} 

*/





TrieNode* fileReader(char* fileName)
{
    char word[100];
    FILE *fp = fopen(fileName,"r");
    if (fp == NULL)
    {
        printf("Unable to open the file");
        exit(1);
    }
    TrieNode* root = createNode();
    clock_t begin = clock();
    while(1)
    {
        fgets(word,100,fp);
        insertWord(root,word);
        if(feof(fp))
            break;
    }
    clock_t end = clock();
    double time_spent = (double)(end-begin)/CLOCKS_PER_SEC;
    printf("%.8lf s\n",time_spent);
    fclose(fp);
    return root;
}




int main()
{
    char text[100];
    TrieNode* root = readFile();
    while(1)
    {
        printf("Enter keyword: ");
        scanf("%s",&text);
        char str[60];
        strcpy(str,text);
        clock_t begin = clock();
        printSuggestions(root,str);
        printf("Suggested Words\n");
        printf("-----------------------\n");
        //printSuggetions(root,str,strlen(str));
        clock_t end = clock();
        double elapsedtime = (double)(end-begin)/CLOCKS_PER_SEC;
        printf("Elapsed time: %f s\n",elapsedtime);
        printf("Memory: %d Bytes",sizeof(*(root))*nodeCount);
        printf("\n");
    }
    return 0;
}
