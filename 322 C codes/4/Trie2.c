// C implementation of search and insertWord operations 
// on Trie 
#include <stdio.h> 
#include <stdlib.h> 
#include <string.h> 
#include <stdbool.h> 
#include <ctype.h>  



// use only lower case letters 


int getIndex(char x)	//take the child index of a given character 
{
	return (int)x - (int)'a';
} 

char alphabet[26] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
//store alphabet

//node of the trie  


#include <stdio.h> 
#include <stdlib.h> 
#include <stdbool.h> 
#include <assert.h>
  
/* Link list node */
struct Node 
{ 
    int key; 
    void* data;
    struct Node* next; 
}; 
  
/* Given a reference (pointer to pointer) to the head 
  of a list and an int, push a new node on the front 
  of the list. */


/* Checks whether the value x is present in linked list */
bool search(struct Node* head, int x) 
{ 
    struct Node* current = head;  // Initialize current 
    while (current != NULL) 
    { 
        if (current->key == x) 
            return true; 
        current = current->next; 
    } 
    return false; 
} 






// Takes head pointer of  
// the linked list and index 
// as arguments and return 
// data at index 
int getValue(struct Node* head, int index) 
{ 
      
    struct Node* current = head; 
      
     // the index of the  
     // node we're currently 
     // looking at 
     
    while (current != NULL) 
    { 
        if (current->key == index) 
            return(current->key); 
 
        current = current->next; 
    } 
  
    /* if we get to this line,  
       the caller was asking 
       for a non-existent element 
       so we assert fail */
    assert(0);              
} 

typedef struct Node node_t; // node_t is now a type and is "struct node"
typedef node_t * list_t;    // list is address to first node 


//node of the trie  
typedef struct trienode  
{ 
    list_t list;  //child nodes for each letter
    
    bool lastLetter;    //to mark an end of a word
}TrieNode; 




void push(struct Node** head_ref, int new_key) 
{ 
    /* allocate node */
    if(!search(*head_ref,  new_key))
    {
        struct Node* new_node = (struct Node*) malloc(sizeof(struct Node)); 
  
        /* put in the key  */
        new_node->key  = new_key; 
      
        /* link the old list off the new node */
        new_node->next = (*head_ref); 
      
        /* move the head to point to the new node */
        (*head_ref)    = new_node; 
    }
    
} 
  





// Returns new trie node (initialized to NULLs) 
TrieNode* createNode(void) 
{ 
	TrieNode* newNode = NULL; 

	newNode = ( TrieNode *)malloc(sizeof(TrieNode)); 

	if (newNode) 
	{ 
		int i; 

		newNode->lastLetter = false; 		
		newNode->list = NULL ; 
	} 

	return newNode; 
} 


// If not present, inserts key into trie 
// If the key is prefix of trie node, just marks leaf node 



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







int main() 	//main function
{ 
	
	//TrieNode *root1 = readFile(); 		//create a new tree by creating a root for 1st file

	char word[10000];	//to store user input

	printf("Enter a word to search: ");		//get user input
	scanf("%s",word);

	//printSuggestions(root1,word); 	//print suggestions
	
	return 0; 
} 
