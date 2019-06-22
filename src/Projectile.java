import java.awt.Color;
import java.awt.Graphics;

public class Projectile extends GameObject {
	int speed;
	double speedY;
	double speedX;
double angle;

	public Projectile(int x, int y, int width, int height, double angle) {
		super(x, y, width, height);
		speed = 20;
		this.angle = angle;
		System.out.println(angle);
	
		speedX = speed*Math.sin(-angle);
		speedY = speed*Math.cos(angle);
	}
	void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawImage(GamePanel.pancakeImg, (int)x,(int) y, width, height, null);
	}
	void update() {
		super.update();

		y += speedY;

		x += speedX;
	}
}
