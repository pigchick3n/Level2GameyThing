import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Enemus {


		JFrame frame;
		
		static int height = 900;
		static int width = 1800;
		GamePanel gp;
	public static void main(String[] args) {
		Enemus li = new Enemus();
		li.setup();
		System.out.println("sup");
	}
	public Enemus(){
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
		
	//bruh
		gp.startGame();
	}
	
	}   



