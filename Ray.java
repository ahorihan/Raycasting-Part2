package RaycastingPart2;

import java.awt.Point;
import java.util.List;

public class Ray {
	public double degInRads;
	public Point head;
	public Point tail;
	
	public int minX;
	public int minY;
	
	public Ray(int t1, int t2, double deg)
	{
		degInRads = Math.toRadians(deg);
		tail = new Point(t1, t2);
		head = new Point((int)(2000*Math.cos(degInRads)), (int)(2000*Math.sin(degInRads))); //Pseudo infinite vector in one direction
		
		minX = (int)head.getX();
		minY = (int)head.getY();
	}
	
	public double getDist()
	{
		return tail.distance(head);
	}
	
	public void cast(List<Boundary> boundaries)
	{
		int x3 = (int)tail.getX();
		int y3 = (int)tail.getY();
		int x4 = (int)head.getX();
		int y4 = (int)head.getY();
		
		int x1;
		int y1;
		int x2;
		int y2;
		
		for(Boundary b: boundaries)
		{
			x1 = (int)b.ptA.getX();
			y1 = (int)b.ptA.getY();
			x2 = (int)b.ptB.getX();
			y2 = (int)b.ptB.getY();
			
			double denom = (x1-x2)*(y3-y4)-(y1-y2)*(x3-x4);
			double t;
			double u;
			
			if(denom != 0)
			{
				t = ((x1-x3)*(y3-y4)-(y1-y3)*(x3-x4))/denom;
				u = -1*(((x1-x2)*(y1-y3)-(y1-y2)*(x1-x3))/denom);
				
				//System.out.println("t: " + t);
				//System.out.println("u: " + u);
				
				int newHeadX;
				int newHeadY;
				
				if(t > 0 && t < 1 && u > 0)
				{
					newHeadX = (int)(((x1*y2-y1*x2)*(x3-x4)-(x1-x2)*(x3*y4-y3*x4))/denom);
					newHeadY = (int)(((x1*y2-y1*x2)*(y3-y4)-(y1-y2)*(x3*y4-y3*x4))/denom);
					
					//System.out.println(tail.distance(newHeadX, newHeadY));
					//System.out.println(tail.distance(head.getX(), head.getY()));
					
					if(tail.distance(newHeadX, newHeadY) < tail.distance(head.getX(), head.getY()))
					{
						minX = newHeadX;
						minY = newHeadY;
						head = new Point(minX, minY);
						//System.out.println(minX + ", " + minY);
					}
				}
			}
		}			
	}
}
