package vesper.zombie.simulation;

public class Police extends Humanoid {
	private static int startAmmo;
	private static int accuracy;
	private int ammo;
	
	public Police(int x, int y) {
		super(x, y);
		this.ammo = startAmmo;
	}

	private void shoot() {
		ammo--;
	}

	public static void setStartAmmo(int startAmmo) {
		Police.startAmmo = startAmmo;
	}

	public static void setAccuracy(int accuracy) {
		Police.accuracy = accuracy;
	}


}
