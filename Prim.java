//To compile: javac Java.java
//To run: java Java

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Arrays;

public class Prim{
	public static void main(String[] args) {
		
		AdjListGraph graph = new AdjListGraph(6);

		graph.addEdge(0, 1, 10);
		graph.addEdge(1, 0, 10);
		graph.addEdge(0, 2, 1);
		graph.addEdge(2, 0, 1);
		graph.addEdge(0, 3, 5);
		graph.addEdge(3, 0, 5);
		graph.addEdge(1, 2, 8);
		graph.addEdge(2, 1, 8);
		graph.addEdge(1, 4, 1);
		graph.addEdge(4, 1, 1);
		graph.addEdge(1, 5, 1);
		graph.addEdge(5, 1, 1);
		graph.addEdge(2, 3, 3);
		graph.addEdge(3, 2, 3);
		graph.addEdge(3, 4, 4);
		graph.addEdge(4, 3, 4);
		graph.addEdge(4, 5, 2);
		graph.addEdge(5, 4, 2);
		graph.addEdge(5, 2, 6);
		graph.addEdge(2, 5, 6);
		
		int[] parents = graph.prim();

		out.println("Parent list: ");
		for(int i=0; i<parents.length; i++){
			out.print(parents[i] + ", ");
		}
		out.println();

		int sol = graph.minSpanningTreeValue(parents);
		out.println("Minimum spanning tree value: " + sol);

	}//end of main
}//end of class Prim

class AdjListGraph{
	int size;
	ArrayList<ArrayList<Edge>> graph;

	public AdjListGraph(int size){
		this.size = size;
		graph = new ArrayList<ArrayList<Edge>>(size);
		for(int i=0; i<size; i++){
			graph.add(new ArrayList<Edge>(5));
		}
	}//end of AdjListGraph contructor

	public void addEdge(int src, int dest, int weight){
		graph.get(src).add(new Edge(src, dest, weight));
	}//end of addEdge
	
	//starts at node 0 and returns the parent list of the minSpanningTree info
	public int[] prim(){
		boolean[] visited = new boolean[size];
		int[] parents = new int[size];
		Arrays.fill(parents, -1);
		int[] keys = new int[size];
		Arrays.fill(keys, Integer.MAX_VALUE);

		keys[0] = 0;

		boolean allTrue = false;
		while(!allTrue){
			//pick the smllest value in keys that has not been visited
			int smallestKeyIdx = Integer.MAX_VALUE;
			int smallestKeyVal = Integer.MAX_VALUE;
			allTrue = true;
			for(int i=0; i<size; i++){
				if(!visited[i]){
					allTrue = false;
					if(keys[i] < smallestKeyVal){
						smallestKeyVal = keys[i];
						smallestKeyIdx = i;
					}
				}
			}
			
			if(allTrue)
				break;

			//Mark the smallest key as visisted in the visited list
			visited[smallestKeyIdx] = true;

			//Find the connections that are not in the V list
			//if the new edge weight is smller than the current edge weight update it and set it's parent
			for(int i=0; i<graph.get(smallestKeyIdx).size(); i++){
				if(!visited[graph.get(smallestKeyIdx).get(i).dest]){
					if(graph.get(smallestKeyIdx).get(i).weight < keys[graph.get(smallestKeyIdx).get(i).dest]){
						keys[graph.get(smallestKeyIdx).get(i).dest] = graph.get(smallestKeyIdx).get(i).weight;
						parents[graph.get(smallestKeyIdx).get(i).dest] = smallestKeyIdx;
					}
				}
			}

		}
		return parents;
	}//end of prim
	
	//takes in a parents list and returns the value of the minSpanningTree
	public int minSpanningTreeValue(int[] parents){
		int ret = 0;
		for(int i=1; i<parents.length; i++){
			for(int j=0; j<graph.get(i).size(); j++){
				if(graph.get(i).get(j).dest == parents[i]){
					ret += graph.get(i).get(j).weight;
				}
			}
		}
		return ret;
	}//end of minSpanningTreeValue

}//end of class AdjListGraph


class Edge implements Comparable<Edge>{
	public int src;
	public int dest;
	public int weight;

	public Edge(int src, int dest, int weight){
		this.src = src;
		this.dest = dest;
		this.weight = weight;
	}//end of contructor Edge
	
	@Override
	public int compareTo(Edge e){
		return weight - e.weight;
	}//end of compareTo

}//end of class Edge
