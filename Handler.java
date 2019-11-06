package RaycastingPart2;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
//import javax.swing.JPanel;

public class Handler implements MouseMotionListener, KeyListener{
	
	private Canvas canvas;

	public Handler(Canvas c)
	{
		canvas = c;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {	
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		//canvas.originX = e.getX();       
		//canvas.originY = e.getY();
		//try
		//{
		//canvas.centerAngle = (int)Math.toDegrees(Math.atan((e.getY()-250)/(e.getX()-250)));
		//}
		//catch(Exception ex) {}
		//System.out.println(canvas.centerAngle);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_A)
		{
			canvas.originX-=3;
		}
		else if(arg0.getKeyCode() == KeyEvent.VK_D)
		{
			canvas.originX+=3;
		}
		else if(arg0.getKeyCode() == KeyEvent.VK_W)
		{
			//Point directionVector = canvas.getDirectionVector(5);
			//canvas.originX = canvas.originX + (int)directionVector.getX();
			//canvas.originY = canvas.originY + (int)directionVector.getY();
			canvas.originY-=3;
		}
		else if(arg0.getKeyCode() == KeyEvent.VK_S)
		{
			//Point directionVector = canvas.getDirectionVector(5);
			//canvas.originX = canvas.originX - (int)directionVector.getX();
			//canvas.originY = canvas.originY - (int)directionVector.getY();
			canvas.originY+=3;
		}
		else if(arg0.getKeyCode() == KeyEvent.VK_O)
		{
			canvas.centerAngle-=3;
		}
		else if(arg0.getKeyCode() == KeyEvent.VK_P)
		{
			canvas.centerAngle+=3;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
