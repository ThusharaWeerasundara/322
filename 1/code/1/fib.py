#!/usr/bin/python  #shebang

import matplotlib.pyplot as plt  #to plot
import timeit       #to measure execution time
import numpy as np  #to deal with arrays
import csv

def fib_r(x) :  #recursive method
  
    if (x <= 2):
    
        return 1

    return fib_r(x-1) + fib_r(x-2)


def fib_i(x) :  #iterative method
    
    a = 1
    b = 1
    fib = 1
    i = 2
    while i < x:
        fib = a +b
        a = b
        b = fib
        i+=1
    
    return fib

max = 41                    #max problem size + 1 
x_axis = np.arange(1,max,1)  #values in x axis(problem size)
iteration_time = []         #list to store iteration algorithm's execution times
recursion_time = []         #list to store recursion algorithm's execution times

for x in range(1,max):       #using for loop for calculation

   #from the time it takes to measure and start of the algorithm and the time algorithm completes and the time the final measurement is taken is same for both algorithms for all x values
   #therefore we can take it as a constant and therefore neglect it 
   
   start_r = timeit.default_timer()     #start time for recursion algorithm
   ansr = fib_r(x)                      #calling algorithm
   duration_r = timeit.default_timer() - start_r  #execution time for recursion algorithm
   

   start_i = timeit.default_timer()     #start time for iteration algorithm
   ansi = fib_i(x)                      #calling algorithm
   duration_i = timeit.default_timer() - start_i    #execution time for iteration algorithm

   recursion_time.append(duration_r * 1000) #store time in ms
   iteration_time.append(duration_i * 1000)

   print ("Fib of " + str(x) + " using recursion = " + str(ansr) + " runtime = " + str(duration_r * 1000) + "ms")   #display results
   print ("Fib of " + str(x) + " using iteration = " + str(ansi) + " runtime = " + str(duration_i * 1000) + "ms\n")


keys = "ProblemSize,Runtime"    #headers to store runtimes

with open('PythonRecursionTime.csv', 'w') as f:     #store runtimes in csv files
    f.write(keys + "\n")
    for time in range(0, max - 1):
        f.write(str(time) + "," + str(recursion_time[time]) + "\n")

with open('PythonIterationTime.csv', 'w') as f:
    f.write(keys + "\n")
    for time in range(0, max - 1):
        f.write(str(time) + "," + str(iteration_time[time]) + "\n")


plt.plot(x_axis, iteration_time, label = "iteration")   #plot execution time against the problem size for both algorithms
plt.plot(x_axis, recursion_time, label = "recursion")



# giving a title to my graph 
plt.title('Execution time of fibbonacci algorithm in Python') 

# show a legend on the plot 
plt.legend()

plt.xlabel("Problem Size")
plt.ylabel("execution time/(ms)")
  
# function to show the plot 
plt.show() 
