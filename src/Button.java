import java.awt.Color;
import java.awt.Graphics;

public class Button extends GameObject{
	int value;
	String name;
	String key;
	int textOffset;
	public Button(int x, int y, int width, int height, String name, String key) {
		super(x,y,width,height);
		value= 1;
		this.name=name;
		this.key = key;
		textOffset= name.length()*6;
		
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
		
		g.drawString(name,(int) x-textOffset,(int)y-4);
		super.draw(g);
		
		g.drawString(key, (int)x+15, (int)y+20);
		g.drawString(value + "", (int)x + 15,(int)y +40);
	}
}
