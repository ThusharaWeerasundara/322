// C implementation of search and insertWord operations 
// on Trie 
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

char alphabet[26] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
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
	nodeCount++;	//increase node count
	if (newNode) 
	{  
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


char* extractWord(char * string)
{
    int length = strlen(string);
    int j = 0; 
    char out[length] ;
    for (int i = 0; i < length; i++)
    {
        char c = string[i];
        if (isalpha(c))
        {
            out[j] = toupper(c);
            j++; 
        }else continue ;
    }
    out[j] = '\0';
    bzero(string,length);   //erase data in string
    string = out ;
    return string;  
}

TrieNode* readFile(char* file)      //function to open the file
{
    
    FILE * source = fopen(file,"r");

    if(source == NULL)  //if failed to open the file  
    {
       printf("unable to open the file\n");
       exit(1);
    }
    
    TrieNode* root = createNode();  //trie to store words 
    
    char buffer[1000];

    while(fscanf(source," %1000s",buffer)==1)   //to store words
    {
        insertWord(root,extractWord(buffer));       //extract the correct format of word
        bzero(buffer,1000); //erase data in buffer
    }

    fclose(source); //close the file and return the node
    return root;

}




int main() 	//main function
{ 
	
	TrieNode *root1 = readFile("wordlist10000s.txt"); 		//create a new tree by creating a root for 1st file

	char word[10000];	//to store user input

	printf("\nMemory usage: %ld Bytes\n\n",sizeof(*(root1))*nodeCount);	//memory usage for storing

	while(1)
	{
		printf("Enter a word to search: ");		//get user input
		scanf("%s",word);

		clock_t begin = clock();	//start searching
		int done = printSuggestions(root1,word); 	//print suggestions

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
