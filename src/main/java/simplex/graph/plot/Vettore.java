package simplex.graph.plot;

import javafx.scene.effect.Light.Point;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class Vettore extends Path {
	private Punto firstPoint;
	private Punto secondPoint;
	
	private static final double defaultArrowHeadSize = 3.0;

	public Vettore(Punto p1, Punto p2, Color color) {
		super();
		if(p1 == null) return;
		firstPoint = p1;
		secondPoint = p2;
		strokeProperty().bind(fillProperty());
		setFill(color);
		this.setStrokeWidth(2);

		// Line
		getElements().add(new MoveTo(p1.getX(), p1.getY()));
		getElements().add(new LineTo(p2.getX(), p2.getY()));

		// ArrowHead
		double angle = Math.atan2((p2.getY() - p1.getY()), (p2.getX() - p1.getX())) - Math.PI / 2.0;
		double sin = Math.sin(angle);
		double cos = Math.cos(angle);
		// point1
		double x1 = (-1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * defaultArrowHeadSize + p2.getX();
		double y1 = (-1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * defaultArrowHeadSize + p2.getY();
		// point2
		double x2 = (1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * defaultArrowHeadSize + p2.getX();
		double y2 = (1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * defaultArrowHeadSize + p2.getY();

		getElements().add(new LineTo(x1, y1));
		getElements().add(new LineTo(x2, y2));
		getElements().add(new LineTo(p2.getX(), p2.getY()));
	}
	
	public Punto getFirstPoint() {
		return firstPoint;
	}
}
