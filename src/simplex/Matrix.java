package simplex;
import java.util.LinkedList;
import java.util.Queue;

public class Matrix {

	private double[][] A;
	private double[] c;
	private double[][] B;
	private int[] indexes; //indici delle colonne di A che formano N

	public Matrix(double[][] A) {
		this.A = A;
	}

	public Matrix(double[][] A, double[] c, double[][] B, int[] indexes) {
		this.A = A;
		this.c = c;
		this.B = B;
		this.indexes = indexes;
	}

	public double[][] getMatrix() {
		return A;
	}

	public double getDeterminant() throws Exception {
		return det(A.length, A);
	}

	@Override
	public String toString() {

		String result = "";
		for (int i = 0; i < A.length; i++) {
			String s = "";
			for (int j = 0; j < A[i].length; j++) {
				if(A[i][j] == -0.0) A[i][j] = 0.0;
				if (A[i][j] >= 0) {
					s = s + "  " + String.format("%.1f", A[i][j]); // per l'indentazione
				} else {
					s = s + " " + String.format("%.1f", A[i][j]);
				}
			}
			s = "[" + s + " ]\n"; // stampo le righe dell'array e poi le accorpo creando una matrice
			result = result + s;
		}
		return result;
	}

	public static double det(int n, double matrix[][]) throws Exception {
		
		Matrix g = new Matrix(matrix);
		if (!g.checkSquared())
			throw new Exception("The matrix isn't squared");
		double det = 0;
		double[][] subMatrix = new double[n][n];

		switch (n) {

		case 1:
			return matrix[0][0];

		case 2:
			return (matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]);

		default:

			for (int i = 0; i < n; i++) { // fisso la riga
				int m = 0;
				for (int j = 1; j < n; j++) { // fisso la colonna
					int l = 0;
					for (int k = 0; k < n; k++) { // scorro l'elemento
						if (k == i)
							continue;
						subMatrix[m][l] = matrix[j][k];
						l++;
					}
					m++;
				}
				det = det + Math.pow(-1, i) * matrix[0][i] * det(n - 1, subMatrix);
			}
			return det;
		}
	}

	public static Matrix reverseMatrix(double[][] A) throws Exception {

		Matrix m = new Matrix(A);
		if (!m.checkSquared())
			throw new Exception("The matrix isn't squared");
		
		switch (A.length) {
		case 1:
			return m;

		case 2:
			System.out.println(m);
			double det = m.getDeterminant();
			double[][] temp = new double[2][2];
			temp[0][0] = A[1][1] * (1 / det);
			temp[0][1] = -A[0][1] * (1 / det);
			temp[1][0] = -A[1][0] * (1 / det);
			temp[1][1] = A[0][0] * (1 / det);
			return new Matrix(temp);

		case 3:
			//Inizializzo la matrice dei cofattori
			double[][] temp2 = new double[3][3];
			
			// Calcolo la matrice dei cofattori
			
			for(int i = 0; i < A.length; i++) {
				for(int j = 0; j < A[i].length; j++) {
					temp2[i][j] = Math.pow(-1, i+j+2) * det(2, m.getSubMatrix(i, j, A));
					temp2[i][j] = temp2[i][j] / det(A.length, A);
				}
			}
			
			return new Matrix(temp2).transpose();

		default:
			throw new Exception("Inverse of matrix 3x3 + not yet implemented");

		}
	}
	
	public Matrix transpose() throws Exception {
		
		double[][] temp = new double[A.length][A.length];
		
		if(!this.checkSquared()) throw new Exception("The matrix isn't squared");
		
		for(int i = 0; i < A.length; i++) {
			for(int j = 0; j < A[i].length; j++) {
				temp[i][j] = A[j][i];
			}
		}
		return new Matrix(temp);
	}
		
	private double[][] getSubMatrix(int i, int j, double[][] t) {
		
		//i � la riga, j � la colonna
		double[][] temp = new double[2][2];
		Queue<Double> queue = new LinkedList<>();
		
		for(int k = 0; k < t.length; k++) {
			for(int x = 0; x < t[i].length; x++) {
				if(x == j || k == i) continue;
				queue.offer(t[k][x]);
			}
		}
		
		for(int k = 0; k < 2; k++) {
			for(int x = 0; x < 2; x++) {
				temp[k][x] = queue.poll().doubleValue();
			}
		}
		
		return temp;
	}

	public boolean checkSquared() {
		for (int i = 0; i < A.length; i++) {
			if (A.length != A[i].length)
				return false;
		}
		return true;
	}
	
	public static Matrix matrixProduct(Matrix B, Matrix C) throws Exception {
		
		double[][] result;
		
		int BRows = B.getMatrix().length;
		int BColumns = 0;
		int CRows = C.getMatrix().length;
		int CColumns = 0;
		
		int temp1 = B.getMatrix()[0].length;
		int temp2 = C.getMatrix()[0].length;
		
		//TODO: aggiungere un metodo che controlla le dimensioni
		for(int i = 0; i < BRows; i++) {
			BColumns = 0;
			for(int j = 0; j < B.getMatrix()[i].length; j++) {
				BColumns++;
			}
			if(temp1 != BColumns) throw new Exception("Matrix has different dimensions!");
		}
		
		for(int i = 0; i < CRows; i++) {
			CColumns = 0;
			for(int j = 0; j < C.getMatrix()[i].length; j++) {
				CColumns++;
				
			}
			if(temp2 != CColumns) throw new Exception("Matrix has different dimensions!");
		}
	
		if(!(BColumns == CRows)) throw new Exception("Cannot do matrix moltiplication");
		
		result = new double[BRows][CColumns];
		
		for (int i = 0; i < BRows; i++) {
			for (int j = 0; j < CColumns; j++) {
				for (int k = 0; k < CRows; k++) {
					result[i][j] = result[i][j] + B.getMatrix()[i][k] * C.getMatrix()[k][j];
				}
			}
		}
		
		return new Matrix(result);
	}
	
	public OPTResult testOpt() throws Exception {
		
		//B = [A1, A4, A5]
		//indexes = [1, 4 ,5]
		
		OPTResult result = null;
		
		//TODO:Aggiungere il check per verificare che sia rettangolare
		double[][] ctb = new double[1][A.length];
		System.out.println(A.length);
		
		//c � il vettori dei costi
		//devo ricavare cTb che ha valori non nulli negli indici di colonne 
		//di base
		for(int i = 0; i < ctb[0].length; i++) {
			ctb[0][i] = c[indexes[i]];
			//Setto i coefficienti in base e lascio a 0 quelli fuori base
		}
		
		Matrix ut = Matrix.matrixProduct(new Matrix(ctb), Matrix.reverseMatrix(this.B));
		System.out.println("\nUt: "+ut);	
		
		return result;
	}

	public static void main(String[] args) {
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
			
			double[][] A = {{-1,-1,2,1,0},{1,-2,1,0,1}};
			double[] c = {-3,2,4,0,0};
			double[][] B = {{-1,2},{-2,1}};
			int[] indexes = {2,3};
			Matrix m1 = new Matrix(A, c, B, indexes);
			m1.testOpt();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	class OPTResult {
		protected double[] c;
		protected boolean opt;
		
		public OPTResult(double[] c, boolean opt) {
			this.c = c;
			this.opt = opt;
		}
	}
}
