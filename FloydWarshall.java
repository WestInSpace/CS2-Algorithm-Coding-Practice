//To compile: javac Java.java
//To run: java Java

public class FloydWarshall{
	public static void main(String[] args) {
		AdjMatrixGraph graph = new AdjMatrixGraph(4);
		graph.addEdge(0, 1, 5);
		graph.addEdge(1, 0, 50);
		graph.addEdge(1, 2, 15);
		graph.addEdge(1, 3, 5);
		graph.addEdge(2, 0, 30);
		graph.addEdge(2, 3, 15);
		graph.addEdge(3, 0, 15);
		graph.addEdge(3, 2, 5);
		//graph.print();
		int[][] sol = graph.floydWarshall();

		for(int i=0; i<sol.length; i++){
			for(int j=0; j<sol[i].length; j++){
				System.out.print(sol[i][j] + ", ");
			}
			System.out.println();
		}

	}//end of main
}//end of class Java

class AdjMatrixGraph{
	public int numNodes;
	int[][] graph;
	
	public AdjMatrixGraph(int numNodes){
		this.numNodes = numNodes;
		graph = new int[numNodes][numNodes];
		for(int i=0; i<numNodes; i++){
			for(int j=0; j<numNodes; j++){
				graph[i][j] = Integer.MAX_VALUE;
			}
		}
	}
	
	public void addEdge(int src, int dest, int weight){
		graph[src][dest] = weight;
	}

	private int[][] createSolMatrix(){
		int[][] newGraph = new int[numNodes][numNodes];
		for(int i=0; i<numNodes; i++){
			for(int j=0; j<numNodes; j++){
				if(i == j){
					newGraph[i][j] = 0;
				}else{
					newGraph[i][j] = graph[i][j];
				}
			}
		}
		return newGraph;
	}

	public int[][] floydWarshall(){
		int[][] solMatrix = createSolMatrix();
		for(int k=0 ; k<numNodes; k++){
			for(int i=0; i<numNodes; i++){
				for(int j=0; j<numNodes; j++){
					//skip row and collumn k (currant node) and skip the diagnoal (0)
					if(i == k || j == k || i == j)
						continue;
					//Skip if either value to be added in INF to avoid overflow
					if(solMatrix[i][k] == Integer.MAX_VALUE || solMatrix[k][j] == Integer.MAX_VALUE)
						continue;

					if(solMatrix[i][k] + solMatrix[k][j] < solMatrix[i][j])
						solMatrix[i][j] = solMatrix[i][k] + solMatrix[k][j];
				}
			}
		}
		return solMatrix;
	}
	
	public void print(){
		for(int i=0; i<numNodes; i++){
			for(int j=0; j<numNodes; j++){
				if(graph[i][j] == Integer.MAX_VALUE)
					System.out.print("INF, ");
				else
					System.out.print(graph[i][j] + ", ");
			}
			System.out.println();
		}
	}

}
