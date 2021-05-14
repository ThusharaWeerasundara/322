import csv
import matplotlib.pyplot as plt  #to plot
import numpy as np  #to deal with arrays

x_axis = np.arange(1,41,1)  #values in x axis(problem size)

Javaiteration_time = [] 
with open("JavaIterationTime.csv") as iterationTime:  #to read and store runtime values for iteration
    duration = csv.DictReader(iterationTime)
    for row in duration:
        Javaiteration_time.append(float(row["Runtime"]))
        

Javarecursion_time = []
with open("JavaRecursionTime.csv") as recursionTime:    #to read and store runtime values for recursion
    duration2 = csv.DictReader(recursionTime)
    for row in duration2:
        Javarecursion_time.append(float(row["Runtime"]))

Pythoniteration_time = []
with open("PythonIterationTime.csv") as recursionTime:    #to read and store runtime values for recursion
    duration2 = csv.DictReader(recursionTime)
    for row in duration2:
        Pythoniteration_time.append(float(row["Runtime"]))


Pythonrecursion_time = []
with open("PythonRecursionTime.csv") as recursionTime:    #to read and store runtime values for recursion
    duration2 = csv.DictReader(recursionTime)
    for row in duration2:
        Pythonrecursion_time.append(float(row["Runtime"]))



plt.plot(x_axis, Javaiteration_time, label = "iteration(java)")   #plot execution time against the problem size for both algorithms in both languages
plt.plot(x_axis, Javarecursion_time, label = "recursion(java)")

plt.plot(x_axis, Pythoniteration_time, label = "iteration(Python)")   
plt.plot(x_axis, Pythonrecursion_time, label = "recursion(Python)")

# giving a title to my graph 
plt.title('Execution time of fibbonacci algorithm in Java and Python') 

# show a legend on the plot 
plt.legend()

plt.xlabel("Problem Size")
plt.ylabel("execution time/(ms)")
  
# function to show the plot 
plt.show() 
