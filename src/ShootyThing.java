	import java.awt.Color;
import java.awt.Graphics;

	public class ShootyThing  extends GameObject{
	double angle;
	int speed;
		public ShootyThing(int x, int y, int width, int height) {
			super(x, y, width, height);
			// TODO Auto-generated constructor stub
			speed = 20;
			angle = 0;
		}

	 void draw(Graphics g) {
		 g.setColor(Color.BLUE);
	        g.fillRect((int)x, (int)y, width, height);
	     
	 }
	 void turn(double deg) {
		 this.angle += deg;
	 }
	 double getAngle () {
		return angle;
		 
	 }
	}


