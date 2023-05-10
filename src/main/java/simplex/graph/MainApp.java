package simplex.graph;

import javafx.application.Application;
import javafx.stage.Stage;
import simplex.graph.plot.Funzione;
import simplex.graph.plot.Piano;
import simplex.graph.plot.Retta;
import simplex.graph.view.GestoreViste;

public class MainApp extends Application {
	@Override
	public void start(Stage stage) {
		GestoreViste.getGestore().startApp(stage);
		Funzione retta = new Retta(1,0, Piano.getPiano().getNumTacks());
		Piano.getPiano().setFunzione(retta);
	}

	public static void main(String[] args) {
		launch();
	}

}