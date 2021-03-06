import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener {
	Timer t;
	public static ArrayList<Button> buttons;
	public static ArrayList<BufferedImage> emuRun;
	Australian au;
	Enemus st;
	ObjectManager om;

	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	final int WIN_STATE = 3;
	int currentState = 0;
	Font titleFont;
	Font enterFont;
	Font upgradeFont;
	double turnSpeed = 0.08;
	public static BufferedImage pancakeImg;
	public static BufferedImage australianImg;
	public static BufferedImage emuwinImg;
	public static BufferedImage backgroundImg;

	public GamePanel() {
		t = new Timer(1000 / 60, this);
		buttons = new ArrayList<Button>();
		emuRun = new ArrayList<BufferedImage>();
		buttons.add(new Button(1740, 20, 50, 50, "More Pancakes", "A"));
		buttons.add(new Button(1740, 100, 50, 50, "More Lives", "S"));
		buttons.add(new Button(1740, 180, 50, 50, "Tough Pancakes", "D"));
		au = new Australian(725, 850, 50, 50);
		om = new ObjectManager(au);
		loadEmuAnimations();
		titleFont = new Font("Arial", Font.PLAIN, 48);
		enterFont = new Font("Arial", Font.PLAIN, 25);
		upgradeFont = new Font("Arial", Font.PLAIN, 20);
		try {
			pancakeImg = ImageIO.read(this.getClass().getResourceAsStream("pancakes.png"));
		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}
		try {
			backgroundImg = ImageIO.read(this.getClass().getResourceAsStream("background.jpg"));
		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}
		try {
			emuwinImg = ImageIO.read(this.getClass().getResourceAsStream("coolemu.jpg"));
		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}
		try {
			australianImg = ImageIO.read(this.getClass().getResourceAsStream("australiansoldier.png"));
		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

	void loadEmuAnimations() {
		try {
			BufferedImage emuImg = ImageIO.read(this.getClass().getResourceAsStream("birdemuthing.png"));
			for (int i = 0; i < 6; i++) {
				emuRun.add(emuImg.getSubimage((emuImg.getWidth() / 12) * (i + 6), 0, (emuImg.getWidth() / 12),
						(emuImg.getHeight() / 8)));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (currentState == MENU_STATE) {
			updateMenuState();
		} else if (currentState == GAME_STATE) {
			updateGameState();
		} else if (currentState == END_STATE) {
			updateEndState();
	} else if (currentState == WIN_STATE) {
		updateWinState();
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
	} else if (currentState == WIN_STATE) {
		drawWinState(g);
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
			currentState = MENU_STATE;
			au = new Australian(725, 850, 50, 50);

			om = new ObjectManager(au);
		} else if ((keycode == 10) && (currentState == WIN_STATE)) {
			currentState = MENU_STATE;
			au = new Australian(725, 850, 50, 50);

			om = new ObjectManager(au);
		}

		if ((currentState == GAME_STATE) && (keycode == 32)) {
			om.addProjectile(true);
		}
		if (keycode == 37) {
			au.turn(-turnSpeed);

		}else if (keycode == 39) {
			au.turn(turnSpeed);

		}else if (keycode == 16) {
			turnSpeed = 0.02;
		}else if (keycode == 65) {
			buttons.get(0).buy();
		}else if (keycode == 83 && om.emubucks>=100) {
			buttons.get(1).buy();
			ObjectManager.lives++;
		}else if (keycode == 68) {
			buttons.get(2).buy();
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
		if(om.score == 725000) {
			currentState = WIN_STATE;
		}
	}

	void updateEndState() {
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).value = 0;
		}
	}
	void updateWinState() {
		
	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, Enemus.width, Enemus.height);
		g.setColor(Color.YELLOW);
		g.setFont(titleFont);
		g.drawString("THE EMUS ARE COMING.", 600, 200);
		g.setFont(enterFont);
		g.drawString("Press ENTER to start :)", 800, 350);
		g.drawString("Left and Right Arrow Keys to Aim :)", 730, 450);
		g.drawString("SHIFT to turn slower :)", 785, 550);
		g.drawString("HOLD DOWN!!! SPACE to shoot pancakes :)", 790, 650);
		g.drawString("To upgrade, click button or press corresponding key :)", 650, 750);
		g.drawString("All upgrades cost 100 emubucks and killing an emu gives 10 emubucks:)", 650, 800);
		g.drawString("For example, press A to increase rate of fire :)", 650, 850);

	}

	void drawGameState(Graphics g) {
		 g.drawImage(GamePanel.backgroundImg, 0,0, st.width, st.height, null);
		
		om.draw(g);
		om.setCrosshairPosition(au.angle, g);
		g.setColor(Color.WHITE);
		g.setFont(enterFont);
		g.drawString("You killed " + om.getScore() + " emus", 1, 20);
		g.drawString("You have " + ObjectManager.lives + " lives left", 300, 20);
		g.drawString("You have " + ObjectManager.emubucks + " emubucks", 600, 20);
		g.setColor(Color.YELLOW);
		g.setFont(upgradeFont);

		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).draw(g);
		}
		
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, Enemus.width, Enemus.height);
		g.setColor(Color.BLACK);
		g.setFont(titleFont);
		g.drawString("GAME OVER", 800, 200);
		g.setFont(enterFont);
		g.drawString("You killed " + om.getScore() + " enemies", 800, 300);
		g.drawString("You're still dead tho :(", 800, 320);
		g.drawString("Press ENTER to restart", 800, 400);
		g.drawImage(emuwinImg, 100,100, 300, 300, null);
	}
	void drawWinState(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(0, 0, Enemus.width, Enemus.height);
		g.setColor(Color.BLACK);
		g.setFont(titleFont);
		g.drawString("YOU KILLED EVERY SINGLE EMU", 300, 200);
		g.setFont(enterFont);
		g.drawString("You win now celebrate youve been playing this game for much too long", 800, 300);
		g.drawString("You did massacre a whole species tho :(", 800, 320);
		g.drawString("Press ENTER to restart", 800, 400);
	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		Rectangle clicked = new Rectangle(e.getX(), e.getY() - 26, 1, 1);
		System.out.println(e.getX() + " " + e.getY());
		for (int i = 0; i < buttons.size(); i++) {
			if (buttons.get(i).collisionBox.intersects(clicked)) {
				buttons.get(i).buy();
				if (i == 1 && om.emubucks >= 100) {
					ObjectManager.lives++;
				}
			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
