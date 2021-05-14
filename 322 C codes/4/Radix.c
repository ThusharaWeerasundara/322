#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>

int nodeCount = 0;

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

TrieNode* createNode()  
{   
    if (TrieNode* newNode = (TrieNode*)malloc(sizeof(TrieNode))) 
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
    TrieNode*  *previousNode = NULL;
    TrieNode* newNode = NULL;
    
    linkedString* currentLetter;                //to store characters
    linkedString* previousLetter = NULL;
    currentLetter = currentNode->linkedString;  //map to linkedString in current node
   
    for(level = 0; level < length; level++)     //go till last letter in word
    {
    	index = getIndex(key[level]);      //get index for each letter in word

    	if(currentLetter != NULL)   //current letter is present then map to the next child node, map to next linkedString   
        {
    		if(currentNode->children[index] == NULL)
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




//Recursive function to print print suggestions for given word 
void searchSubTrees(TrieNode* root, char* lookFor) 
{         
    if (root->lastLetter)   //if found a string in Trie with the given word print it
    {   
        printf("%s\n",lookFor); 
    } 
    
    
    char str[10000] = "";   //to store a word
    strcat(str,lookFor);    //store the search entry
    
    if (haveChildren(root))     //if search entry word has no sub trees return
        return; 
     
    for (int i = 0; i < 26; i++)    //else traverse through sub trees
    { 
        if (root->children[i])      //traverse if have children
        {                 
            char letter = alphabet[i];  //store the character associated with current node
               
             strncat(str, &letter, 1);    // append current character to lookFor string to get new word
                  
            searchSubTrees(root->children[i], str);  // recursion to test subtrees for matching words
            
            str[(int)(strlen(str)-1)] = '\0';       //to check for the next entry, remove the appended character
            
        } 
    } 
} 


int printSuggestions(TrieNode* root,  char* search)     //function to print suggesstions stored
{ 
    TrieNode* currentNode = root;   //root
  
    int level;  //to store the current level
    int n = strlen(search);         //get length of the search word
    
    for (level = 0; level < n; level++)     //1st check whether whole word is present or not
    { 
        int index = getIndex(search[level]); 
          
        if (!currentNode->children[index])  //if not present return -1
            return -1; 
  
        currentNode = currentNode->children[index];     //else current node is the next child
    } 
     
    if ((currentNode->lastLetter == true) && haveChildren(currentNode))  //if word is present and it has no subtrees return -1
    {  
        return -1; 
    } 
  
    if (!haveChildren(currentNode))         //if it has subtree search for suggestions
    { 
        searchSubTrees(currentNode, search); 
        return 1; 
    } 

    return 1;
} 


int isWord(char* word)
{
    for (int i = 0; i < strlen(word); ++i)
        {
            if(!isalpha(word[i]))
            {   
                return 0;
            }
        }
    return 1;
}




TrieNode* readFile()    //function to read a file and store results in a Trie data structure
{   
  FILE *source = fopen("wordlist10000.txt","r");                                                                                       //opening the file in to a file pointer
  
  if( !source )    //error in file opening                                                                                                           //if unable to open
    {
      printf("Source file couldn't open.Process terminated.\n");
      return NULL;
    }

    TrieNode* root = createNode();
    char store[1024];
    /* assumes no word exceeds length of 1024 */

    clock_t begin = clock();    //time of start storing
    while (fscanf(source, "%1024s", store) == 1) 
    {   

        if(isWord(store))
        {
            insertWord(root,store); 
        }        
    }
    clock_t end = clock();  //finished storing

    double time_spent = (double)(end-begin)/CLOCKS_PER_SEC;
    printf("Time spent on storing : %.8lf s\n",time_spent);     //print elapsed time

    fclose(source);     //close file
    return root;   
}






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
