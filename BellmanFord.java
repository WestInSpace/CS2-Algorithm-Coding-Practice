//To compile: javac Java.java
//To run: java Java

public class BellmanFord{
	public static void main(String[] args) {
		Edge[] e = new Edge[8];
		e[0] = new Edge(1, 2, 5);
		e[1] = new Edge(2, 1, 50);
		e[2] = new Edge(2, 3, 15);
		e[3] = new Edge(3, 1, 30);
		e[4] = new Edge(3, 4, 15);
		e[5] = new Edge(4, 1, 15);
		e[6] = new Edge(4, 3, 5);
		e[7] = new Edge(2, 4, 5);

		int[] dist = bellmanFord(4, e, 1);
		
		System.out.println("Shortest distance from 1 to every other node")
		for(int i=0; i<dist.length; i++){
			System.out.println("1 -> " + (i+1) + ": " + dist[i]);
		}

	}//end of main

	public static int[] bellmanFord(int numVs, Edge[] e, int src){
		int[] dist = new int[numVs];
		for(int i=0; i<numVs; i++)
			dist[i] = Integer.MAX_VALUE;
		dist[src-1] = 0;
		
		boolean changeMade;
		int i = 0;
		do {
			System.out.println("Itteration: " + (i+1));
			changeMade = false;
			for(int j=0; j<e.length; j++){
				if(dist[e[j].src-1] + e[j].weight < dist[e[j].dest-1]){
					dist[e[j].dest-1] = dist[e[j].src-1] + e[j].weight;
					changeMade = true;
				}
			}
			i++;
		} while(i < numVs && changeMade);

		return dist;

	}

}//end of class Java

class Edge{
	public int src;
	public int dest;
	public int weight;	

	public Edge(int src, int dest, int weight){
		this.src = src;
		this.dest = dest;
		this.weight = weight;
	}
}
