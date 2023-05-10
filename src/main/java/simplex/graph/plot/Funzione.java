package simplex.graph.plot;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public abstract class Funzione implements Drawable {
	protected List<Punto> punti = new ArrayList<>();
	protected final static double INTERVAL = 0.1f;
	public abstract void calcolaPuntiFunzione(int numTacks);
	protected Color getColorForFunzione() {
		return Color.color(Math.random(), Math.random(), Math.random()).darker();
	}
	@Override
	public String toString() {
		String result = "";
		for(int i = 0 ; i < punti.size() ; i++)
			result += punti.get(i)+"\n";
		return result;
	}
}
