package vesper.zombie.simulation;

import java.awt.*;
import java.util.ArrayList;
    
class Board extends Canvas {
	
	private static final long serialVersionUID = 1L;
	private static final int boardWidth = ZombieSimulator.getBoardWidth();
	private static final int boardHeight = ZombieSimulator.getBoardHeight();
	private ArrayList<Humanoid> humanList = new ArrayList<Humanoid>();
	private ArrayList<Zombie> zombieList = new ArrayList<Zombie>();
	private int turn;
	
	public Board() {
		
	}

	void updateBoard(ArrayList<Humanoid> humanList, ArrayList<Zombie> zombieList, int turn) {
		this.humanList = humanList;
		this.zombieList = zombieList;
		this.turn = turn;
		//repaint();
		update(getGraphics());
	}
	
	public void update(Graphics g) {
		Graphics offgc;
		Image offscreen = null;

		// create the offscreen buffer and associated Graphics
		offscreen = createImage(boardWidth + 2, boardHeight + 2);
		offgc = offscreen.getGraphics();
		// clear the exposed area
		offgc.setColor(getBackground());
		offgc.fillRect(0, 0, boardWidth + 2, boardHeight + 2);
		offgc.setColor(getForeground());
		// do normal redraw
		paint(offgc);
		// transfer offscreen to window
		g.drawImage(offscreen, 0, 0, this);
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.ORANGE);
		g.drawString(String.valueOf(turn), 0, boardHeight);

		g.setColor(Color.WHITE);
		for (Humanoid human : humanList) {
			if (human instanceof Police) {
				g.setColor(Color.BLUE);
				g.fillOval(human.getX(), human.getY(), 2, 2);
				g.setColor(Color.RED);
			} else {
				g.fillOval(human.getX(), human.getY(), 2, 2);
			}
		}
		g.setColor(Color.GREEN);
		for (Zombie zombie : zombieList) {
			g.fillOval(zombie.getX(), zombie.getY(), 2, 2);
		}

    	
    }
}