
// C program for implementation of Bubble sort 
#include <stdio.h> 



void printArray(int arr[], int size) 
{ 
    int i; 
    for (i=0; i < size; i++) 
        printf("%d ", arr[i]); 
    printf("\n"); 
}  
void swap(int *xp, int *yp) 
{ 
    int temp = *xp; 
    *xp = *yp; 
    *yp = temp; 
} 
  
// A function to implement bubble sort 
void bubbleSort(int arr[], int n) 
{ 
   int i, j; 
   for (i = 0; i <= n-2; i++)       
  	{
       // Last i elements are already in place    
       for (j = n - 1; j >= i+1; j--)  
           if (arr[j] < arr[j-1]) 
              swap(&arr[j], &arr[j-1]);

        printArray(arr, n);

     } 
} 
  
/* Function to print an array */

  
// Driver program to test above functions 
int main() 
{ 
    int arr[] = {64, 34, 25, 22, 22}; 
    int n = sizeof(arr)/sizeof(arr[0]); 
    bubbleSort(arr, n); 
    printf("Sorted array: \n"); 
    printArray(arr, n); 
    return 0; 
} 
