package RaycastingPart2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Canvas extends JPanel{
	private List<Boundary> boundaries;
	private List<Ray> rays;
	private JFrame frame;
	
	public int originX = 250;
	public int originY = 250;
	
	public int pov = 40;
	public int centerAngle = 0;
	
	public void paint(Graphics g)
	{
		g.setColor(Color.BLACK); //Draw Background
		g.fillRect(0,0,frame.getWidth(), frame.getHeight());
		
		for(Boundary b: boundaries) //Draw all boundaries
		{
			g.setColor(b.lineColor);
			g.drawLine((int)b.ptA.getX(), (int)b.ptA.getY(), (int)b.ptB.getX(), (int)b.ptB.getY());
		}
		
		for(Ray r: rays) //Draw all rays
		{
			g.setColor(Color.WHITE);
			g.drawLine((int)r.tail.getX(), (int)r.tail.getY(), (int)r.head.getX(), (int)r.head.getY());
		}
	}
	
	public Canvas(List<Boundary> b, List<Ray> r)
	{
		boundaries = b;
		rays = r;
	}
	
	public static void main(String[] args)
	{		
		List<Boundary> tempLine = new ArrayList<>();
		List<Ray> tempRay = new ArrayList<>();
		new Canvas(tempLine, tempRay).go();
	}
	
	public void go()
	{
		frame = new JFrame("Raycasting!");
		frame.add(this);
		frame.setSize(500,500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.addMouseMotionListener(new Handler(this));
		frame.addKeyListener(new Handler(this));
		
		List<Boundary> lines = new ArrayList<>();		
		lines.add(new Boundary((int)(500*Math.random()), (int)(500*Math.random()), (int)(500*Math.random()), (int)(500*Math.random())));
		lines.add(new Boundary((int)(500*Math.random()), (int)(500*Math.random()), (int)(500*Math.random()), (int)(500*Math.random())));
		lines.add(new Boundary((int)(500*Math.random()), (int)(500*Math.random()), (int)(500*Math.random()), (int)(500*Math.random())));
		lines.add(new Boundary((int)(500*Math.random()), (int)(500*Math.random()), (int)(500*Math.random()), (int)(500*Math.random())));
		lines.add(new Boundary((int)(500*Math.random()), (int)(500*Math.random()), (int)(500*Math.random()), (int)(500*Math.random())));
		
		boundaries = lines;
		
		while(true) //Setup goes here, may change this later.
		{	
			List<Ray> r1 = new ArrayList<>();
			for(int i = centerAngle-(pov/2); i < centerAngle+(pov/2); i+=1)
			{
				Ray newRay = new Ray(originX, originY, i);
				newRay.cast(lines);
				r1.add(newRay);
			}
			
			rays = r1;
			
			repaint();
		}
	}
	
}
