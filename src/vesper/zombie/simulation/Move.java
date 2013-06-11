package vesper.zombie.simulation;

public class Move {
	
	private static final int runDistance = 2;
	int Xdir;
	int Ydir;
	
	public final static Move rest = new Move(0, 0);
	public final static Move up = new Move(0, 1);
	public final static Move down = new Move(0, -1);
	public final static Move right = new Move(1, 0);
	public final static Move left = new Move(-1, 0);
	public final static Move upFast = new Move(0, runDistance);
	public final static Move downFast = new Move(0, -runDistance);
	public final static Move rightFast = new Move(runDistance, 0);
	public final static Move leftFast = new Move(-runDistance, 0);
	
	private Move(int x, int y) {
		Xdir = x;
		Ydir = y;
	}
	
	public int getX() {
		return Xdir;
	}
	
	public int getY() {
		return Ydir;
	}
	
	public String toString() {
		return "[" + Integer.toString(getX()) + "," + Integer.toString(getY()) + "]";
	}

}
