import java.math.BigInteger;
import java.math.BigDecimal;

class HashTableImp2 extends HashTableImp //to give hash multiplication methods extends parent class
{

	public HashTableImp2(int buckets) 	//constructor
		{	
			 super(buckets);
			 
		// create a open hash table with given number of buckets 
	    
		}


	public int hashFunction(String key) 	//method overiding used to make hashmultiplication method
	    { 
	        	BigDecimal keyvalue = new BigDecimal(asciValue(key));
	        	BigDecimal a = new BigDecimal(Double.toString( ((Math.pow(5,0.5) - 1.0)/2.0) ));  //use big decimal to prevent overflow

	        	keyvalue = keyvalue.multiply(a);	//get kA 

	        	BigInteger intPart = keyvalue.toBigInteger();	

	        	keyvalue = keyvalue.subtract(new BigDecimal(intPart));	//get (kA - floor(kA))

	        	double dec =  keyvalue.doubleValue(); 

	        	double ans = dec * Buckets;
	        	//System.out.println((int)Math.floor(ans));

	        	return (int)Math.floor(ans);	//h(k) = floor(m * (kA - floor(kA)))

	    }  
}