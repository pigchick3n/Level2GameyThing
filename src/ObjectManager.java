import java.awt.Graphics;


public class ObjectManager {
ShootyThing cannon;

long enemyTimer = 0;
int enemySpawnTime = 1000;
int score;
public ObjectManager(ShootyThing bob){
	cannon = bob;

	score = 0;
}


void draw(Graphics g) {
cannon.draw(g);


}
	
public int getScore() {
    return score;
}

}