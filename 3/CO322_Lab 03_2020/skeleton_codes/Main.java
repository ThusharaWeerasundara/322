
//Main class to execute code

public class Main
{
	public static void main(String[] args) throws Exception
	{
	   Support obj = new Support();	//create Support object to use operations
	   HashTableImp buckets;	//2 hashmaps from 2 types
	   HashTableImp2 buckets2;

	   //get distributions for 2 hashmaps
	   obj.getDataFromDivisionHashTable("sample-text1.txt");
	   obj.getDataFromMultiplicationHashTable("sample-text1.txt");
	
	   obj.getDataFromDivisionHashTable("sample-text2.txt");
	   obj.getDataFromMultiplicationHashTable("sample-text2.txt");

	   	//to test results
		HashTableImp bucketstest = obj.readFile("sample-text1.txt",47);
		HashTableImp2 bucketstest2 = obj.readFile2("sample-text1.txt",128);;
		

		System.out.println(bucketstest.search("the"));
		System.out.println(bucketstest2.search("the"));


		HashTableImp test2 = obj.readFile("sample-text2.txt",47);
		HashTableImp2 test3 = obj.readFile2("sample-text2.txt",128);;
		

		System.out.println(test2.search("the"));
		System.out.println(test3.search("the"));
		
	}
}