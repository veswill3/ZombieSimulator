package vesper.zombie.simulation;

import java.util.ArrayList;
import java.util.Random;

public class ZombieSimulator {
	
	static int boardHeight = 500;
	static int boardWidth = 800;
	static Board board;
	static ArrayList<Humanoid> humanList = new ArrayList<Humanoid>();
	static ArrayList<Zombie> zombieList = new ArrayList<Zombie>();
	private static boolean gameOver = false;
	static GameSetup setup = new GameSetup();
	static int turnNumber;	

	static public void main(String[] args) {
		
		setup.setVisible(true);
	}
	
	static void startGame(int strtCitizens, int strtPolice, int ammo, int accuracy, int strtZombies,
			int zombieDetection, int maxHumStam, int maxZomStam, int brdHeight, int brdWidth) {
		setup.setVisible(false);
		boardHeight = brdHeight;
		boardWidth = brdWidth;
		setupPlayers(strtCitizens, strtPolice, ammo, accuracy, maxHumStam, strtZombies, maxZomStam, zombieDetection);
		board = new Board();
		new GameWindow(boardHeight, boardWidth, board).setLocation(setup.getLocation());
		runGame();
	}

	private static void runGame() {
		// this has the actual game loop
		// I needed to invoke a new thread here to keep the GUI responsive
	    new Thread(new Runnable() {
	    	public void run() {
	    		turnNumber = 0;
	    		while (gameOver == false) {
	    			turnNumber++;
	    			executeHumanoidDecisions();
	    			applyInfection();
	    			gameOver = isGameOver();
	    			updateBoard();
	    		}
	    		postGameReport();
	    	}	
	    }).start(); // start the thread
	}

	private static void executeHumanoidDecisions() {
		for (Zombie zombie : zombieList) {
			zombie.takeTurn(humanList);
		}
		for (Humanoid human : humanList) {
			human.takeTurn();
		}		
	}

	private static void setupPlayers(int numCitizens, int numPolice, int policeAmmo, int accuracy,
			int humanStamina, int numZomibe, int zombieStamina, int detectionDistance) {
		Random rnd = new Random();
		// Set properties in each person type
		Citizen.setMaxStamina(humanStamina);
		Police.setMaxStamina(humanStamina);
		Police.setAccuracy(accuracy);
		Police.setStartAmmo(policeAmmo);
		Zombie.setMaxStamina(zombieStamina);
		Zombie.setDetectionDistance(detectionDistance);
		humanList.clear();
		// generate all Citizens
		for (int i = 0; i < numCitizens; i++) {
			humanList.add(new Citizen(rnd.nextInt(boardWidth), rnd.nextInt(boardHeight)));
		}
		// generate all Police
		for (int i = 0; i < numPolice; i++) {
			humanList.add(new Police(rnd.nextInt(boardWidth), rnd.nextInt(boardHeight)));
		}
		// generate all Zombies
		zombieList.clear();
		for (int i = 0; i < numZomibe; i++) {
			zombieList.add(new Zombie(rnd.nextInt(boardWidth), rnd.nextInt(boardHeight), 0));
		}
	}
	
	private static void applyInfection() {
		ArrayList<Humanoid> infected = new ArrayList<Humanoid>();
		ArrayList<Zombie> newZombies = new ArrayList<Zombie>();
		
		for (Zombie zombie : zombieList) {
			infected.clear();
			for (Humanoid human : humanList) {
				if (getDistanceBetween(human, zombie) <= Math.sqrt(2)) {
					infected.add(human);
					newZombies.add(new Zombie(human.getX(), human.getY(), turnNumber));
				}
			}
			
			for (Humanoid infectee : infected) {
				humanList.remove(infectee);
			}
		}
		
		for (Zombie zombie : newZombies) {
			zombieList.add(zombie);
		}
	}
	
	private static void updateBoard() {
		// this will update the color matrix and show you the state of play
		board.updateBoard(humanList, zombieList, turnNumber);
	}

	private static boolean isGameOver() {
		if (humanList.size() < 1) {
			return true;
		} else {
			return false;
		}
	}

	private static void postGameReport() {
		// outputs the statistics for the game
		
		int zombies = zombieList.size();
		int humans = humanList.size();
		
		if (zombieList.size() != 0) {
			System.out.println("All hail our zombie overloards! \n");
		} else {
			System.out.println("May the dead rest in peace, and may the undead stay dead.");
		}
		System.out.println("Zombies at large: " + zombies + " Humans left: " + humans);
	}

	public static int getBoardHeight() {
		return boardHeight;
	}

	public static int getBoardWidth() {
		return boardWidth;
	}

	public static ArrayList<Humanoid> getHumanList() {
		return humanList;
	}

	public static ArrayList<Zombie> getZombieList() {
		return zombieList;
	}
	
	public static double getDistanceBetween(Humanoid first, Humanoid second) {
		return getDistanceBetween(first.getX(), first.getY(), second.getX(), second.getY());
	}
	
	public static double getDistanceBetween(int X1, int Y1, int X2, int Y2) {
		return Math.sqrt(Math.pow(Math.abs(X2-X1), 2) + Math.pow(Math.abs(Y2-Y1), 2));
	}

}
