I wanted to practice implementing some algorithms that I learned in CS2 in order to study for my final exam.

In this repo is the code I wrote to implement these common algorithms to study with.  
I hope they might help someone else study for their exam too and better learn the matrial.

I tried to add comments to make them as clear as possible, and I tried to write the code to be as simple as I could so that anyone trying to study these algorithms can easily read me code and see how it works.

Each one has an example input hard coded into it so you can run it right away without coming up with your own input.

I have also included a make file to easily compile and run each algorithm as well as clean up all the .class files when you're done. To use it:  
1. Open the linux terminal
2. Navigate to the directory containing the .java files and the Makefile using cd
3. Run your chosen command based on which algorithm you want to compile and run:
	a) `make knap`	: AllOrNoneKnapsack.java
	b) `make bell`	: BellmanFord.java
	c) `make dijk`	: Dijkstra.java
	d) `make floyd`	: FloydWarshall.java
	e) `make prim`	: Prim.java
4. To clean up all the .class files run: `make clean`

Happy studying! :)
