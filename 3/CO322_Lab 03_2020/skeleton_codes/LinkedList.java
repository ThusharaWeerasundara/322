
// Java program to implement 
// a Singly Linked List 
class LinkedList { 

	Node head = null; 

	public static LinkedList insertToBucket(LinkedList list, String key) // method to insert new entry
	{ 

		Node currNode = list.head; 

		while (currNode != null) 			//go through all nodes to check this already exists in linked list
		{ 
			if(currNode.key.equals(key))
			{	
				currNode.frequency++;		//if exists increase frequency by 1 and return the list
				return list; 
			}
			// Go to next node 
			currNode = currNode.next; 		//move to the next node
			
		} 

		Node new_node = new Node(key); 		//if not exists make a new node and it should be the last node
		new_node.next = null; 


		if (list.head == null) 				//if there are no node, this is initial state and 1st node should be head
		{ 
			list.head = new_node; 			//assign current node as head
		} 
		else 								//else put current node behind the last node and make it the last node
		{ 
			Node last = list.head; 
			while (last.next != null) 
			{ 
				last = last.next; 
			} 

			
			last.next = new_node; 
		} 

		
		return list; 		//return the linked list
	} 


	public static int getSize(LinkedList list)		//to get number of nodes in a linked list
	{
		Node currNode = list.head; 			//begin with head
		int size = 0;
		//System.out.println("Value: "); 

		// Traverse through the LinkedList 
		while (currNode != null) 	
		{ 
			size++;		//increase size count
			

			// Go to next node 
			currNode = currNode.next; 
		} 
		return size;	//finally return size
	}

	//method to search an entry by key and linked list
	public static int searchElement(LinkedList list, String key) 	
	{ 
		Node currNode = list.head; 	//begin with head

		// Traverse through the LinkedList 
		while (currNode != null) 
		{ 
			if(currNode.key.equals(key)) 	//if entry exists return it's frequency
			{
				return currNode.frequency;
			}
			

			// Go to next node 
			currNode = currNode.next; 
		} 
		return 0;	//if not exists return 0
	}




	
	public static void printList(LinkedList list) //prints data in a linked list
	{ 
		Node currNode = list.head; 

		System.out.println("LinkedList: "); 

		// Traverse through the LinkedList 
		while (currNode != null) 
		{ 
			// Print the frequency at current node 
			System.out.println(currNode.key + " "+ currNode.frequency); 

			// Go to next node 
			currNode = currNode.next; 
		} 


	} 

	//prints data in a linked list regarding an element
	public static void printElement(LinkedList list, String key) 
	{ 
		Node currNode = list.head; 

		System.out.println("Value: "); 

		// Traverse through the LinkedList 
		while (currNode != null) 
		{ 
			// Print the frequency at current node
			if(currNode.key.equals(key)) 
			{
				System.out.println(currNode.key + " "+ currNode.frequency); 
				return;
			}
			

			// Go to next node 
			currNode = currNode.next; 
		} 
		System.out.println("Does not exist"); 
	}


} 
