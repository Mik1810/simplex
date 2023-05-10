package simplex.graph.plot;

import java.text.DecimalFormat;

public class Punto {
	private double x;
	private double y;
	DecimalFormat format = new DecimalFormat("#.00");
	public Punto() {
		this.x = 0;
		this.y = 0;
	}

	public Punto(double coordX, double coordY) {
		this.x = coordX;
		this.y = coordY;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public void setX(double coord) {
		this.x = coord;
	}

	public void setY(double coord) {
		this.y = coord;
	}
	
	public Punto toJavaCoordinates(int dimTacks) {
		Punto puntoJava = new Punto();
		puntoJava.setX(Piano.ORIGINE.getX()+(this.x*dimTacks));
		puntoJava.setY(Piano.ORIGINE.getY()-(this.y*dimTacks));
		return puntoJava;
	}

	@Override
	public String toString() {
		return "[x=" + x + ", y=" + y + "]";
	}
}
