import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GameObject {
	double x;
	double y;
	int width;
	int height;
	boolean isAlive;
	Rectangle collisionBox;

	public GameObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		isAlive = true;
		collisionBox = new Rectangle(x, y, width, height);
	}

	void update() {
		collisionBox.setBounds((int)x, (int)y, width, height);
	}

	void draw(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height);
	}
}