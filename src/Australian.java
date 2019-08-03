	import java.awt.Color;
import java.awt.Graphics;

	public class Australian  extends GameObject{
	double angle;
	double angleVelocity;
	int speed;
		public Australian(int x, int y, int width, int height) {
			super(x, y, width, height);
			// TODO Auto-generated constructor stub
			speed = 20;
			angle = Math.PI;
			angleVelocity = 0;
		}

	 void draw(Graphics g) {
		 g.setColor(Color.BLUE);
		 g.drawImage(GamePanel.australianImg, (int)x,(int) y, width, height, null);
	     
	 }
	 void turn(double deg) {
		 this.angleVelocity = deg;
	 }
	 void update() {
		 angle+=angleVelocity;
	 }
	 double getAngle () {
		return angle;
		 
	 }
	}


