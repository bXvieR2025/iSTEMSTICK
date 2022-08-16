package main;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Controller {

	public static JFrame frame;
	public static int accuracy = 10;
	public static final int Size = 600;

	public static void main(String[] args)
	{
		CanvasRenderer game = new CanvasRenderer();
	    Dimension size = new Dimension(Size, Size);
	    game.setPreferredSize(size);
	    game.setMaximumSize(size);
	    game.setMinimumSize(size);
	
	    frame = new JFrame();
	    frame.add(game);
	    frame.pack();
	    frame.setResizable(false);
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
		new Clock();
	}

}
