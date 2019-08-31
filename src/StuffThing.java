	import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;

public class StuffThing {


		JFrame frame;
		
		static int height = 900;
		static int width = 1800;
		GamePanel gp;
	public static void main(String[] args) {
		StuffThing li = new StuffThing();
		li.setup();
	}
	public StuffThing(){
		frame = new JFrame();
		gp = new GamePanel();
	}
	void draw(Graphics g) {
		 g.drawImage(GamePanel.backgroundImg, 0,0, width, height, null);
	}
	void setup() {
		frame.addKeyListener(gp);
		frame.addMouseListener(gp);
		frame.add(gp);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setPreferredSize(new Dimension(width, height));
		frame.pack();
		
		gp.startGame();
	}
	}   



