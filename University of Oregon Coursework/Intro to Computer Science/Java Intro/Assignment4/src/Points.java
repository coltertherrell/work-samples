
import java.awt.Point;
import java.awt.Color;

public class Points {
	public int xPosition;
	public int yPosition;
	public Color color;
	public int size;
	
	public Points(Point pt,Color clr,int sz)
	{
		xPosition = pt.x;
		yPosition = pt.y;
		color = clr;
		size = sz;
		
	}

}
