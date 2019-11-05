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
	private List<Slice> slices;
	private JFrame frame;
	
	public int originX = 250;
	public int originY = 250;
	
	public int fov = 40;
	public int centerAngle = 0;
	
	private int width = fov*10;
	private int height = 500;
	
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
		
		if(slices != null && slices.size() > 0)
		{
			for(Slice s: slices) //Draw all slices
			{
				g.setColor(new Color(s.shading, s.shading, s.shading));
				int yOffset = (500-s.height)/2;
				g.fillRect(500 + (s.id*10), yOffset, 10, s.height);
			
			}
		}
	}
	
	public Canvas(List<Boundary> b, List<Ray> r, List<Slice> s)
	{
		boundaries = b;
		rays = r;
		slices = s;
	}
	
	public static void main(String[] args)
	{		
		List<Boundary> tempLine = new ArrayList<>();
		List<Ray> tempRay = new ArrayList<>();
		List<Slice> tempSlice = new ArrayList<>();
		new Canvas(tempLine, tempRay, tempSlice).go();
	}
	
	public void go()
	{
		frame = new JFrame("Raycasting!");
		frame.add(this);
		frame.setSize(500+width,500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.addMouseMotionListener(new Handler(this));
		frame.addKeyListener(new Handler(this));
		
		List<Boundary> lines = new ArrayList<>();		
		//lines.add(new Boundary((int)(500*Math.random()), (int)(500*Math.random()), (int)(500*Math.random()), (int)(500*Math.random())));
		//lines.add(new Boundary((int)(500*Math.random()), (int)(500*Math.random()), (int)(500*Math.random()), (int)(500*Math.random())));
		//lines.add(new Boundary((int)(500*Math.random()), (int)(500*Math.random()), (int)(500*Math.random()), (int)(500*Math.random())));
		//lines.add(new Boundary((int)(500*Math.random()), (int)(500*Math.random()), (int)(500*Math.random()), (int)(500*Math.random())));
		//lines.add(new Boundary((int)(500*Math.random()), (int)(500*Math.random()), (int)(500*Math.random()), (int)(500*Math.random())));
		
		lines.add(new Boundary(100, 100, 200, 100));
		lines.add(new Boundary(100, 100, 100, 200));
		lines.add(new Boundary(100, 200, 200, 200));
		lines.add(new Boundary(200, 100, 200, 200));
		
		lines.add(new Boundary(0, 0, 0, 500));
		lines.add(new Boundary(0, 0, 500, 0));
		lines.add(new Boundary(0, 500, 500, 500));
		lines.add(new Boundary(500, 0, 500, 500));
		
		boundaries = lines;
		
		while(true) //Setup goes here, may change this later.
		{	
			List<Ray> r1 = new ArrayList<>();
			for(int i = centerAngle-(fov/2); i < centerAngle+(fov/2); i+=1)
			{
				Ray newRay = new Ray(originX, originY, i);
				newRay.cast(lines);
				r1.add(newRay);
			}
			
			rays = r1;
			int index = 0;
			List<Slice> s1 = new ArrayList<>();
			for(Ray r: rays)
			{
				int shadeValue = (int)(255*(1-(r.getDist()/1000)));
				int heightValue = (int)(500*(1-(r.getDist()/1000))); //Play around with these values
				if(shadeValue <= 255 && shadeValue >= 0)
					s1.add(new Slice(index, shadeValue, heightValue));
				else
					System.out.println("ruh roh!");
				index++;
			}
			slices = s1;
			repaint();
		}
	}
	
}
