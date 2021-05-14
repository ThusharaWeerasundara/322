import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

class Fib {

    public static int fib_r(int x) {
	if(x <= 2) return 1;
	return fib_r(x-1) + fib_r(x-2);
    }

    public static int fib_i(int x) {
	int a=1, b=1, fib=1, i=2;
	while(i<x) {
	    fib = a + b;
	    a = b;
	    b = fib;
	    i+=1;
	}
	return fib;
    }


    public static void main(String [] args)  throws Exception
    {

    	long startTime_i, startTime_r;
		long endTime_i, endTime_r;

		long recursion; 
		long iteration;

		long total1, total2;
		
		BufferedWriter recTime = Files.newBufferedWriter(Paths.get("JavaRecursionTime.csv"));		//BufferedWriter objects to write to csv files
		BufferedWriter itrTime = Files.newBufferedWriter(Paths.get("JavaIterationTime.csv"));
		
		String [] header = {"ProblemSize", "Runtime"};
		
		recTime.write(String.join(",", header));
		recTime.newLine();
		itrTime.write(String.join(",", header));
		itrTime.newLine();
		
		for (int i = 1; i <= 40; i++) 
			{
				// For recursion algorithm
				
				total1 = 0;
				total2 = 0;

				for (int j = 1; j <= 50 ; j++) 			//check for 50 times to get the average
				{
					startTime_r = System.nanoTime();	// starting the time calculation
		 
					fib_r(i);
					
					endTime_r = System.nanoTime();  // ending the time calculation	

					// For itterative algorithm
				
					startTime_i = System.nanoTime(); // starting the time calculation
					 
					fib_i(i);
					
					endTime_i = System.nanoTime();		// ending the time calculation

					iteration = endTime_i - startTime_i;	//calculate runtime for each algorithm in ns
					recursion = endTime_r - startTime_r;

					total1 = total1 + iteration;
					total2 = total2 + recursion;

				}


				iteration = total1/50;	//calculate averages
				recursion = total2/50;
				
				
				itrTime.write(i + "," + iteration/1000000.0);	//write to files in ms
				itrTime.newLine();

				
				recTime.write(i + "," + recursion/1000000.0);
				recTime.newLine();

				System.out.println("Problem size: " + i + ", Iteration: " + iteration/1000000.0 + ", Recursion: " + recursion/1000000.0 + " (In ms.)");	//display results in ms
			}
		itrTime.close();	//close resources
		recTime.close();
	
	//from the time it takes to measure and start of the algorithm and the time algorithm completes and the time the final measurement is taken is same for both algorithms for all i values
    //therefore we can take it as a constant and therefore neglect it 		

    }
}

	
