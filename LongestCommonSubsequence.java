//To compile: javac Java.java
//To run: java Java

public class LongestCommonSubsequence{
	public static void main(String[] args) {
		String A = "CHIMPANZEE";
		String B = "HUMAN";
		
		char[] sol = LCS(A, B);
		System.out.println("Longest Common Subsequence:");
		for(int i=0; i<sol.length; i++)
			System.out.print(sol[i]);
		System.out.println();

	}//end of main
	
	//returns a char array with the characters in the longest common subsequence
	public static char[] LCS(String str1, String str2){
		int[][] solMatrix = new int[str1.length()+1][str2.length()+1];
		
		for(int i=1; i<solMatrix.length; i++){
			for(int j=1; j<solMatrix[i].length; j++){
				if(str1.charAt(i-1) == str2.charAt(j-1)){
					solMatrix[i][j] = solMatrix[i-1][j-1] + 1;
				}else{
					solMatrix[i][j] = Math.max(solMatrix[i][j-1], solMatrix[i-1][j]);
				}
			}
		}
		
		System.out.println("Solution Matrix: ");
		for(int i=0; i<solMatrix.length; i++){
			for(int j=0; j<solMatrix[i].length; j++){
				System.out.print(solMatrix[i][j] + ", ");
			}
			System.out.println();
		}
		System.out.println();

		int solLength = solMatrix[solMatrix.length-1][solMatrix[solMatrix.length-1].length-1];
		char[] sol = new char[solLength];
		
		int i = solMatrix.length-1, j = solMatrix[solMatrix.length-1].length-1, solIdx = sol.length-1;
		
		while(solIdx >= 0){
			if(str1.charAt(i-1) == str2.charAt(j-1)){
				sol[solIdx] = str1.charAt(i-1);
				solIdx--;
				i--;
				j--;
			}else{
				if(solMatrix[i-1][j] > solMatrix[i][j-1])
					i--;
				else
					j--;
			}
		}

		return sol;
	}

}//end of class Java
