package wiiusej;

import java.awt.geom.Point2D;

public class Point2DInteger extends Point2D {
	private int x;
	private int y;
	
	public Point2DInteger(int xx, int yy) {
		super();
		setLocation(xx,yy);
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public void setLocation(double xx, double yy) {
		this.x = (int)xx;
		this.y = (int)yy;
	}
	
	@Override
	public String toString() {
		return "("+x+","+y+")";
	}

}
