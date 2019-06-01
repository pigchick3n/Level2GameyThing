import java.awt.Color;
import java.awt.Graphics;

public class Upgrades extends GameObject {
int x;
int y;
int width;
int height;
		public Upgrades(int x, int y, int width, int height) {
			super(x, y, width, height);
		
		}

	 void draw(Graphics g) {
		 g.setColor(Color.GRAY);
	        g.fillRect((int)x, (int)y, width, height);
	     
	 }
	 
	 
	 void update() {
	
	 }

	 }


