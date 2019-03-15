import java.awt.Color;
import java.awt.Graphics;

public class Projectile extends GameObject {
	int speed;
	double speedY;
	double speedX;
int angle;

	public Projectile(int x, int y, int width, int height, int angle) {
		super(x, y, width, height);
		speed = 10;
		this.angle = angle;
		System.out.println(angle);
		speedY = speed * (1 - (Math.abs(angle / 90.0)));
		speedX = speed * (angle / 90.0);
	}

	void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
		 g.setColor(Color.RED);
	        g.fillRect(0,0, width, height);
	}

	void update() {
		super.update();

		y -= speedY;

		x -= speedX;
	}
}
