import java.util.LinkedList;
import java.util.Queue;

public class Matrix {

	private double[][] A;

	public Matrix(double[][] A) {
		this.A = A;
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

	public double det(int n, double matrix[][]) throws Exception {

		if (!this.checkSquared())
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

	public void reverseMatrix() throws Exception {

		if (!this.checkSquared())
			throw new Exception("The matrix isn't squared");
		
		switch (A.length) {
		case 1:
			return;

		case 2:
			double det = this.getDeterminant();
			double[][] temp = new double[2][2];
			temp[1][1] = A[2][2] * (1 / det);
			temp[1][2] = -A[1][2] * (1 / det);
			temp[2][1] = -A[2][1] * (1 / det);
			temp[2][2] = A[1][1] * (1 / det);
			this.A = temp;
			break;

		case 3:
			//Inizializzo la matrice dei cofattori
			double[][] temp2 = new double[3][3];
			
			// Calcolo la matrice dei cofattori
			
			for(int i = 0; i < A.length; i++) {
				for(int j = 0; j < A[i].length; j++) {
					temp2[i][j] = Math.pow(-1, i+j+2) * det(2, this.getSubMatrix(i, j, A));
					temp2[i][j] = temp2[i][j] / det(A.length, A);
				}
			}
			
			A = temp2;
			this.transpose();
			break;

		default:
			throw new Exception("Inverse of matrix 3x3 + not yet implemented");

		}
	}
	
	public void transpose() throws Exception {
		
		double[][] temp = new double[A.length][A.length];
		
		if(!this.checkSquared()) throw new Exception("The matrix isn't squared");
		
		for(int i = 0; i < A.length; i++) {
			for(int j = 0; j < A[i].length; j++) {
				temp[i][j] = A[j][i];
			}
		}
		A = temp;
	}
		
	public double[][] getSubMatrix(int i, int j, double[][] t) {
		
		//i è la riga, j è la colonna
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

	public static void main(String[] args) {
		try {
			double[][] a = { { 2, 0, 0}, { 1, 1, 0}, { 2, 0, 1} };
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
			m.reverseMatrix();
			System.out.println("\nMatrice inversa:");
			System.out.println(m);
			
			//System.out.println("Matrice trasposta: ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
