package moscap;

import util.Constants;

public class ThresholdMOSCAP extends MOSCAP {
	// At the Threshold Voltage for MOSCAP
	public static double Vtn(double Na, double QfQit, double Xox) {
		System.out.print("Threshold Voltage: ");
		return Constants.s(Vfb(Na, Xox, QfQit) + 2 * phiFb(Na)
				+ Math.sqrt(2 * Constants.q * Constants.esi * Na * phiFb(Na))
				/ Cox(Xox));
	}

	public static double VSC(double Na) {
		System.out.print("Space charge Voltage: ");
		return Constants.s(2 * phiFb(Na));
	}

	public static double Wd(double Na) {
		System.out.print("Width of the depletion region: ");
		return Constants.s(Math.sqrt(4 * Constants.esi * phiFb(Na)
				/ (Constants.q * Na)));
	}

	public static double Qsc(double Na) {
		System.out.print("Space charge: ");
		return Constants.s(-Math.sqrt(4 * Constants.q * Constants.esi * Na
				* phiFb(Na)));
	}

	public static double Qg(double Na, double QfQit) {
		System.out.print("Charge on the gate: ");
		return Constants.s(Math.sqrt(4 * Constants.q * Constants.esi * Na
				* phiFb(Na))
				- QfQit);
	}

	public static double Eox(double Na, double QfQit) {
		System.out.print("Oxide Field: ");
		return Constants.s(Qg(Na, QfQit) / Constants.eox);
	}

	public static double Vox(double Xox, double Na, double QfQit) {
		System.out.print("Voltage on the oxide: ");
		return Constants.s(Eox(Na, QfQit) * Xox);
	}
}
