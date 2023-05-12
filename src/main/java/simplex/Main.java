package simplex;

import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import simplex.algorithm.Matrix;
import simplex.algorithm.Simplex;
import simplex.graph.plot.Piano;
import simplex.graph.plot.Punto;
import simplex.graph.plot.Vettore;
import simplex.graph.view.GestoreViste;

public class Main extends Application {

	@Override
	public void start(Stage stage) {
		new Main().run();
		new Main().run2();
		
		GestoreViste.getGestore().startApp(stage);
		//Funzione retta = new Retta(1, 0, Piano.getPiano().getNumTacks());
		//Piano.getPiano().setFunzione(retta);
		//Piano.getPiano().setPunto(new Punto(10, 20).toJavaCoordinates(Piano.getPiano().getNumTacks()));
	}

	public static void main(String[] args) {
		launch();
	}

	private void run2() {
		
	}

	private void run() {
		try {

			// TEST 1
			double[][] A = {{ -1, -1, 2, 1}, 
							{ 1, -2, 1, 0}};

			double[] c = { -3, 2, 4, 0, 0 };

			double[][] B = { { -1, 2 }, { -2, 1 } };

			int[] indexes = { 2, 3 };

			double[] b = { 1, -1 };

			//new Simplex(new Matrix(A), new Matrix(c), new Matrix(b), indexes).compute();

			// TEST 2
			double[][] A2 = {{ 1, 1, 1, 0, 0, 0 }, 
							{ 1, 0, 0, 1, 0, 0 }, 
							{ 0, 1, 0, 0, 1, 0 }, 
							{ 1, 2, 0, 0, 0, 1 } };

			double[] c2 = { -3, -5, 0, 0, 0, 0 };

			int[] indexes2 = { 3, 4, 5, 6 };

			double[] b2 = { 12, 10, 6, 16 };
			
			Piano.getPiano().setDimTacks(25);
			Matrix solution = new Simplex(A2, c2, b2, indexes2).compute();
			Punto p = new Punto(-3,-5);
			Piano.getPiano().setVettore(new Vettore(Piano.ORIGINE, p.toJavaCoordinates(25), Color.BLUE));
			//Devo annullare k vriabili dove k sono le colonne di base
			
			//esempio
			/*
			double[][] temp1 = {{1,0,0,0},
								{0,1,0,0},
								{1,0,1,0},
								{2,0,0,1}};
			double[] temp2 = {4,3};
			
			
			double[][] k = {{1,3,1,0},
							{4,1,0,1}};
			double[] bk = {4,3};
			//Matrix base = Simplex.getPoints(new Matrix(A2), indexes2, new Matrix(b2));
			new Matrix(A).sliceColumns(1, 4).print("Matrice senza righe: \n");;
			
			
			//Mi serve un metodo che rimuova determinate colonne da A
			Matrix[] basi = new Matrix[4];
			for(int i = 1; i < basi.length+1; i++) {
				if(i+1 > basi.length) {
					basi[i-1] = new Matrix(k).sliceColumns(1, i);
				} else {
					basi[i-1] = new Matrix(k).sliceColumns(i, i+1);
				}
			}
			System.out.println(basi.length);
			for(Matrix m : basi) {
				Matrix solution1 = LinearSystem.solve(m, new Matrix(bk));
				solution1.print("Soluzione: \n");
				Piano.getPiano().setPunto(LinearSystem.toPoint(solution1.transpose()));
			}*/
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}