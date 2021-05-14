// E/16/388
// CO322 Data Structures and Algorithms  - Graph ADT lab 5



#include<stdio.h> 
  
int nodes; // to store no of nodes

// graph in adjacancy matrix form assume no self loops as given in the lab sheet

void transitiveClosure(int transtitive[nodes][nodes]); 	// function to get transtitive closure
void printMatrix(int matrix[nodes][nodes]);	// function to print a matrix
  
int main() 
{ 	
	
    printf("Enter the number of nodes of the digraph: "); // asking for number of nodes of the digraph from user
    scanf("%d",&nodes);

    int graph[nodes][nodes];	// matrix to store adjacency matrix

    printf("Enter the adjacency matrix of the digraph :\n");

    for (int row = 0; row < nodes; row++)	// get elements in the matrix by a nested loop
    {
        for (int col = 0; col < nodes; col++)
        {
            printf("Enter data in [%d][%d]: ", row, col); // store the element in correct place
            scanf("%d", &graph[row][col]);
        }
        printf("\n");
    }

    printf("Your input is: \n\n");

    printMatrix(graph);	// display input
    printf("\n");

    /* adjacency matrix for given problem:
      0 1 1 0 0 0 0
      0 0 0 0 1 0 0
      0 0 0 0 0 1 0
      0 1 0 0 1 0 1
      0 0 0 0 0 0 1
      0 0 0 1 0 0 0
      0 1 0 0 0 0 0 
    */

    transitiveClosure(graph); 	// calling function
    return 0; 
} 

// function to print a matrix
void printMatrix(int matrix[nodes][nodes])
{
	for (int i = 0; i < nodes; i++)	// go through all elements and print using a nested loop
    {
        for (int j = 0; j < nodes; j++)
        {
            printf("%d ", matrix[i][j]);
        }
        printf("\n");
    }
}



void transitiveClosure(int transtitive[nodes][nodes])  // function to print transtitive closure of a given adjacency matrix of a graph
{ 
                         
    for (int k = 0; k < nodes; k++) 	// for middle node
    { 
        for (int i = 0; i < nodes; i++) // for starting node
        { 
            for (int j = 0; j < nodes; j++) // for destination node
            {                 
                   transtitive[i][j] = transtitive[i][j] || (transtitive[i][k] && transtitive[k][j]);  // if we can directly reach node j from node i or 
            } 																						   // if we can reach node j via node k then there is a path between nodes i and j
        } 
    } 

    printf ("\nTransitive closure of the graph\n\n"); 

    printMatrix(transtitive); // display trastitive closure
    printf("\n"); 

} 
