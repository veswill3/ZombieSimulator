package vesper.zombie.simulation;

import java.util.Random;

public class Humanoid {
	private static int maxStamina;
	private int X;
	private int Y;
	private int stamina;
	private int distanceTraveled;
	
	Humanoid(int x, int y) {
		this.X = x;
		this.Y = y;
		this.stamina = maxStamina;
		this.distanceTraveled = 0;
	}
	
	void takeTurn() {
		Random randomGenerator = new Random();
		int i = randomGenerator.nextInt(10);
		if (i == 0) {
			makeMove(Move.up);
		} else if (i == 1) {
			makeMove(Move.down);
		} else if (i == 2) {
			makeMove(Move.left);
		} else if (i == 3) {
			makeMove(Move.right);
		} else {
			makeMove(Move.rest);
		}
	}
	
	void makeMove(Move move) {
		// This will force a rest period if you are too tired to move
		if (this.stamina <= 1) {
				move = Move.rest;
		} else { // player can only move if he has some stamina
			// make sure player does not move off board in x direction
			if ( (this.X + move.getX()) <= ZombieSimulator.getBoardWidth() & (this.X + move.getX()) >= 0) {
				this.X = this.X + move.getX();
				this.distanceTraveled = this.distanceTraveled + Math.abs(move.getX());
			} // and the same for the Y direction
			if ( (this.Y + move.getY()) <= ZombieSimulator.getBoardHeight() & (this.Y + move.getY()) >= 0) {
				this.Y = this.Y + move.getY();
				this.distanceTraveled = this.distanceTraveled + Math.abs(move.getY());
			}
			// If player was running, take away 2 stamina
			if ((Math.abs(move.getX()) > 1) || (Math.abs(move.getY()) > 1)) {
				this.stamina = this.stamina - 2;
			}
		} if (move == Move.rest & this.stamina < maxStamina) {
			// if player was resting, give back 1 stamina unless he is maxed out
			this.stamina++;
		}
	}

	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}

	public int getDistanceTraveled() {
		return distanceTraveled;
	}

	public static void setMaxStamina(int maxStamina) {
		Humanoid.maxStamina = maxStamina;
	}
	
}
