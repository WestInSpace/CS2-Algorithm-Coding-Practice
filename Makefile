.PHONY: knap bell dijk floyd long prim clean

knap:
	javac AllOrNoneKnapsack.java
	java AllOrNoneKnapsack

bell:
	javac BellmanFord.java
	java BellmanFord

dijk:
	javac Dijkstra.java
	java Dijkstra

floyd:
	javac FloydWarshall.java
	java FloydWarshall

long:
	javac LongestCommonSubsequence.java
	java LongestCommonSubsequence

prim:
	javac Prim.java
	java Prim

#Clean up all the compiled files, to use call: make clean
#Use * to delete all file of format such as: *.class
clean:
	rm -f *.class
