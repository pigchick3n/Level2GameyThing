
	import java.awt.Color;
	import java.awt.Graphics;

	public class Emu extends GameObject{
		int speed;
	public Emu(int x, int y, int width, int height) {
		super(x,y,width,height);
		speed=10;
	}
	void update() {
		super.update();
		y+=speed;
	}
	void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, width, height);
	}
	}


