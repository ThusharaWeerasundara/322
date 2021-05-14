/* C implementation QuickSort */
#include<stdio.h> 

// A utility function to swap two elements 
void swap(int* a, int* b) 
{ 
	int t = *a; 
	*a = *b; 
	*b = t; 
} 

/* Function to print an array */
void printArray(int arr[], int size) 
{ 
	int i; 
	for (i=0; i < size; i++) 
		printf("%d ", arr[i]); 
	printf("\n"); 
} 

/* This function takes last element as pivot, places 
the pivot element at its correct position in sorted 
	array, and places all smaller (smaller than pivot) 
to left of pivot and all greater elements to right 
of pivot */
int partition (int arr[], int low, int high, int pivot) 
{ 
	

	int l = low;
	int r = high;
	int n = 2; 


	printf("l: %d r: %d pivot: %d \n", l+1, r+1, pivot);
	do
	{	
		printArray(arr, n );

		printf("swap arr[%d]: %d  arr[%d]: %d \n", l+1,arr[l], r+1,arr[r]);
		swap(&arr[l], &arr[r]); 

		printArray(arr, n );
		printf("\n");
		while(arr[l] < pivot)
		{
			l = l + 1;
		}

		while(arr[r] >= pivot)
		{
			r = r - 1;
		}

		printf("l: %d r: %d \n", l+1, r+1);
	}
	while(l <= r);

	printf("k: %d\n", l);
	return l;


} 

/* The main function that implements QuickSort 
arr[] --> Array to be sorted, 
low --> Starting index, 
high --> Ending index */
void quickSort(int arr[], int low, int high) 
{ 
	int pivot = arr[0]; // pivot 
	
	if(arr[0] < arr[1])
	{
		pivot = arr[1];
	}
		/* pi is partitioning index, arr[p] is now 
		at right place */
		int k = partition(arr, low, high, pivot); 

		// Separately sort elements before 
		// partition and after partition 
		quickSort(arr, low, k - 1); 
		quickSort(arr, k , high); 
	 
} 



// Driver program to test above functions 
int main() 
{ 
	//int arr[] = {3, 1, 4, 1, 5, 9, 2, 6, 5 ,3}; 

	//int arr[] = {2, 1, 1}; 
	//int arr[] = {4, 5, 9, 3, 6, 5, 3};

	//int arr[] = {3,4,3};
	//int arr[] = {9, 6, 5, 5};

	//int arr[] = {5, 6, 5};
	int arr[] = {3,3};
	//int arr[] = {2};

	int pivot = arr[0]; // pivot 
	
	if(arr[0] < arr[1])
	{
		pivot = arr[1];
	}


	int n = sizeof(arr)/sizeof(arr[0]); 
	printf("size: %d\n", n);
	partition(arr, 0, n-1, pivot); 
	//quickSort(arr, 0, n-1); 


	
	return 0; 
} 
