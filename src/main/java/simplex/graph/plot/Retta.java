package simplex.graph.plot;

import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class Retta extends Funzione {
	private double coefficienteAngolare;
	private double termineNoto;

	public Retta(double coeffAng, double termineNoto, int numTacks) {
		this.coefficienteAngolare = coeffAng;
		this.termineNoto = termineNoto;
		this.calcolaPuntiFunzione(numTacks/2);
	}

	@Override
	public void calcolaPuntiFunzione(int numTacks) {
		for(int x = -numTacks; x<= numTacks; x++) {
			double y = (this.coefficienteAngolare*x)+this.termineNoto;
			this.punti.add(new Punto(x,y));
		}	
	}

	@Override
	public Shape drawFunction(int dimTacks) {
		Punto min = this.punti.get(0).toJavaCoordinates(dimTacks);
		Punto max = this.punti.get(this.punti.size()-1).toJavaCoordinates(dimTacks);
		Shape retta = new Line(min.getX(), min.getY(), max.getX(), max.getY());
		retta.setFill(this.getColorForFunzione());
		return retta;
	}

}
