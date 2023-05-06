package simplex;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Simplex {

	private Matrix A, B, c;
	private int[] indexes;
	
	class OPTResult {
		private Matrix c;
		private boolean opt;
		
		public OPTResult(Matrix c, boolean opt) {
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

		// TODO:Aggiungere il check per verificare che sia rettangolare
		double[][] ctb = new double[1][A.getMatrix().length];

		// c è il vettori dei costi
		// devo ricavare cTb che ha valori non nulli negli indici di colonne
		// di base
		for (int i = 0; i < ctb[0].length; i++) {
			ctb[0][i] = c.getMatrix()[0][indexes[i]-1];
			// Setto i coefficienti in base e lascio a 0 quelli fuori base
		}

		Matrix ut = Matrix.matrixProduct(new Matrix(ctb), B.reverseMatrix());
		System.out.println("\nUt: " + ut);
		
		//Creo vettore dei costi ridotto
		double[][] cr = new double[1][A.getColumns()];
		List<Integer> indexesList  = Arrays.stream(indexes)
				.boxed()
				.collect(Collectors.toList());
		
		boolean opt = true;
		for(int i = 1; i < cr[0].length+1; i++) {
			if(!indexesList.contains(i)) {
				System.out.println(Matrix.matrixProduct(ut, A.selectColumn(i)));
				
				cr[0][i-1] = c.getMatrix()[0][i-1] - Matrix.matrixProduct(ut, A.selectColumn(i)).toValue();
				if(cr[0][i-1] < 0) opt = false;
			}
		}
		
		return new OPTResult(new Matrix(cr), opt);
	}

	public Matrix compute() throws Exception{
		
		OPTResult optimality = this.testOpt();

		if(optimality.opt) System.out.println("La soluzione è ottimale");
		else System.out.println("La soluzione non è ottimale");
		return null;
	}

}
