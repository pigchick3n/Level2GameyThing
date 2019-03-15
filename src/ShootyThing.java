	import java.awt.Color;
import java.awt.Graphics;

	public class ShootyThing  extends GameObject{
	int angle;
	int speed;
		public ShootyThing(int x, int y, int width, int height) {
			super(x, y, width, height);
			// TODO Auto-generated constructor stub
			speed = 20;
			angle = 0;
		}

	 void draw(Graphics g) {
		 g.setColor(Color.BLUE);
	        g.fillRect(x, y, width, height);
	     
	 }
	 void turn(double deg) {
		 this.angle += deg;
	 }
	}


