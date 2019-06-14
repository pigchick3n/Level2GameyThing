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
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Timer t;
	Australian au;
	StuffThing st;
	ObjectManager om;

	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	int currentState = 0;
	Font titleFont;
	Font enterFont;
	double turnSpeed = 0.08;
	JButton rof;
	int rofTracker;
	

	public GamePanel() {
		t = new Timer(1000 / 60, this);
		au = new Australian(725, 850, 50, 50);
		om = new ObjectManager(au);
		titleFont = new Font("Arial", Font.PLAIN, 48);
		enterFont = new Font("Arial", Font.PLAIN, 25);
		rof = new JButton();
		st.gp.add(rof);
		rof.setLocation(500, 500);
		rof.setVisible(true);
		rof.addActionListener(this);
		
		rofTracker=0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		if(e.getSource().equals (rof) && om.emubucks>=100) {
			om.emubucks-=100;
			rofTracker+=5;
			
		}
		if (currentState == MENU_STATE) {
			updateMenuState();
		} else if (currentState == GAME_STATE) {
			updateGameState();
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

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();
		System.out.println(keycode);
		// TODO Auto-generated method stub
		if ((keycode == 10) && (currentState == MENU_STATE)) {
			currentState = GAME_STATE;
		} else if ((keycode == 10) && (currentState == GAME_STATE)) {
			currentState = END_STATE;

		} else if ((keycode == 10) && (currentState == END_STATE)) {
			currentState = MENU_STATE;
			au = new Australian(725, 850, 50, 50);
	
			om = new ObjectManager(au);
		}

		if ((currentState == GAME_STATE) && (keycode == 32)) {
			om.addProjectile(true);
		}
		if (keycode == 37) {
			au.turn(-turnSpeed);

		}
		if (keycode == 39) {
			au.turn(turnSpeed);

		}
		if (keycode == 16) {
			turnSpeed = 0.02;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int keycode = e.getKeyCode();
		if (keycode == 37) {
			au.turn(0);

		}
		if (keycode == 39) {
			au.turn(0);

		}
		if ((currentState == GAME_STATE) && (keycode == 32)) {
			om.addProjectile(false);
		}
		if (keycode == 16) {
			turnSpeed = 0.08;
		}
	}

	void updateMenuState() {

	}

	void updateGameState() {

		if (au.isAlive == false) {
			currentState = END_STATE;
		}
		om.update();
		om.manageEnemies();
		om.checkCollision();
		om.purgeObjects();
		if (ObjectManager.lives == 0) {
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
		g.drawString("Survive the emu invasion", 600, 200);
		g.setFont(enterFont);
		g.drawString("Press ENTER to start", 800, 350);

	}

	void drawGameState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, StuffThing.width, StuffThing.height);
		om.draw(g);
		om.setCrosshairPosition(au.angle, g);
		g.setColor(Color.WHITE);
		g.setFont(enterFont);
		g.drawString("You killed " + om.getScore() + " enemies", 1, 20);
		g.drawString("You have " + ObjectManager.lives + " lives left", 300, 20);
		g.drawString("You have " + ObjectManager.emubucks + " emubucks", 600, 20);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, StuffThing.width, StuffThing.height);
		g.setColor(Color.BLACK);
		g.setFont(titleFont);
		g.drawString("GAME OVER", 100, 200);
		g.setFont(enterFont);
		g.drawString("You killed " + om.getScore() + " enemies", 120, 350);
		g.drawString("Press ENTER to restart", 800, 350);
	}
}
