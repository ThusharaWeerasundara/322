import csv
import matplotlib.pyplot as plt  #to plot
import numpy as np  #to deal with arrays

x_axis = np.arange(1,41,1)  #values in x axis(problem size)

iteration_time = [] 
with open("JavaIterationTime.csv") as iterationTime:  #to read and store runtime values for iteration
    duration = csv.DictReader(iterationTime)
    for row in duration:
        iteration_time.append(float(row["Runtime"]))
        

recursion_time = []
with open("JavaRecursionTime.csv") as recursionTime:    #to read and store runtime values for recursion
    duration2 = csv.DictReader(recursionTime)
    for row in duration2:
        recursion_time.append(float(row["Runtime"]))


plt.plot(x_axis, iteration_time, label = "iteration")   #plot execution time against the problem size for both algorithms
plt.plot(x_axis, recursion_time, label = "recursion")

# giving a title to my graph 
plt.title('Execution time of fibbonacci algorithm in Java') 

# show a legend on the plot 
plt.legend()

plt.xlabel("Problem Size")
plt.ylabel("execution time/(ms)")
  
# function to show the plot 
plt.show() 
