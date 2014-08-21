import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainFrame extends JFrame implements ActionListener 
{
	private JButton blueButton;
	private JButton redButton;
	private JButton blackButton;
	private JButton greenButton;
	private JButton smallButton;
	private JButton medButton;
	private JButton largeButton;
	private JButton clearButton;
	private Drawer  draw;
	//private JLabel  label;
	
	public MainFrame(Drawer x) 
	{
		super("Drawing Application");
		draw = x;
		
		blueButton = new JButton("Blue");
		blueButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				Drawer.setColor(Color.BLUE);
			}
		});
		
		redButton = new JButton("Red");
		redButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				Drawer.setColor(Color.RED);
			}
		});
		
		blackButton = new JButton("Black");
		blackButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				Drawer.setColor(Color.BLACK);
			}
		});
		
		greenButton = new JButton("Green");
		greenButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				Drawer.setColor(Color.GREEN);
			}
		});
		
		smallButton = new JButton("Small");
		smallButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				Drawer.setSize(4);
			}
		});
		
		medButton = new JButton("Medium");
		medButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				Drawer.setSize(10);
			}
		});
		
		largeButton = new JButton("Large");
		largeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				Drawer.setSize(20);
			}
		});
		
		clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				draw.reset();
			}
		});
		
		
		//label = new JLabel();
		
		JPanel colorPanel = new JPanel(new FlowLayout());
		JPanel utlPanel = new JPanel();
		utlPanel.setLayout(new GridLayout(4,1));
		colorPanel.add(blueButton);
		colorPanel.add(redButton);
		colorPanel.add(blackButton);
		colorPanel.add(greenButton);
		utlPanel.add(smallButton);
		utlPanel.add(medButton);
		utlPanel.add(largeButton);
		utlPanel.add(clearButton);
		//panel.add(label);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		add(colorPanel, BorderLayout.NORTH);
		add(utlPanel, BorderLayout.WEST);
		pack();
		setVisible(true);
		
	
		}
	public static void main(String[] args) {
		new MainFrame(null);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
