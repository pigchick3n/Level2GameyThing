import java.awt.Graphics;
import java.util.ArrayList;


public class ObjectManager {
ShootyThing cannon;

ArrayList<Projectile> projectiles;
long enemyTimer = 0;
int enemySpawnTime = 1000;
int score;
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
}
void addProjectile(Projectile p) {
	projectiles.add(p);
}
	
public int getScore() {
    return score;
}

}