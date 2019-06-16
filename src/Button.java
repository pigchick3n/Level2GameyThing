import java.awt.Color;
import java.awt.Graphics;

public class Button extends GameObject{
	int value;
	public Button(int x, int y, int width, int height) {
		super(x,y,width,height);
		value= 1;
}
	void update() {

	}
	void buy() {
		if(ObjectManager.emubucks>=100) {
			value++;
			System.out.println(value);
			ObjectManager.emubucks-=100;
		}
		
	}
	void draw(Graphics g) {
		
		g.setColor(Color.WHITE);
		g.fillRect((int)x, (int)y, width, height);
		super.draw(g);
	}
}
