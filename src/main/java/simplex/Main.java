package simplex;

import javafx.application.Application;
import javafx.stage.Stage;
import simplex.algorithm.Simplex;
import simplex.graph.plot.Funzione;
import simplex.graph.plot.Piano;
import simplex.graph.plot.Retta;
import simplex.graph.view.GestoreViste;

public class Main extends Application {

	@Override
	public void start(Stage stage) {
		new Main().run();
		new Main().run2();
		
		GestoreViste.getGestore().startApp(stage);
		Funzione retta = new Retta(1, 0, Piano.getPiano().getNumTacks());
		Piano.getPiano().setFunzione(retta);
		
	}

	public static void main(String[] args) {
		launch();
	}

	private void run2() {

	}

	private void run() {
		try {

			// TEST 1
			double[][] A = { { -1, -1, 2, 1, 0 }, { 1, -2, 1, 0, 1 } };

			double[] c = { -3, 2, 4, 0, 0 };

			double[][] B = { { -1, 2 }, { -2, 1 } };

			int[] indexes = { 2, 3 };

			double[] b = { 1, -1 };

			// new Simplex(new Matrix(A), new Matrix(c), new Matrix(b), indexes).compute();

			// TEST 2
			double[][] A2 = { { 1, 1, 1, 0, 0, 0 }, { 1, 0, 0, 1, 0, 0 }, { 0, 1, 0, 0, 1, 0 }, { 1, 2, 0, 0, 0, 1 } };

			double[] c2 = { -3, -5, 0, 0, 0, 0 };

			int[] indexes2 = { 3, 4, 5, 6 };

			double[] b2 = { 12, 10, 6, 16 };

			new Simplex(A2, c2, b2, indexes2).compute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}