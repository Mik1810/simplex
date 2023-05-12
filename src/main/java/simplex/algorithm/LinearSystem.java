package simplex.algorithm;

import simplex.graph.plot.Piano;
import simplex.graph.plot.Punto;

public class LinearSystem {
	
	public static Matrix solve(Matrix A, Matrix b) throws Exception {
		
		//A.print("Matrice dei coefficienti: \n");
		
		Matrix amr = A.reverseMatrix();
		//amr.print("Matrice dei coefficienti inversa: \n");
		
		b = b.transpose();
		//b.print("Termini noti: \n");
		Matrix result = Matrix.matrixProduct(amr,b);
		return result;
	}
	
	public static Punto toPoint(Matrix m) {
		Punto p = new Punto(m.getMatrix()[0][0], m.getMatrix()[0][1]);
		return p;
	}
	
	public static Matrix solve(double[][] k, double[] w) throws Exception {
		
		Matrix A = new Matrix(k);
		Matrix b = new Matrix(w);
		//A.print("Matrice dei coefficienti: \n");
		
		Matrix amr = A.reverseMatrix();
		//amr.print("Matrice dei coefficienti inversa: \n");
		
		b = b.transpose();
		//b.print("Termini noti: \n");
		Matrix result = Matrix.matrixProduct(amr,b);
		return result;
	}

}
