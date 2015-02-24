import mosfet.LevelOneModel;


public class Runner {

	public static void main(String[] args) {
		double kN = 500e-6;
		double vTN = 1;
		double lambdaN = 0.025;
		double vGS = 5;
		double vDS = 6;
		double current = LevelOneModel.saturationRangeDrainCurrent(kN, vGS, vTN, vDS, lambdaN)*1000;
		System.out.println("Drain current: " + current + "mA");
		double currentNoModulation = LevelOneModel.saturationRangeDrainCurrent(kN, vGS, vTN, vDS, 0)*1000;
		System.out.println("No modulaton current: " + currentNoModulation + "mA");
		double error = Math.abs(currentNoModulation-current)/current*100;
		System.out.println("Percent error: " + error);
	}
}
