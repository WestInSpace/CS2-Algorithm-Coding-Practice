//To compile: javac Java.java
//To run: java Java

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Dijkstra{
	public static void main(String[] args) {
		AdjListGraph graph = new AdjListGraph(5);
		
		/*
		graph.addEdge(0, 1, 10);
		graph.addEdge(0, 4, 3);
		graph.addEdge(1, 2, 2);
		graph.addEdge(1, 4, 4);
		graph.addEdge(2, 3, 9);
		graph.addEdge(3, 2, 7);
		graph.addEdge(4, 1, 1);
		graph.addEdge(4, 2, 8);
		graph.addEdge(4, 3, 2);
		*/

		graph.addEdge(0, 1, 10);
		graph.addEdge(0, 4, 3);
		graph.addEdge(1, 2, 8);
		graph.addEdge(1, 3, 2);
		graph.addEdge(2, 0, 2);
		graph.addEdge(2, 1, 3);
		graph.addEdge(2, 3, 4);
		graph.addEdge(3, 0, 5);
		graph.addEdge(3, 2, 4);
		graph.addEdge(4, 1, 12);
		graph.addEdge(4, 2, 16);
		graph.addEdge(4, 3, 13);

		int[] sol = graph.dijkstra(0);

		for(int i=0; i<sol.length; i++){
			out.println(sol[i] + ", ");
		}

	}//end of main
}//end of class Dijkstra

class AdjListGraph{
	int size;
	ArrayList<ArrayList<Node>> graph;
	
	public AdjListGraph(int size){
		this.size = size;
		graph = new ArrayList<ArrayList<Node>>(size);
		for(int i=0; i<size; i++){
			graph.add(new ArrayList<Node>(5));
		}
	}//end of AdjListGraph constructor

	public void addEdge(int src, int dest, int weight){
		graph.get(src).add(new Node(dest, weight));
	}//end of addEdge
	
	//return the minimum path from start to all other nodes
	public int[] dijkstra(int start){
		PriorityQueue<Node> pq = new PriorityQueue<Node>(10);
		boolean[] complete = new boolean[size];
		int[] dist = new int[size];
		
		//inizilize the dist array
		for(int i=0; i<size; i++){
			dist[i] = Integer.MAX_VALUE;
		}

		pq.offer(new Node(start, 0));
		
		while(pq.size() != 0){
			Node curr = pq.poll();
			
			if(complete[curr.id]){
				continue;
			}

			dist[curr.id] = curr.weight;
			complete[curr.id] = true;

			for(int i=0; i<graph.get(curr.id).size(); i++){
				pq.offer(new Node(graph.get(curr.id).get(i).id, (curr.weight + graph.get(curr.id).get(i).weight)));
			}
		}
	
		return dist;
	}//end of dijkstra

}//end of class AdjListGraph

class Node implements Comparable<Node>{
	int id;
	int weight;
	
	public Node(int id, int weight){
		this.id = id;
		this.weight = weight;
	}//end of Node constructor
	
	@Override
	public int compareTo(Node n2){
		return weight - n2.weight;
	}//end of compareTo

}//end of class Node
