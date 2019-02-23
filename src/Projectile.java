import java.awt.Color;
import java.awt.Graphics;

public class Projectile extends GameObject{
	int speed;
 public Projectile(int x, int y, int width, int height) {
	super(x, y, width, height);
	speed = 10;
 }

	  void draw(Graphics g) {
		   g.setColor(Color.BLUE);
	        g.fillRect(x, y, width, height);
			 }
	  

void update(int arrow){
	 super.update();
if (arrow == 1) {
	x-=speed;
}else if (arrow == 2) {
	x+=speed;
}else if (arrow == 3) {
	y-=speed;
}else if (arrow == 4) {
	y+=speed;
}
}
}
