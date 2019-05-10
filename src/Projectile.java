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
		//speedY = speed * (1 - (Math.abs(angle / 90.0)));
		//speedX = speed * (angle / 90.0);
		speedX = speed*Math.sin(-angle);
		speedY = speed*Math.cos(angle);
	}
	void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect((int)x, (int)y, width, height);
	}
	void update() {
		super.update();

		y += speedY;

		x += speedX;
	}
}
