import java.awt.Color;
import java.awt.Graphics;

public class Button extends GameObject{

	public Button(int x, int y, int width, int height) {
		super(x,y,width,height);
}
	void update() {

	}

	void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect((int)x, (int)y, width, height);
	}
}
