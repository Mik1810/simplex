package simplex.graph.plot;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class Piano extends AnchorPane {
	private final static int DIMENSIONE = 600;
	private static int DIM_TACKS = 50;
	public final static Punto ORIGINE = new Punto(DIMENSIONE / 2, DIMENSIONE / 2);
	private Shape funzione;
	private int numTacks;
	private Line asseX;
	private Line asseY;
	private static Piano piano = new Piano();

	private Piano() {
		this.getStyleClass().add("piano");
		this.setMaxSize(DIMENSIONE, DIMENSIONE);
		this.setMinSize(DIMENSIONE, DIMENSIONE);
		this.numTacks = DIMENSIONE / DIM_TACKS;
		setAsseX();
		setAsseY();
	}

	public static Piano getPiano() {
		return piano;
	}

	private void setAsseX() {
		this.asseX = new Line(0, DIMENSIONE / 2, DIMENSIONE, DIMENSIONE / 2);
		this.asseX.getStyleClass().add("asse");
		this.getChildren().add(asseX);
		for (int i = 1; i <= this.numTacks / 2; i++) {
			Punto punto1InJava = new Punto(ORIGINE.getX() + (i * DIM_TACKS), ORIGINE.getY());
			Punto punto2InJava = new Punto(ORIGINE.getX() - (i * DIM_TACKS), ORIGINE.getY());
			Line tack1 = new Line(punto1InJava.getX(), punto1InJava.getY() - 5, punto1InJava.getX(),
					punto1InJava.getY() + 5);
			Line tack2 = new Line(punto2InJava.getX(), punto2InJava.getY() - 5, punto2InJava.getX(),
					punto2InJava.getY() + 5);
			Label num1 = new Label(String.valueOf(i));
			Label num2 = new Label(String.valueOf(-i));
			tack1.getStyleClass().add("tack");
			tack2.getStyleClass().add("tack");
			num1.getStyleClass().add("coordinata");
			num2.getStyleClass().add("coordinata");
			num1.setLayoutX(punto1InJava.getX());
			num2.setLayoutX(punto2InJava.getX() - 5);
			num1.setLayoutY(punto1InJava.getY());
			num2.setLayoutY(punto2InJava.getY());
			this.getChildren().addAll(tack1, tack2, num1, num2);
		}
	}

	private void setAsseY() {
		this.asseY = new Line(DIMENSIONE / 2, 0, DIMENSIONE / 2, DIMENSIONE);
		this.asseY.getStyleClass().add("asse");
		this.getChildren().add(asseY);
		for (int i = 1; i <= this.numTacks / 2; i++) {
			Punto punto1InJava = new Punto(ORIGINE.getX(), ORIGINE.getY() - (i * DIM_TACKS));
			Punto punto2InJava = new Punto(ORIGINE.getX(), ORIGINE.getY() + (i * DIM_TACKS));
			Line tack1 = new Line(punto1InJava.getX() - 5, punto1InJava.getY(), punto1InJava.getX() + 5,
					punto1InJava.getY());
			Line tack2 = new Line(punto2InJava.getX() - 5, punto2InJava.getY(), punto2InJava.getX() + 5,
					punto2InJava.getY());
			Label num1 = new Label(String.valueOf(i));
			Label num2 = new Label(String.valueOf(-i));
			tack1.getStyleClass().add("tack");
			tack2.getStyleClass().add("tack");
			num1.getStyleClass().add("coordinata");
			num2.getStyleClass().add("coordinata");
			num1.setLayoutX(punto1InJava.getX());
			num2.setLayoutX(punto2InJava.getX() + 5);
			num1.setLayoutY(punto1InJava.getY());
			num2.setLayoutY(punto2InJava.getY());
			this.getChildren().addAll(tack1, tack2, num1, num2);
		}
	}
	public void setDimTacks(int dimTacks) {
		DIM_TACKS = dimTacks;
		this.getChildren().clear();
		this.numTacks = DIMENSIONE / DIM_TACKS;
		setAsseY();
		setAsseX();
	}
	public void setFunzione(Funzione f) {
		this.getChildren().remove(this.funzione);
		this.funzione= f.drawFunction(DIM_TACKS);
		this.getChildren().add(this.funzione);
	}
	
	public void setVettore(Vettore v) {
		if(v == null) return;
		this.getChildren().add(v);
	}
	
	public void setPunto(Punto p, boolean opt) {
		Circle c = new Circle();
		c.setCenterX(p.getX());
		c.setCenterY(p.getY());
		c.setFill(Color.BLUE);
		c.setRadius(3);
		this.getChildren().add(c);
		Label l;
		if(opt) l = new Label("x*");
		else l = new Label("x");
		l.setTranslateX(p.getX()+5);
		l.setTranslateY(p.getY()-18);
		this.getChildren().add(l);
	}

	public int getNumTacks() {
		return this.numTacks;
	}
}
