//To compile: javac Java.java
//To run: java Java

public class AllOrNoneKnapsack{
	public static void main(String[] args) {
		Item[] items = new Item[5];
		items[0] = new Item(4, 6);
		items[1] = new Item(2, 4);
		items[2] = new Item(1, 3);
		items[3] = new Item(6, 9);
		items[4] = new Item(3, 5);
		int maxWeight = 10;

		int[] sol = getMaxValue(maxWeight, items);
		System.out.println("max value: " + sol[0]);
		
		System.out.println("Items in knapsack: ");
		for(int i=1; i<sol.length; i++){
			if(sol[i] == 1)
				System.out.print(i + ", ");
		}
		System.out.println();

	}//end of main
	
	//returns an array with arr[0] = maxValue and all other are 1 or 0 to tell if the item is in the solution or not
	public static int[] getMaxValue(int maxWeight, Item[] items){
		int[][] solMatrix = new int[items.length + 1][maxWeight + 1];
		for(int i=1; i<solMatrix.length; i++){
			for(int j=1; j<solMatrix[i].length; j++){
				if(items[i-1].weight > j){
					solMatrix[i][j] = solMatrix[i-1][j];
				}else{
					solMatrix[i][j] = Math.max((solMatrix[i-1][j-items[i-1].weight] + items[i-1].value), solMatrix[i-1][j]);
				}
			}
		}
		
		System.out.println("The solution matrix: ");
		for(int i=0; i<solMatrix.length; i++){
			for(int j=0; j<solMatrix[i].length; j++){
				System.out.print(solMatrix[i][j] + ", ");
			}
			System.out.println();
		}
		System.out.println();

		int[] sol = new int[items.length + 1];
		sol[0] = solMatrix[solMatrix.length-1][solMatrix[solMatrix.length-1].length-1];
		int solIdx = sol.length-1;
		int weightIdx = maxWeight;
		//get the items that result in that solution
		for(int i=solMatrix.length-1; i>0; i--){
			if(weightIdx <= 0)
				break;

			if(solMatrix[i-1][weightIdx] != solMatrix[i][weightIdx]){
				sol[solIdx] = 1;
				weightIdx -= items[i-1].weight;
			}else{
				sol[solIdx] = 0;
			}
			solIdx--;
		}
		return sol;
	}

}//end of class Java

class Item{
	public int weight;
	public int value;
	
	public Item(int weight, int value){
		this.weight = weight;
		this.value = value;
	}
}//end of class Item
