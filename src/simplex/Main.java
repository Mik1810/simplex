package simplex;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}
	
	private void run() {
		try {
			
			double[][] A = {{-1,-1,2,1,0},
							{1,-2,1,0,1}};
			
			double[] c = {-3,2,4,0,0};
			
			double[][] B = {{-1,2},
							{-2,1}};
			
			int[] indexes = {2,3};
			
			double[][] A1 = {{-1,0,1,0},
							 {1,0,1,1},
							 {0,1,0,0},
							 {1,1,3,1}};
			
			double[][] B1 = {{1,-2},
							 {2,-1}};
			
			double[] b = {1, -1};
			
			//new Simplex(new Matrix(A), new Matrix(B), new Matrix(c), indexes, new Matrix(b)).compute();
			
			//Test 2
			double[][] A2 = {{1,2,2,1,0,0},
							{2,1,2,0,1,0},
							{2,2,1,0,0,1}};
			double[] c2 = {-10,-12,-12,0,0,0};
			int[] indexes2 = {4,5,6};
			double[] b2 = {20,20,20};
			double[][] B2 = {{1,0,0},
							{0,1,0},
							{0,0,1}};
			
			new Simplex(new Matrix(A2), new Matrix(c2), indexes2, new Matrix(b2)).compute();
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
