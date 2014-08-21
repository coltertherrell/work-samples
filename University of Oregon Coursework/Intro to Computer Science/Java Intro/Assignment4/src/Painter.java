import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Painter {
	

	public static void main(String [] args)
	{
		Drawer paintPanal = new Drawer();
		MainFrame buttons = new MainFrame(paintPanal);
		
		paintPanal.setBackground(Color.WHITE);
		paintPanal.setPreferredSize(new Dimension(600,600));
		buttons.add(paintPanal, BorderLayout.CENTER);
		
		buttons.add( new JLabel( "Click and Drag the mouse to draw"),
				BorderLayout.SOUTH);
		
		buttons.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		buttons.setSize(800,600);
		buttons.setVisible(true);
		
	}

}
