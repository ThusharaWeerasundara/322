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




// Returns new trie node (initialized to NULLs) 
TrieNode* createNode(void) 
{ 
    TrieNode *newTrieNode = NULL; 
    newTrieNode -> linkedString = NULL;
    newTrieNode -> lastLetter = false;

    newTrieNode = ( TrieNode *)malloc(sizeof(TrieNode));    //allocate memory for a new node
    nodeCount++;    //increase node count
    if (newTrieNode) 
    {  
        //inirialization of a new node
        newTrieNode->lastLetter = false; 

        for (int i = 0; i < 26; i++) 
            newTrieNode->children[i] = NULL; 
    } 

    return newTrieNode;     //return the new node
} 



linkedString* addChar(char* str,int inital,int end)     //function to append a char to a linkedString
{
    
    linkedString* current = createWord(str[inital]);
    linkedString* newString = NULL;
    linkedString* string = current;

    for(int i = inital + 1;i < end; i++)
    {
        newString = createWord(str[i]);     //make a new nodes for each character in input
        current->nextletter = newString;    //store it in current linkedstring
        current = current->nextletter;      //point to next position
    }
    
    current = NULL;
    return string;
}




TrieNode* linkedStringBreak(TrieNode* previousNode,TrieNode* node,linkedString* breakPoint)
{
    TrieNode* newNode = createNode();
    linkedString* newString = breakPoint->nextletter;
    breakPoint->nextletter = NULL;
    int index1 = getIndex(newString->letter);

    newNode->linkedString = node->linkedString;
    node->linkedString = newString;
    newNode->children[index1] = node;

    int index2 = getIndex(newNode->linkedString->letter);
    previousNode->children[index2] = newNode;

    return newNode;
}

void insertNode(TrieNode* root,char* word)
{
    int lastLetterIndex = strlen(word)-1;
    int i=0,charIndex;

    TrieNode* currentNode = root, *previousNode = NULL;
    TrieNode* newNode = NULL;
    linkedString* currentLetter, *previousLetter = NULL;
    currentLetter = currentNode->linkedString;
    while(i<lastLetterIndex)
    {
    	charIndex = getIndex(word[i]);
    	if(currentLetter == NULL)
        {
    		if(currentNode->children[charIndex] == NULL)
    		{
    			newNode = createNode();
    			newNode->linkedString = addChar(word,i,lastLetterIndex);
                newNode->lastLetter = true;
    			currentNode->children[charIndex] = newNode;
    			nodeCount = nodeCount+1;
    			break;
    		}
    		else
            {
                previousNode = currentNode;
                currentNode = currentNode->children[charIndex];
                previousLetter = currentNode->linkedString;
    			currentLetter = currentNode->linkedString->nextletter;
    		}
    	}
        else
        {
    		if (currentLetter->letter != word[i])
    		{
    			currentNode = linkedStringBreak(previousNode,currentNode,previousLetter);
    			newNode = createNode();
    			newNode->linkedString = addChar(word,i,lastLetterIndex);
                newNode->lastLetter = true;
    			currentNode->children[charIndex] = newNode;
    			break;
    		}
            else
            {
                previousLetter = currentLetter;
    			currentLetter = currentLetter->nextletter;
    		}
    	}
    	i++;
    }
}

TrieNode* fileReader(char* fileName)
{
    char word[60];
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
        fgets(word,60,fp);
        insertNode(root,word);
        if(feof(fp))
            break;
    }
    clock_t end = clock();
    double time_spent = (double)(end-begin)/CLOCKS_PER_SEC;
    printf("%.8lf s\n",time_spent);
    fclose(fp);
    return root;
}

TrieNode* searchNodes(TrieNode* root,char* word)
{
    int lastIndex = strlen(word);
    int i=0,charIndex;
    TrieNode* currentNode = root;
    linkedString* currentLetter = currentNode->linkedString;

    while(i<lastIndex)
    {
    	charIndex = getIndex(word[i]);

    	if(currentLetter == NULL)
        {
            currentNode = currentNode->children[charIndex];
    		currentLetter = currentNode->linkedString->nextletter;
    	}
        else
        {
    		currentLetter = currentLetter->nextletter;
    	}
        i++;
    }

    while(currentLetter != NULL)
    {
    	word[lastIndex] = currentLetter->letter;
    	currentLetter = currentLetter->nextletter;
    	lastIndex++;
    }
    word[lastIndex] = '\0';
    return currentNode;
}

int stringToChar(linkedString* begin,char* str,int initial)
{
    int newSize = initial;
    linkedString* currentLetter = begin;

    while (currentLetter != NULL)
    {
        str[newSize] = currentLetter->letter;
        newSize++;
        currentLetter = currentLetter->nextletter;
    }
    str[newSize] = '\0';
    return newSize-1;
}


void printSuggetions(TrieNode* suggestedNode,char wordBuffer[],int bufferSize)
{
    TrieNode* currentNode = suggestedNode;
    int i,j,newSize;

    if(currentNode->lastLetter)
    {
        for (j=0;j<bufferSize;j++)
        {
            printf("%c",wordBuffer[j]);
        }
        printf("\n");
    }

    for (i=0;i<26;i++)
    {
        if(currentNode->children[i]!=NULL)
        {
            newSize = stringToChar(currentNode->children[i]->linkedString,wordBuffer,bufferSize);
            printSuggetions(currentNode->children[i],wordBuffer,newSize+1);
        }
    }
}

int main()
{
    char text[100];
    TrieNode* root = fileReader("wordlist10000.txt");
    while(1)
    {
        printf("Enter keyword: ");
        scanf("%s",&text);
        char str[60];
        strcpy(str,text);
        clock_t begin = clock();
        TrieNode* suggestedNode = searchNodes(root,str);
        printf("Suggested Words\n");
        printf("-----------------------\n");
        printSuggetions(suggestedNode,str,strlen(str));
        clock_t end = clock();
        double elapsedtime = (double)(end-begin)/CLOCKS_PER_SEC;
        printf("Elapsed time: %f s\n",elapsedtime);
        printf("Memory: %d Bytes",sizeof(*(root))*nodeCount);
        printf("\n");
    }
    return 0;
}
