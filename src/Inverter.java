
public class Inverter {

	/**Drain Current In Linear Bias Range */
	public static double Idlinear(double Kn,double Vin, double Vtn, double Vout){
		double result = Kn*((Vin-Vtn)*Vout - (1/2)*Math.pow(Vout,2));
		System.out.print("Drain Current in the Linear Bias Range (A): ");
		return Constants.s(result);
	}

	/**Drain Current In Saturation Bias Range */
	public static double Idsat(double Kn,double Vin, double Vtn, double Vout, double lambda, double Vdsat){
		double result = (Kn/2)*Math.pow(Vin-Vtn,2)*(1+lambda*(Vout-Vdsat));
		System.out.print("Drain Current in the Saturation Bias Range (A): ");
		return Constants.s(result);
	}

	/**First Equation For Vdsat */
	public static double Vdsat1(double Vgs, double Vtn){
		double result = Vgs- Vtn;
		System.out.print("Vdsat (V): ");
		return Constants.s(result);
	}

	/**Second Equation for Vdsat */
	public static double Vdsat2(double Vin, double Vtn){
		double result = Vin - Vtn; 
		System.out.print("Vdsat (V): ");
		return Constants.s(result);
	}

	/**Equation for V output low */
	public static double Vol(double Vdd, double Vtn, double Kn, double Rl){
		double result = (Vdd - Vtn + (1/(Kn*Rl)))- Math.sqrt(Constants.p((Vdd-Vtn+1/(Kn*Rl)), 2)-(2*Vdd)/(Kn*Rl));
		System.out.print("Vol (V output low) (V): ");
		return Constants.s(result);
	}

	/**Equation of V input low */
	public static double Vil(double Vtn, double Kn, double Rl){
		System.out.print("Vil(V input low) (V): ");
		return Constants.s(Vtn+1/(Kn*Rl));
	}
	
	/**Equation for V input high */
	public static double Vih(double Vtn, double Vdd, double Kn, double Rl){
		System.out.print("Vih(V input high) (V): ");
		return Constants.s(Vtn-1/(Kn*Rl)+Constants.p((8*Vdd)/(3*Kn*Rl), 1/2));
	}
	
	/**Equation for Static Noise Margin Low */
	public static double SNMl(double Vtn, double Vdd, double Kn, double Rl){
		System.out.print("Static Noise Margin Low: ");
		return Constants.s(2*Vtn-Vdd+Math.sqrt(Constants.p(Vdd-Vtn+1/(Kn*Rl), 2)-(2*Vdd)/(Kn*Rl)));
	}
	
	/**Equation for Static Noise Margin High*/
	public static double SNMh(double Vdd, double Vtn, double Kn, double Rl){
		System.out.print("Static Noise margin High: ");
		return Constants.s(Vdd-Vtn+1/(Kn*Rl)-Constants.p((8*Vdd)/(3*Kn*Rl), 1/2));
	}
	
	/**Equation for Static Current at Vin=Voh */
	public static double IddVoh(double Vtn, double Vdd, double Kn, double Rl){
		System.out.print("Static Currenat At Vin = Voh: ");
		return Constants.s((Vtn-1/(Kn*Rl)+Math.sqrt(Constants.p(Vdd-Vtn+1/(Kn*Rl), 2)-(2*Vdd)/(Kn*Rl)))/Rl);
	}
	
	/**Equation for Static Power at Vin=Voh */
	public static double PddVoh(double Vtn, double Vdd, double Kn, double Rl){
		System.out.print("Static Power at Vin = Voh: "); 
		return Constants.s((Vdd/Rl)*(Vtn-1/(Kn*Rl)+Math.sqrt(Constants.p(Vdd-Vtn+1/(Kn*Rl), 2)-(2*Vdd)/(Kn*Rl))));
	}

	/** Maximum Drain Current */
	public static double Iddmax(double Vdd, double Vol, double Rd){
		double result = (Vdd-Vol)/Rd;
		System.out.print("Maximum Drain Current (A): ");
		return Constants.s(result);
	}

	/**Maximum Power Dissipation */
	public static double Pddmax(double Vdd, double Vol, double Rd){
		double result = Iddmax(Vdd, Vol, Rd)*Vdd;
		System.out.print("Maximum Power Dissipation (W): ");
		return Constants.s(result);
	}

	/**Calculating Vout at Transition Point */
	public static void Voutb(double Vdd, double Kn, double Rd){
		double a = (Kn*Rd)/2;
		double b = 1;
		double c = -Vdd;
		quad(a,b,c);
	}

	/**Quadratic Solver and Output Formatter (Shouldn't need to ever call this) */
	public static void quad(double a, double b, double c){
		double discriminant = Math.pow(b, 2) - (4 * a * c);
		System.out.print("Vout1 (V): ");
		System.out.println(((-1 * b) + Math.sqrt(discriminant)) / (2 * a));
		System.out.print("Vout2 (V): ");
		System.out.print(((-1 * b) - Math.sqrt(discriminant)) / (2 * a));
	}
}