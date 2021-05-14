/*
E/16/388
CO-322
Lab 4 part 1
This trie structure only stores strings with all alphabetic characters
*/

#include <stdio.h> 
#include <stdlib.h> 
#include <string.h> 
#include <stdbool.h> 
#include <ctype.h>  
#include <time.h>

int nodeCount = 0;		//to count number of nodes

// use only lower case letters 


int getIndex(char x)	//take the child index of a given character 
{
	return (int)x - (int)'a';
} 

char alphabet[26] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
//store alphabet

//node of the trie  
typedef struct trienode  
{ 
	struct trienode *children[26]; 	//child nodes for each letter
	bool lastLetter; 	//to mark an end of a word
}TrieNode; 


bool haveChildren(TrieNode* root) 	//function to check whether the node is the last node
{ 
    for (int i = 0; i < 26; i++) 
        if (root->children[i]) 
            return 0; 
    return 1; 
} 


//Recursive function to print print suggestions for given word 
void searchSubTrees(TrieNode* root, char* lookFor) 
{         
    if (root->lastLetter) 	//if found a string in Trie with the given word print it
    { 	
    	printf("%s\n",lookFor); 
    } 
  	
    
    char str[10000] = "";	//to store a word
    strcat(str,lookFor);	//store the search entry
    
    if (haveChildren(root)) 	//if search entry word has no sub trees return
        return; 
     
    for (int i = 0; i < 26; i++) 	//else traverse through sub trees
    { 
        if (root->children[i]) 		//traverse if have children
        { 	              
            char letter = alphabet[i];	//store the character associated with current node
               
   			 strncat(str, &letter, 1); 	  // append current character to lookFor string to get new word
                  
            searchSubTrees(root->children[i], str);  // recursion to test subtrees for matching words
        	
        	str[(int)(strlen(str)-1)] = '\0';		//to check for the next entry, remove the appended character
        	
        } 
    } 
} 


int printSuggestions(TrieNode* root,  char* search) 	//function to print suggesstions stored
{ 
    TrieNode* currentNode = root; 	//root
  
    int level;  //to store the current level
    int n = strlen(search); 		//get length of the search word
    
    for (level = 0; level < n; level++) 	//1st check whether whole word is present or not
    { 
        int index = getIndex(search[level]); 
          
        if (!currentNode->children[index])  //if not present return -1
            return -1; 
  
        currentNode = currentNode->children[index]; 	//else current node is the next child
    } 
  	 
    if ((currentNode->lastLetter == true) && haveChildren(currentNode))  //if word is present and it has no subtrees return -1
    {  
        return -1; 
    } 
  
    if (!haveChildren(currentNode)) 		//if it has subtree search for suggestions
    { 
        searchSubTrees(currentNode, search); 
        return 1; 
    } 

    return 1;
} 



// Returns new trie node (initialized to NULLs) 
TrieNode* createNode(void) 
{ 
	TrieNode *newNode = NULL; 

	newNode = ( TrieNode *)malloc(sizeof(TrieNode)); 	//allocate memory for a new node
		
	if (newNode) 
	{  
        nodeCount++;    //increase node count
		//inirialization of a new node
		newNode->lastLetter = false; 

		for (int i = 0; i < 26; i++) 
			newNode->children[i] = NULL; 
	} 

	return newNode; 	//return the new node
} 

// If not present, inserts key into trie 
// If the key is prefix of trie node, just marks leaf node 
TrieNode* insertWord(TrieNode *root, char *key) 
{ 
	int level; 					//maximum height to store
	int length = strlen(key); 	//get length of key
	int index; 

	 TrieNode *currentNode = root; 

	for (level = 0; level < length; level++) 	//go till end of word
	{ 
		index = getIndex(key[level]); 			//if letter is not here make a new node
		if (!currentNode->children[index]) 
			currentNode->children[index] = createNode(); 

		currentNode = currentNode->children[index]; 	//map current node to next child
	} 

	//mark as end of a word
	currentNode->lastLetter = true; 	

	return root;
} 





int isWord(char* word)      //to check whether a word has invalid characters
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



TrieNode* readFile(char* file) 	//function to read a file and store results in a Trie data structure
{	
  FILE *source = fopen(file,"r");                                                                                       //opening the file in to a file pointer
  
  if( !source )    //error in file opening                                                                                                           //if unable to open
    {
      printf("Source file couldn't open.Process terminated.\n");
      exit(1);
    }

    TrieNode* root = createNode();
    char store[1024];
    /* assumes no word exceeds length of 1024 */

    clock_t begin = clock();	//time of start storing
    
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

    clock_t end = clock();	//finished storing

    double time_spent = (double)(end-begin)/CLOCKS_PER_SEC;
    printf("Time spent on storing in Trie : %.8lf s\n",time_spent);		//print elapsed time

    fclose(source);  	//close file
    return root;   
}










int main() 	//main function
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
	
	char word[10000];	//to store user input

	printf("\nMemory usage: %ld Bytes",sizeof(*(root))*nodeCount);	//memory usage for storing
    printf("\nNumber of Nodes: %d \n\n",nodeCount);  //number of nodes

	while(1)
	{
		printf("Enter a word to search: ");		//get user input
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

		clock_t begin = clock();	//start searching
		int done = printSuggestions(root,word); 	//print suggestions
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
