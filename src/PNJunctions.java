
public class PNJunctions {

	/**Built in Voltage */
	public static double Vbi(double Na, double Nd){
		System.out.print("Built in voltage (V): ");
		return C.s( C.kbtq*Math.log((Na*Nd)/C.p(C.ni,2)));
	}

	/**Depletion Region Width of PNJunction */
	public static double Wd(double Na, double Nd,double Vpn){
		System.out.print("Depletion width: ");
		return C.s( Math.sqrt((2*C.esi*(Na+Nd)*(Vbi(Na,Nd)-Vpn))/(C.q*Na*Nd)));
	}

	/** Depletion Region Width on P-Side */
	public static double Wdp(double Na, double Nd,double Vpn){
		System.out.print("Depletion width on p-side: ");
		return C.s( (Nd*Wd(Na,Nd,Vpn))/(Na+Nd));
	}

	/**Depletion Region Width on N-Side */
	public static double Wdn(double Na, double Nd, double Vpn){
		System.out.print("Depletion region width on n-side: ");
		return(Na*Wd(Na,Nd,Vpn))/(Na+Nd);
	}

	/**Maximum Electric Field at x=0 */
	public static double zeroField(double Na,double Nd,double Vpn){
		System.out.print("Field when x=0. The max field: ");
		return C.s((C.q*Na*Wdp(Na,Nd,Vpn))/C.esi);

	}

	/**pN Law of the Junction. Calculated Minority Carrier Concentrations at the P Depletion Region Edges */
	public static double lawOfJunctionP(double Na, double Nd, double Vpn){
		System.out.print("Concentration of holes at thermal equilibrium: ");
		double Pno= C.s(Nd/(C.p(C.ni, 2)));
		System.out.println(Pno);
		System.out.print("Holes at Depletion region edge: ");
		return C.s(Pno*C.exp((Vpn)/C.kbtq));

	}

	/**nP Law of the Junction. Calculated Minority Carrier Concentrations at the N Depletion Region Edges */
	public static double lawOfJunctionN(double Na, double Nd, double Vpn){
		double Npo=Na/(C.p(C.ni, 2));
		System.out.println("Npo= "+Npo);
		System.out.print("Electrons at Depletion Region: ");
		return C.s(Npo*C.exp((Vpn)/C.kbtq));
	}

	/**PN-Junction Diode Current Density (12 review 2) */
	//Add this 
	//Add diode current 

	/**Small Signal PN-Junction Diode Depletion Capacitance Per Unit Area */
	public static double depletionCapacitance(double Na, double Nd, double Vpn){
		System.out.print("Depletion region capacitance value: ");
		return C.s( Math.sqrt((C.q*C.esi*Na*Nd)/(2*(Na+Nd)*(Vbi(Na,Nd)-Vpn))));
	}

	//other capacitances on 22. Get a lotta ugly stuff in there
}
