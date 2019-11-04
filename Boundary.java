package RaycastingPart2;

import java.awt.Color;
import java.awt.Point;

public class Boundary {
	public Point ptA;
	public Point ptB;
	public Color lineColor = Color.WHITE;
	
	public Boundary(int x1, int y1, int x2, int y2)
	{
		ptA = new Point(x1, y1);
		ptB = new Point(x2, y2);
	}
	
	public Boundary(int x1, int y1, int x2, int y2, Color c)
	{
		ptA = new Point(x1, y1);
		ptB = new Point(x2, y2);
		lineColor = c;
	}
}
