import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.awt.Color;

public class Drawer extends JPanel{
	
	private static Color curColor = Color.BLACK;
	private static int curSize = 4;
	private static ArrayList<Points> points = new ArrayList<Points>();
	
	public Drawer()
	{
		addMouseMotionListener
		(
				new MouseMotionAdapter()
				{
					public void mouseDragged( MouseEvent event )
					{
						
							points.add(new Points(event.getPoint(), curColor, curSize));
							repaint();
						
					}

				});
		
	}
	
		

	public static void setColor(Color color)
	{
		curColor = color;
	}
	public static void setSize(int size)
	{
		curSize = size;
	}
	public void reset()
	{
		points = new ArrayList<Points>();
		repaint();
	}
	
	
	public void paintComponent( Graphics g)
	{
		super.paintComponent( g );
		
		
		for (Points p : points)
		{
			g.setColor(p.color);
			g.fillOval(p.xPosition, p.yPosition, p.size, p.size);
		}

	}
	
	
}
