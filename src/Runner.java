import util.Physics;


public class Runner {

	public static void main(String[] args) {
		double velocity = Physics.v_drift(500, 5e3);
		System.out.println("Electron drift velocity: " + velocity);
		
	}
}
