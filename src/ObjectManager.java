import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;

public class ObjectManager {
	Australian cannon;
	boolean isShooting;

	ArrayList<Projectile> projectiles;
	ArrayList<Emu> emus;
	long stage = 0;
	long enemyTimer = 0;
	int enemySpawnTime = 1000;
	long shootTimer = 1000;
	double shootySpawnTime = 250;
	int score;
	public static int emubucks;
	int crosshairX;
	int crosshairY;
	public static int lives;

	public ObjectManager(Australian bob) {
		cannon = bob;
		projectiles = new ArrayList<Projectile>();
		emus = new ArrayList<Emu>();

		score = 0;
		isShooting = false;
		lives = 5;
		emubucks = 10000000;
	}

	void buttonStats() {
		shootySpawnTime = 250 - 10 * GamePanel.buttons.get(0).value;

	}

	void update() {
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
		if (isShooting) {

			if (System.currentTimeMillis() - shootTimer >= shootySpawnTime) {
				Projectile p = new Projectile((int) cannon.x + 20, (int) cannon.y, 30, 30, cannon.angle);
				projectiles.add(p);
				shootTimer = System.currentTimeMillis();
			}
		}

		cannon.update();

		for (int i = 0; i < emus.size(); i++) {
			emus.get(i).update();
		}
		buttonStats();
	}

	void draw(Graphics g) {
		cannon.draw(g);
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).draw(g);
		}
		for (int i = 0; i < emus.size(); i++) {
			emus.get(i).draw(g);
		}

	}

	void addProjectile(boolean b) {
		isShooting = b;
	}

	void addEmu(Emu a) {
		emus.add(a);
	}

	void manageEnemies() {

		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
			addEmu(new Emu(new Random().nextInt(StuffThing.width - 300), 0, 50, 50));
			enemyTimer = System.currentTimeMillis();
			if (enemySpawnTime >=5) {
				enemySpawnTime -= 5;
			}
		}

	}

	void purgeObjects() {
		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isAlive == false) {
				projectiles.remove(i);
			}
		}
		for (int i = 0; i < emus.size(); i++) {
			if (emus.get(i).isAlive == false) {
				emus.remove(i);
			}

		}
	}

	void checkCollision() {

		for (Emu a : emus) {

			for (int i = 0; i < projectiles.size(); i++) {
				if (projectiles.get(i).collisionBox.intersects(a.collisionBox)) {
					projectiles.get(i).hit();
					if(projectiles.get(i).health<=0) {
					projectiles.get(i).isAlive = false;
					}
					a.isAlive = false;
					score += 1;
					emubucks += 10;
					System.out.println(score);
				}
			}

		}

	}

	public int getScore() {
		return score;
	}

	void setCrosshairPosition(double angle, Graphics g) {
		int endX = (int) (cannon.x + 25 + (2000 * Math.sin(-cannon.getAngle())));
		int endY = (int) (cannon.y + (2000 * Math.cos(cannon.getAngle())));
		g.setColor(Color.RED);
		g.drawLine((int) cannon.x + 25, (int) cannon.y, endX, endY);

	}
}
