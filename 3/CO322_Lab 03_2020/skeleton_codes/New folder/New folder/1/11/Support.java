import java.io.*; // for file IO
import java.nio.file.Files;	//for manage files
import java.nio.file.Paths;
import java.util.ArrayList;	//for use ArrayLists

//class Supports contains methods that are used in Main class

class Support	
{

public boolean isPrime(int n) 	//To check whether  an Integer is prime or not. In hash division method we need prime number of nodes for better performance
{
    for(int i=2;i<n;i++) 
    {
        if(n%i==0)				//if n divides by any value less than n not a prime
            return false;
    }
    return true;
}

public boolean isPowerOfTwo(int n)	//To check whether  an Integer is a power of 2 or not. In hash multiplication 
    {								//method we need powers of 2 number of nodes for better performance
        if (n == 0)				//0 is not a power
            return false;
         
        while (n != 1)
        {
            if (n % 2 != 0)		//if not divisible by 2 not a power
                return false;
            n = n / 2;			
        }
        return true;
    }

public void getDataFromDivisionHashTable(String file)	throws Exception	//method to generate a csv file from hashdivision table
{		
		//array lists to store values of minimum, maximum, average, standard deviation of nodes in a linked list
		ArrayList <Integer> max = new ArrayList <Integer>();
		ArrayList <Integer> min = new ArrayList <Integer>();
		ArrayList <Double> avg = new ArrayList <Double>();
		ArrayList <Double> sd = new ArrayList <Double>();
		ArrayList <Integer> lists = new ArrayList <Integer>();

		for(int i = 1; i < 250; i++)
		{
			if(!isPrime(i) || i == 2)	//select only primes for this operations except 2 as node count
			{
				continue;
			}

			HashTableImp buckets = readFile(file,i);	//create a hashtable with hashdivision method from words	

			//store values of minimum, maximum, average, standard deviation of nodes in a linked list
			max.add(buckets.getMax());			
			min.add(buckets.getMin());
			avg.add(buckets.getAverage());
			sd.add(buckets.getStandardDeviation());
			lists.add(i);

			//write the values to a csv file
			writeFile(file+"DataFromDivisionHashTable.csv", lists, max, min, avg, sd);
			

		}
}


public void getDataFromMultiplicationHashTable(String file)	throws Exception
{
		ArrayList <Integer> max = new ArrayList <Integer>();
		ArrayList <Integer> min = new ArrayList <Integer>();
		ArrayList <Double> avg = new ArrayList <Double>();
		ArrayList <Double> sd = new ArrayList <Double>();
		ArrayList <Integer> lists = new ArrayList <Integer>();

		for(int i = 2; i < 2000; i++)
		{
			if(!isPowerOfTwo(i))	//select only powers of 2 for this operation as node count
			{
				continue;
			}

			HashTableImp2 buckets = readFile2(file,i);	//create a hashtable with hashmultiplication method from words	

			//store values of minimum, maximum, average, standard deviation of nodes in a linked list			
			max.add(buckets.getMax());
			min.add(buckets.getMin());
			avg.add(buckets.getAverage());
			sd.add(buckets.getStandardDeviation());
			lists.add(i);

			//write the values to a csv file
			writeFile(file+"DataFromMultiplicationHashTable.csv", lists, max, min, avg, sd);
			

		}
}


//method to write data to a csv file. data is passed as arraylists

public void writeFile(String file, ArrayList <Integer> buckets, ArrayList <Integer> maxvalues, ArrayList <Integer> minvalues, 
	ArrayList <Double> avgvalues, ArrayList <Double> sdvalues) throws Exception
{
	BufferedWriter entries = Files.newBufferedWriter(Paths.get(file));		//BufferedWriter objects to write to csv files
	
	//headers for the csv file	
	String [] header = {"Number of Buckets", "Maximum Nodes", "Minimum Nodes", "Average Nodes", "Standard Deviation"};
	
	//write headers in csv	
	entries.write(String.join(",", header));
	entries.newLine();
	
	int len = buckets.size();	//get number of entries

	for (int i = 0;i < len;i++ )  //write entries in csv with format : bucket_count; maximum_nodes; minimum_nodes; average_nodes;
									//standard_deviation
	{
		entries.write(buckets.get(i) + "," + maxvalues.get(i) + "," + minvalues.get(i) + "," + avgvalues.get(i) + "," +
		 sdvalues.get(i));	//write to files
		entries.newLine();
	}
	

	entries.close();	//close resources after using
}


//reads and store words in a file in a hashtabe using hash division. needs file name and number of buckets as arguments

public HashTableImp readFile(String file, int size)
	{
		HashTableImp buckets = new HashTableImp(size);	//make a hashtable with given number of buckets

		BufferedReader reader = null; 	//BufferedReader to read from file
		String line = null; 

		try 	//read lines from a file, can cause exceptions
				{ 
					reader = new BufferedReader(new FileReader(file));

					while((line = reader.readLine()) != null)
					{
						line = line.replaceAll("\\p{Punct}","");	//replace punctuations using regex
						String [] words = line.split(" ");			//store words in a line

						for (String word : words ) 					//store words in array in lower case
						{
								buckets.insert(word.toLowerCase());
						}
					}

					reader.close(); 	//close resousources after use
				} 

			catch(IOException e) 	//exception handling
				{ 
					// IOException is more generic (FileNotFound is a type of a 
					// IOException, so catching only that is sufficient 
					System.out.println("error while reading " + e);
					
				}

				return buckets; 	//return the new hashmap with entries

	}

//reads and store words in a file in a hashtabe using hash multiplication. needs file name and number of buckets as arguments

public HashTableImp2 readFile2(String file, int size)
	{
		HashTableImp2 buckets = new HashTableImp2(size);	//make a hashtable with given number of buckets

		BufferedReader reader = null; 	//BufferedReader to read from file
		String line = null; 

		try 	//read lines from a file, can cause exceptions
				{ 
					reader = new BufferedReader(new FileReader(file));

					while((line = reader.readLine()) != null)
					{
						line = line.replaceAll("\\p{Punct}","");	//replace punctuations using regex
						String [] words = line.split(" ");			//store words in a line

						for (String word : words ) 					//store words in array in lower case
						{
								buckets.insert(word.toLowerCase());
						}
					}

					reader.close(); 	//close resousources after use
				} 

			catch(IOException e) 
				{ 
					// IOException is more generic (FileNotFound is a type of a 
					// IOException, so catching only that is sufficient 
					System.out.println("error");
					
				}

				return buckets; //return the new hashmap with entries

	}


}