package simplex;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}
	
	private void run() {
		try {
			/*double[][] a = { { 2, 0, 0}, { 1, 1, 0}, { 2, 0, 1} };
			Matrix m = new Matrix(a);
			
			//Stampa la matrice
			System.out.println("Matrice di partenza:");
			System.out.println(m.toString());
			
			//Stampa il determinante della matrice
			System.out.println("\nDeterminante: ");
			System.out.println(m.getDeterminant());
			
			//Stampa la sottomatrice fissando la riga i-esima e colonna j-esima
			//RICORDA: gli indici partono da 0
			System.out.println("\nSottomatrice con riga 3 e colonna 1 fissata: ");
			System.out.println(new Matrix(m.getSubMatrix(2, 0, a)));
			
			//Stampa la matrice inversa
			System.out.println("\nMatrice inversa:");
			System.out.println(m.reverseMatrix());
			
			//System.out.println("Matrice trasposta: ");
			System.out.println("\nMatrice trasposta: ");
			System.out.println(m.transpose());
			
			//Prodotto tra matrici
			System.out.println("\nProdotto tra matrici: ");
			double[][] d1 = { { 1, 1, 1 }, { 2, 2, 2 }, { 3, 3, 3 }, { 4, 4, 4 } };
			Matrix D = new Matrix(d1);
			
			double e1[][] = { { 1, 1, 1, 1 }, { 2, 2, 2, 2 }, { 3, 3, 3, 3 } };
			Matrix E = new Matrix(e1);
	
			System.out.println(Matrix.matrixProduct(D, E));*/
			
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
			
			Matrix m = new Matrix(A1);
			
			//System.out.println(m + "Righe: "+m.getRows()+" Colonne: "+m.getColumns());
			//System.out.println(m.reverseMatrix());
			
			//System.out.println(Matrix.addMatrix(new Matrix(B), new Matrix(B1)));
			
			//System.out.println(new Matrix(B).reverseMatrix());
			new Simplex(new Matrix(A), new Matrix(B), new Matrix(c), indexes, new Matrix(b)).compute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
