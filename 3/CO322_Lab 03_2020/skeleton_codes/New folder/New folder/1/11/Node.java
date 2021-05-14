//this class is a node in a linked list
class Node { 

		String key;		//node has a key, frequency and the next node
		int frequency; 
		Node next; 

		// Constructor to set a node
		Node(String key) 
		{ 
			this.key = key; 
			this.frequency = 1; 
			next = null; 
		} 
	} 