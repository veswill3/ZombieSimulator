package vesper.zombie.simulation;

import java.util.ArrayList;

public class Zombie extends Humanoid {
	private static int detectionDistance;
	private int turnInfected;

	Zombie(int x, int y, int turnInfected) {
		super(x, y);
		this.turnInfected = turnInfected;
	}
	
	public void takeTurn(ArrayList<Humanoid> humanList) {
		// find the closest human and run at it.
		Humanoid closestHuman = humanList.get(0);
		for (Humanoid human : humanList) {
			if (ZombieSimulator.getDistanceBetween(this, human) 
					< ZombieSimulator.getDistanceBetween(this,closestHuman)) {
				closestHuman = human;
			}
		}
		// if no human was close enough to see lets wander randomly
		if (ZombieSimulator.getDistanceBetween(this, closestHuman) > detectionDistance) {
			super.takeTurn();
		} else {
			int dx = closestHuman.getX() - getX();
			int dy = closestHuman.getY() - getY();
			if (Math.abs(dx) > Math.abs(dy)) {
				if (dx > 0) {
					makeMove(Move.right);
				} else {
					makeMove(Move.left);
				}
			} else {
				if (dy > 0) {
					makeMove(Move.up);
				} else {
					makeMove(Move.down);
				}
			}
		}
		return;
	}
	
	public void setTurnInfected(int turnInfected) {
		this.turnInfected = turnInfected;
	}

	public int getTurnInfected() {
		return turnInfected;
	}


	public static void setDetectionDistance(int detectionDistance) {
		Zombie.detectionDistance = detectionDistance;
	}


	public static int getDetectionDistance() {
		return detectionDistance;
	}

}
