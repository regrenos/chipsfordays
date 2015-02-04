package pn_junction;

import util.Constants;

public class PNJunctions {

	/** Built in Voltage */
	public static double Vbi(double Na, double Nd) {
		System.out.print("Built in voltage (V): ");
		return Constants.s(Constants.kbtq
				* Math.log((Na * Nd) / Constants.p(Constants.ni, 2)));
	}

	/** Depletion Region Width of PNJunction */
	public static double Wd(double Na, double Nd, double Vpn) {
		System.out.print("Depletion width: ");
		return Constants.s(Math.sqrt((2 * Constants.esi * (Na + Nd) * (Vbi(Na,
				Nd) - Vpn)) / (Constants.q * Na * Nd)));
	}

	/** Depletion Region Width on P-Side */
	public static double Wdp(double Na, double Nd, double Vpn) {
		System.out.print("Depletion width on p-side: ");
		return Constants.s((Nd * Wd(Na, Nd, Vpn)) / (Na + Nd));
	}

	/** Depletion Region Width on N-Side */
	public static double Wdn(double Na, double Nd, double Vpn) {
		System.out.print("Depletion region width on n-side: ");
		return (Na * Wd(Na, Nd, Vpn)) / (Na + Nd);
	}

	/** Maximum Electric Field at x=0 */
	public static double zeroField(double Na, double Nd, double Vpn) {
		System.out.print("Field when x=0. The max field: ");
		return Constants.s((Constants.q * Na * Wdp(Na, Nd, Vpn))
				/ Constants.esi);

	}

	/**
	 * pN Law of the Junction. Calculated Minority Carrier Concentrations at the
	 * P Depletion Region Edges
	 */
	public static double lawOfJunctionP(double Na, double Nd, double Vpn) {
		System.out.print("Concentration of holes at thermal equilibrium: ");
		double Pno = Constants.s(Nd / (Constants.p(Constants.ni, 2)));
		System.out.println(Pno);
		System.out.print("Holes at Depletion region edge: ");
		return Constants.s(Pno * Constants.exp((Vpn) / Constants.kbtq));

	}

	/**
	 * nP Law of the Junction. Calculated Minority Carrier Concentrations at the
	 * N Depletion Region Edges
	 */
	public static double lawOfJunctionN(double Na, double Nd, double Vpn) {
		double Npo = Na / (Constants.p(Constants.ni, 2));
		System.out.println("Npo= " + Npo);
		System.out.print("Electrons at Depletion Region: ");
		return Constants.s(Npo * Constants.exp((Vpn) / Constants.kbtq));
	}

	/** PN-Junction Diode Current Density (12 review 2) */
	// Add this
	// Add diode current

	/** Small Signal PN-Junction Diode Depletion Capacitance Per Unit Area */
	public static double depletionCapacitance(double Na, double Nd, double Vpn) {
		System.out.print("Depletion region capacitance value: ");
		return Constants.s(Math.sqrt((Constants.q * Constants.esi * Na * Nd)
				/ (2 * (Na + Nd) * (Vbi(Na, Nd) - Vpn))));
	}

	// other capacitances on 22. Get a lotta ugly stuff in there
}
