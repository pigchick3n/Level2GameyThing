import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	ShootyThing cannon;
	boolean isShooting;

	ArrayList<Projectile> projectiles;
	ArrayList<Emu> emus;
	long enemyTimer = 0;
	int enemySpawnTime = 1000;
	int score;
	int crosshairX;
	int crosshairY;


	public ObjectManager(ShootyThing bob) {
		cannon = bob;
		projectiles = new ArrayList<Projectile>();
		emus = new ArrayList<Emu>();
		score = 0;
		isShooting = false;
	}

	void update() {
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
		if(isShooting) {
			Projectile p = new Projectile((int)cannon.x + 20, (int)cannon.y, 10, 10, cannon.angle);
			projectiles.add(p);
		}
		
		cannon.update();
		for (int i = 0; i < emus.size(); i++) {
			emus.get(i).update();
		}
	}

	void draw(Graphics g) {
		cannon.draw(g);
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).draw(g);
		}
		for (int i = 0; i < emus.size(); i++) {
			emus.get(i).draw(g);
		}

		g.setColor(Color.RED);
		g.fillRect(crosshairX, crosshairY, 10, 10);
	}

	void addProjectile(boolean b) {
		isShooting = b;
	}

	void addEmu(Emu a) {
		emus.add(a);
	}

	void manageEnemies() {
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
			addEmu(new Emu(new Random().nextInt(StuffThing.width), 0, 50, 50));
			enemyTimer = System.currentTimeMillis();     
		}
		
	}

	public int getScore() {
		return score;
	}

	void setCrosshairPosition(double angle, Graphics g) {
		int endX = (int) (cannon.x + 25 + (200 * Math.sin(-cannon.getAngle())));
		int endY = (int) (cannon.y + (200 * Math.cos(cannon.getAngle())));
		g.drawLine((int) cannon.x + 25, (int) cannon.y, endX, endY);

	}
}