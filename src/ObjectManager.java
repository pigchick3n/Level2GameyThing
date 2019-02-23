import java.awt.Graphics;
import java.util.ArrayList;


public class ObjectManager {
ShootyThing cannon;
Projectile bullet;
ArrayList<Projectile> projectiles;
long enemyTimer = 0;
int enemySpawnTime = 1000;
int score;
public ObjectManager(ShootyThing bob){
	cannon = bob;
	projectiles = new ArrayList<Projectile>();
	score = 0;
}

void update(int arrow) {
	bullet.update(arrow);
	for (int i = 0; i < projectiles.size(); i++) {
		projectiles.get(i).update();
	}
	
}
void draw(Graphics g) {
cannon.draw(g);


}
	
public int getScore() {
    return score;
}

}