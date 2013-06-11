package vesper.zombie.simulation;

import java.awt.*;
import java.awt.event.*;

public class GameWindow extends Frame {

	private static final long serialVersionUID = 1L;

	GameWindow(int boardHeight, int boardWidth, Board board) {
	    addWindowListener(new WindowAdapter() { 
	    		public void windowClosing(WindowEvent e) {
	    			System.exit(0);
	    		}
	    	}
	    );
	    setSize(boardWidth + 25, boardHeight + 46);
	    setTitle("Zombie-Survival - By Vesper Williams");
	    setBackground(Color.black);
	    add("Center", board);
	    setVisible(true);
	    toFront();
    }
}