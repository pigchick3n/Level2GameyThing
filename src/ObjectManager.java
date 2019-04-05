import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class ObjectManager {
ShootyThing cannon;

ArrayList<Projectile> projectiles;
long enemyTimer = 0;
int enemySpawnTime = 1000;
int score;
int crosshairX;
int crosshairY;
public ObjectManager(ShootyThing bob){
	cannon = bob;
	projectiles = new ArrayList<Projectile>();
	score = 0;
}

void update() {
	for (int i = 0; i < projectiles.size(); i++) {
		projectiles.get(i).update();
	}
	
}
void draw(Graphics g) {
cannon.draw(g);
for (int i = 0; i < projectiles.size(); i++) {
	projectiles.get(i).draw(g);
	
}
g.setColor(Color.RED);
g.fillRect(crosshairX, crosshairY, 10, 10);
}
void addProjectile(Projectile p) {
	projectiles.add(p);
}
	
public int getScore() {
    return score;
}
void setCrosshairPosition(double angle, Graphics g) {
	g.drawLine((int)cannon.x + 20, (int)cannon.y, (int)(200*Math.sin(-cannon.getAngle())), (int) (200*Math.cos(cannon.getAngle()))); 
	
}
}