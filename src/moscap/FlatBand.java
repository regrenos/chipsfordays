package moscap;

import util.Constants;

public class FlatBand extends MOSCAP {
	// 34
	public static double Eox(double QfQit, double Xox) {
		System.out.print("Electric Field Oxide: ");
		return Constants.s(QfQit / Constants.eox);
	}

	public static double Vox(double Xox, double QfQit) {
		System.out.print("Voltage across the Oxide: ");
		return Constants.s(-QfQit / Cox(Xox));
	}

	public static double Qsc() {
		System.out.print("Space charge: ");
		return Constants.s(0);
	}

	public static double Vsc() {
		System.out.print("Space charge building: ");
		return Constants.s(0);
	}

	public static double nb(double Na) {
		System.out.print("Electron concentration in bulk:  ");
		return Constants.s(Constants.p(Constants.ni, 2) / Na);
	}

	public static double pb(double Na) {
		System.out.print("Hole concentration in bulk: ");
		return Constants.s(Na);
	}
}
