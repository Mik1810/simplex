package simplex;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Simplex {

	private Matrix A, B, c, b;
	private int[] indexes;
	private boolean illim, opt;

	class OPTResult {
		private Matrix c;
		private boolean opt;

		public OPTResult(Matrix c, boolean opt) {
			this.c = c;
			this.opt = opt;
		}
	}

	class IllimResult {
		private Matrix Ah;
		private boolean illim;

		public IllimResult(Matrix Ah, boolean illim) {
			this.Ah = Ah;
			this.illim = illim;
		}
	}

	public Simplex(Matrix A, Matrix B, Matrix c, int[] indexes, Matrix b) {
		this.A = A;
		this.B = B;
		this.c = c;
		this.indexes = indexes;
		this.b = b;
	}

	private OPTResult testOpt() throws Exception {

		// B = [A1, A4, A5]
		// indexes = [1, 4 ,5]

		// TODO:Aggiungere il check per verificare che sia rettangolare
		double[][] ctb = new double[1][A.getMatrix().length];

		// c � il vettori dei costi
		// devo ricavare cTb che ha valori non nulli negli indici di colonne
		// di base
		for (int i = 0; i < ctb[0].length; i++) {
			ctb[0][i] = c.getMatrix()[0][indexes[i] - 1];
			// Setto i coefficienti in base e lascio a 0 quelli fuori base
		}

		Matrix ut = Matrix.matrixProduct(new Matrix(ctb), B.reverseMatrix());
		System.out.println("\nUt: " + ut);

		// Creo vettore dei costi ridotto
		double[][] cr = new double[1][A.getColumns()];
		List<Integer> indexesList = Arrays.stream(indexes).boxed().collect(Collectors.toList());

		boolean opt = true;
		for (int i = 1; i < cr[0].length + 1; i++) {
			if (!indexesList.contains(i)) {

				cr[0][i - 1] = c.getMatrix()[0][i - 1] - Matrix.matrixProduct(ut, A.selectColumn(i)).toValue();
				if (cr[0][i - 1] < 0)
					opt = false;
			}
		}

		return new OPTResult(new Matrix(cr), opt);
	}

	private IllimResult testIllim(Matrix bRev, int h) throws Exception {

		boolean illim = true;

		// Colonna entrante
		Matrix joiningColumn = Matrix.matrixProduct(bRev, A.selectColumn(h + 1));

		// Prova
		//joiningColumn.getMatrix()[0][0] = -1.5;
		//joiningColumn.getMatrix()[1][0] = -1.5;

		for (int i = 0; i < joiningColumn.getRows(); i++) {
			// TODO: controllare se tutti gli aih devono essere positivi o ne basta uno
			// in modo tale che non tutti siano negativi
			if (joiningColumn.getMatrix()[i][0] > 0)
				illim = false;
		}

		System.out.println("Colonna entrante: " + joiningColumn);

		// System.out.println("Colonna entrante: "+joiningColumn);

		return new IllimResult(joiningColumn, illim);
	}

	public Matrix compute() throws Exception {

		// TODO: extend this loop
		while (!opt && !illim) {

			OPTResult optimality = this.testOpt();

			this.opt = optimality.opt;

			// Se la soluzione � ottima
			if (this.opt) {

				// Calcolo la soluzione ottima
				Matrix bs = Matrix.matrixProduct(B.reverseMatrix(), b.transpose());
				Matrix xstar = Matrix.matrixProduct(B.reverseMatrix(), bs);

				double[] xstarb = new double[A.getColumns()];

				for (int i = 0; i < xstar.getRows(); i++) {
					xstarb[indexes[i] - 1] = xstar.getMatrix()[i][0];
				}
				Matrix xstarbm = new Matrix(xstarb);
				xstarbm.print("x* : \n");
				return xstarbm;
			} else { // se la soluzione non � ottima

				// Cerco la variabile entrante
				int h = 0;
				for (h = 0; h < optimality.c.getMatrix()[0].length; h++) {
					if (optimality.c.getMatrix()[0][h] < 0)
						break;
				}

				IllimResult illimResult = this.testIllim(B.reverseMatrix(), h);

				if (illimResult.illim)
					throw new Exception("STOP: il problema � illimitato");
				else {
					
					//Inizializzo il vettore degli argomenti
					double[] temp = new double[B.getRows()];
					
					//Calcolo b barrato
					Matrix bsm = Matrix.matrixProduct(B.reverseMatrix(), b.transpose());
					double[][] bs = bsm.getMatrix();
					
					//Prendo Ah
					Matrix Ah = illimResult.Ah;
					
					//Calcolo il minimo argomento
					double min = Double.MAX_VALUE;
					for (int i = 0; i < B.getRows(); i++) {
						temp[i] = bs[i][0]/Ah.getMatrix()[i][0];
						if(temp[i] < min) min = temp[i];
					}
					
					//La colonna da sostituire si trova nella posizione t+1
					int t = Arrays.stream(temp)
							.boxed()
							.collect(Collectors.toList())
							.indexOf(min);
					
					//Scambio le colonne nella base	
					indexes[t] = h+1;
				}
			}

		} //end while
		return null;
	}

}
