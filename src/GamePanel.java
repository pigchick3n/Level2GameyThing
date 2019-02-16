import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Timer t;
	ShootyThing st;
	ObjectManager om;
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	int currentState = 0;
	Font titleFont;
	Font enterFont;



	public GamePanel() {
		t = new Timer(1000 / 60, this);
		st = new ShootyThing(725,850,50,50);
		om = new ObjectManager(st);
		titleFont = new Font("Arial", Font.PLAIN, 48);
		enterFont = new Font("Arial", Font.PLAIN, 25);

    }



	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (currentState == MENU_STATE) {
			updateMenuState();
		} else if (currentState == GAME_STATE) {
			updateGameState(5);
		} else if (currentState == END_STATE) {
			updateEndState();
		}
		repaint();

	}

	void startGame() {
		t.start();
	}

	@Override

	public void paintComponent(Graphics g) {
		if (currentState == MENU_STATE) {
			drawMenuState(g);
		} else if (currentState == GAME_STATE) {
			drawGameState(g);
		} else if (currentState == END_STATE) {
			drawEndState(g);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("sup");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();
		// TODO Auto-generated method stub
		System.out.println(keycode);
		if ((keycode == 10) && (currentState == MENU_STATE)) {
			currentState = GAME_STATE;
		} else if ((keycode == 10) && (currentState == GAME_STATE)) {
			currentState = END_STATE;

		} else if ((keycode == 10) && (currentState == END_STATE)) {
			currentState = MENU_STATE;
			st = new ShootyThing(725,700,50,50);
			om = new ObjectManager(st);
		}
		
		if((currentState == GAME_STATE)&&(keycode == 37)) {
			updateGameState(1);
		}else if((currentState == GAME_STATE)&&(keycode == 39)) {
			updateGameState(2);
		}else if((currentState == GAME_STATE)&&(keycode == 38)) {
			updateGameState(3);
		}else if((currentState == GAME_STATE)&&(keycode == 40)) {
			updateGameState(4);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("oof");
	}

	void updateMenuState() {

	}

	void updateGameState(int arrow) {
		
		if(st.isAlive == false) {
			currentState = END_STATE;
		}
	}

	void updateEndState() {

	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, StuffThing.width, StuffThing.height);
		g.setColor(Color.YELLOW);
		g.setFont(titleFont);
		g.drawString("Shoot Stuff for money", 500, 200);
		g.setFont(enterFont);
		g.drawString("Press ENTER to start", 615, 350);

	}
	void drawGameState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, StuffThing.width, StuffThing.height);
		om.draw(g);
	}



	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, StuffThing.width, StuffThing.height);
		g.setColor(Color.BLACK);
		g.setFont(titleFont);
		g.drawString("GAME OVER", 100, 200);
		g.setFont(enterFont);
		g.drawString("You killed " + om.getScore() + " enemies", 120, 350);
		g.drawString("Press ENTER to start", 100, 600);
	}
}
