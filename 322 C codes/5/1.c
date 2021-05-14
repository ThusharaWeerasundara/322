// E/16/388
// CO322 Data Structures and Algorithms  - Graph ADT lab 5



#include<stdio.h> 
  
// define no of nodes in the graph
#define nodes 7

// graph in adjacancy matrix form

int graph[nodes][nodes] = { {0, 1, 1, 0, 0, 0, 0}, 
                       	    {0, 0, 0, 0, 1, 0, 0}, 
                       	    {0, 0, 0, 0, 0, 1, 0}, 
                            {0, 1, 0, 0, 1, 0, 1},
                            {0, 0, 0, 0, 0, 0, 1},
                            {0, 0, 0, 1, 0, 0, 0},
                            {0, 1, 0, 0, 0, 0, 0} };
  
/*
#define nodes 4
int graph[nodes][nodes] = { {0, 1, 1, 0}, 
                       	    {0, 0, 0, 0}, 
                       	    {0, 0, 0, 1}, 
                            {0, 1, 0, 0} 
                        };
 */

void transitiveClosure(int transtitive[nodes][nodes]); 	// function to get transtitive closure

  
int main() 
{ 
    transitiveClosure(graph); 	// calling function
    return 0; 
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

    for (int i = 0; i < nodes; i++) // print results using a loop
    { 
        for (int j = 0; j < nodes; j++)
        {
        	 printf ("%d ", transtitive[i][j]); 
        } 
            printf("\n"); 
    } 
    printf("\n"); 

} 
