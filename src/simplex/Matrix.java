package simplex;

import java.util.LinkedList;
import java.util.Queue;

public class Matrix {

	private double[][] M;
	private int rows, columns;
	// private double[] c;
	// private double[][] B;
	// private int[] indexes; indici delle colonne di A che formano N

	/**
	 * @param matrice di double
	 * @throws Exception
	 */
	public Matrix(double[][] A) throws Exception {
		this.M = A;
		this.rows = A.length;

		int temp1 = A[0].length;

		for (int i = 0; i < A.length; i++) {
			this.columns = 0;
			for (int j = 0; j < A[i].length; j++) {
				columns++;
			}
			if (temp1 != columns)
				throw new Exception("Matrix has different dimensions!");
		}
	}

	/**
	 * @param vettore di double
	 */
	public Matrix(double[] A) {
		this.M = new double[1][A.length];
		this.rows = 1;
		this.columns = A.length;

		for (int i = 0; i < A.length; i++) {
			this.M[0][i] = A[i];
		}
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public double[][] getMatrix() {
		return M;
	}

	public double getDeterminant() throws Exception {
		if (!this.checkSquared())
			throw new Exception("The matrix isn't squared");
		return det(this.rows, this.M);
	}

	public double toValue() throws Exception {
		if (rows == 1 && columns == 1)
			return M[0][0];
		throw new Exception("Unable to extract value from a Matrix that isn't 1x1");
	}

	public Matrix selectColumn(int k) throws Exception {
		if (k > columns)
			throw new Exception("Unable to select the " + k + " column");

		double[][] temp = new double[rows][1];

		for (int i = 0; i < rows; i++) {
			temp[i][0] = M[i][k-1];
		}
		return new Matrix(temp);
	}

	@Override
	public String toString() {

		String result = "";
		for (int i = 0; i < M.length; i++) {
			String s = "";
			for (int j = 0; j < M[i].length; j++) {
				if (M[i][j] == -0.0)
					M[i][j] = 0.0;
				if (M[i][j] >= 0) {
					s = s + "  " + String.format("%.2f", M[i][j]); // per l'indentazione
				} else {
					s = s + " " + String.format("%.2f", M[i][j]);
				}
			}
			s = "[" + s + " ]\n"; // stampo le righe dell'array e poi le accorpo creando una matrice
			result = result + s;
		}
		return result;
	}

	public double det(int n, double matrix[][]) throws Exception {

		double det = 0;
		double[][] subMatrix = new double[n][n];

		if (n == 1)
			return matrix[0][0];

		if (n == 2)
			return (matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]);

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

	public Matrix reverseMatrix() throws Exception {

		if (!this.checkSquared())
			throw new Exception("The matrix isn't squared");

		switch (M.length) {
		case 1:
			return this;

		case 2:
			double det = this.getDeterminant();
			double[][] temp = new double[2][2];
			temp[0][0] = M[1][1] * (1 / det);
			temp[0][1] = -M[0][1] * (1 / det);
			temp[1][0] = -M[1][0] * (1 / det);
			temp[1][1] = M[0][0] * (1 / det);
			return new Matrix(temp);

		default:
			// Inizializzo la matrice dei cofattori
			double[][] temp2 = new double[rows][rows];

			// Calcolo la matrice dei cofattori

			double mDet = det(M.length, M);
			if (mDet == 0)
				throw new Exception("La matrice non è invertibile!");
			for (int i = 0; i < M.length; i++) {
				for (int j = 0; j < M[i].length; j++) {
					double[][] subMatrix = this.getSubMatrix(i, j, M);
					temp2[i][j] = Math.pow(-1, i + j + 2) * det(subMatrix.length, subMatrix);
					temp2[i][j] = temp2[i][j] / mDet;

				}
			}

			return new Matrix(temp2).transpose();

		/*
		 * default: throw new Exception("Inverse of matrix 3x3 + not yet implemented");
		 */
		}
	}
	
	public void print(String s) {
		System.out.println(s+this);
	}
	
	public Matrix transpose() throws Exception {

		double[][] temp = new double[this.columns][this.rows];

		for (int i = 0; i < this.columns; i++) {
			for (int j = 0; j < this.rows; j++) {
				temp[i][j] = M[j][i];
			}
		}
		return new Matrix(temp);
	}

	private double[][] getSubMatrix(int i, int j, double[][] t) {

		// i è la riga, j è la colonna
		double[][] temp = new double[t.length - 1][t.length - 1];
		Queue<Double> queue = new LinkedList<>();

		for (int k = 0; k < t.length; k++) {
			for (int x = 0; x < t[i].length; x++) {
				if (x == j || k == i)
					continue;
				queue.offer(t[k][x]);
			}
		}

		for (int k = 0; k < temp.length; k++) {
			for (int x = 0; x < temp.length; x++) {
				temp[k][x] = queue.poll().doubleValue();
			}
		}

		return temp;
	}

	public boolean checkSquared() {
		for (int i = 0; i < M.length; i++) {
			if (M.length != M[i].length)
				return false;
		}
		return true;
	}

	public Matrix multiplyMatrixFor(double k) throws Exception {

		double[][] temp = new double[rows][columns];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				temp[i][j] = this.M[i][j] * k;
			}
		}
		return new Matrix(temp);
	}

	public static Matrix addMatrix(Matrix A, Matrix B) throws Exception {

		if (A.rows != B.rows || A.columns != B.columns)
			throw new Exception("A and B don't have same dimensions!");

		double[][] temp = new double[A.rows][B.rows];

		for (int i = 0; i < A.rows; i++) {
			for (int j = 0; j < A.columns; j++) {
				temp[i][j] = A.M[i][j] + B.M[i][j];
			}
		}

		return new Matrix(temp);
	}

	public static Matrix subtructMatrix(Matrix A, Matrix B) throws Exception {

		return addMatrix(A, B.multiplyMatrixFor(-1));
	}

	public static Matrix matrixProduct(Matrix B, Matrix C) throws Exception {

		double[][] result;

		int BRows = B.getMatrix().length;
		int BColumns = 0;
		int CRows = C.getMatrix().length;
		int CColumns = 0;

		int temp1 = B.getMatrix()[0].length;
		int temp2 = C.getMatrix()[0].length;

		// TODO: aggiungere un metodo che controlla le dimensioni
		for (int i = 0; i < BRows; i++) {
			BColumns = 0;
			for (int j = 0; j < B.getMatrix()[i].length; j++) {
				BColumns++;
			}
			if (temp1 != BColumns)
				throw new Exception("Matrix has different dimensions!");
		}

		for (int i = 0; i < CRows; i++) {
			CColumns = 0;
			for (int j = 0; j < C.getMatrix()[i].length; j++) {
				CColumns++;

			}
			if (temp2 != CColumns)
				throw new Exception("Matrix has different dimensions!");
		}

		if (!(BColumns == CRows))
			throw new Exception("Cannot do matrix moltiplication");

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
}
