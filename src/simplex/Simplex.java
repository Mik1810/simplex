package simplex;

public class Simplex {

	private Matrix A, B, c;
	private int[] indexes;
	
	class OPTResult {
		protected double[] c;
		protected boolean opt;
		
		public OPTResult(double[] c, boolean opt) {
			this.c = c;
			this.opt = opt;
		}
	}

	public Simplex(Matrix A, Matrix B, Matrix c, int[] indexes) {
		this.A = A;
		this.B = B;
		this.c = c;
		this.indexes = indexes;
	}
	
	private OPTResult testOpt() throws Exception {
		
		// B = [A1, A4, A5]
		// indexes = [1, 4 ,5]
		
		OPTResult result = null;

		// TODO:Aggiungere il check per verificare che sia rettangolare
		double[][] ctb = new double[1][A.getMatrix().length];
		System.out.println(A.getMatrix().length);

		// c è il vettori dei costi
		// devo ricavare cTb che ha valori non nulli negli indici di colonne
		// di base
		for (int i = 0; i < ctb[0].length; i++) {
			ctb[0][i] = c.getMatrix()[0][indexes[i]];
			// Setto i coefficienti in base e lascio a 0 quelli fuori base
		}

		Matrix ut = Matrix.matrixProduct(new Matrix(ctb), B.reverseMatrix());
		System.out.println("\nUt: " + ut);

		return result;
	}

	public Matrix compute() throws Exception{
		
		this.testOpt();

		return null;
	}

}
