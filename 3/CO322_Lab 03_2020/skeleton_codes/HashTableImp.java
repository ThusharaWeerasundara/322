/*********************************************
 
	* CO322: Data structures and algorithms
 
	* Implementation of the hashTable
 *********************************************/

import java.math.BigInteger;
class HashTableImp implements HashTable //this class implements HashTable interface. This hashtable use hash division
{

    //LinkedList<Integer>[] vertex = new LinkedList[5];

	/* Put your code here */
	protected LinkedList [] BucketSet;		//to store a table of Buckets need a array of linked lists
	protected int Buckets;					//number of Buckets

	public HashTableImp(int Buckets) 		//initializing a hashtable
		{	
			 this.Buckets = Buckets;		
			 BucketSet = new LinkedList[Buckets];

			 for (int i = 0;i < Buckets; i++) 	//initialize buckets
				 {
				 	BucketSet[i] = new LinkedList();
				 }
			 
		// create a open hash table with given number of Buckets 
	    
		}


	
	public int hashFunction(String key) 	//generate bucket in hashtable for a key value
	    { 
	       	//index = k % no_of_buckets
	        return asciValue(key).mod(new BigInteger(Integer.toString(Buckets))).intValue(); 	//returns absolute value
	    }     

	  



	public BigInteger asciValue(String key)		//generate key for an string
	{
			BigInteger keysize = new BigInteger("0");	//use BigInteger because could exceed integer range

			BigInteger base;

			int len = key.length();	//get word length

			int power = len - 1;

	        for (int i = 0; i < len ; i++) 
			{
				base = new BigInteger("128").pow(power);
				//String value = Integer.toString(key.charAt(i));
				BigInteger tmp = base.multiply(new BigInteger(Integer.toString(key.charAt(i))));
				keysize = keysize.add(tmp);


				//k//eysize = keysize + arr[i]*((long)Math.pow(128, len -1 -i));	//key = 128^0*x1 + 128^2*x2+ ..... 
			}

			
			return keysize;	//return key
	}
	

	public void insert(String key)	//inserts an string into hashtable
		{	
			int bucketIndex = hashFunction(key); 	//1st get bucket
			LinkedList.insertToBucket(BucketSet[bucketIndex], key);		//then insert to the correct linked list
		
		}

    
	/* given the key return the number of times it was inserted 
     
	 * to the table 
     
	*/
  
	public int search(String key)	
	{	
		int bucketIndex = hashFunction(key); 	//get bucket	
		return (LinkedList.searchElement(BucketSet[bucketIndex], key));	//return frequency of entry
	}
	
	public double getAverage()		//get average number of nodes 
	{	
		int words = 0;

		for (int i = 0;i < Buckets; i++) 	//get total nodes in hashtable
				 {
				 	words = LinkedList.getSize(BucketSet[i]) + words;
				 }

		return words*1.0/this.Buckets;	//return average
	}
    
	public int getMax()	//get maximum nodes in a bucket 
	{
		int max = LinkedList.getSize(BucketSet[0]);   //assume 1st bucket has maximum number of nodes

		for (int i = 0;i < Buckets; i++) 	//iterate and find the bucket with most nodes
				 {	
				 	int words = LinkedList.getSize(BucketSet[i]);
				 	if(words > max)
				 		max = words;
				 }

				 return max;
	}

	public int getMin()	//get minimum nodes in a bucket 
	{
		int min = LinkedList.getSize(BucketSet[0]);   //assume 1st bucket has minimum number of nodes

		for (int i = 0;i < Buckets; i++) 	//iterate and find the bucket with most nodes
				 {	
				 	int words = LinkedList.getSize(BucketSet[i]);
				 	if(words < min)
				 		min = words;
				 }

				 return min;
	}

	public double getStandardDeviation()	//get standard deviation of nodes in hashtable 
	{
		double sd = 0;
		double sum = 0;

		double avg = getAverage();		//get average

		for (int i = 0;i < Buckets; i++) 	//calculate variance
				 {	
				 	int words = LinkedList.getSize(BucketSet[i]);
				 	//System.out.println(words);
				 	sum = sum + Math.pow((avg - words) , 2);
				 }
		sd = Math.pow(sum/Buckets , 0.5);	//get standard deviation from variance

		return sd;
	}

}// end HashTableImp 
