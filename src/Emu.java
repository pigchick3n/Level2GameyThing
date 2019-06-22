
	import java.awt.Color;
	import java.awt.Graphics;

	public class Emu extends GameObject{
		int speed;
		int frameNum=0;
		long animationTimer;
		long animationCooldown=300;
	public Emu(int x, int y, int width, int height) {
		super(x,y,width,height);
		speed=2;
	}
	void update() {
		super.update();
		y+=speed;
		if(y>=900) {
		this.isAlive = false;
		ObjectManager.lives-=1;
		System.out.println(ObjectManager.lives);
		}
		if (System.currentTimeMillis() - animationTimer >= animationCooldown) {
			animationTimer = System.currentTimeMillis();
			frameNum++;
			if(frameNum >= GamePanel.emuRun.size()) {
				frameNum = 0;
			}
		}
	}
	void draw(Graphics g) {
		g.setColor(Color.red);
		g.drawImage(GamePanel.emuRun.get(frameNum),(int) x,(int) y, width, height,null);
	}
	
	}