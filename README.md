# simplex
Java class to calculate an optimal solution of a linear programming problem using the matricial simplex method.

The program is divided into 3 main routine:

* The test of optimality, that return false or true, and, in that case, the program returns an optimal solution
* The test of unlimitedness, that throw an exception if the problem is unlimited
* The calculus of the exiting variable and entering variable, and, next the new base with switched columns
