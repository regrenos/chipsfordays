
public class ThresholdMOSCAP extends MOSCAP {
	//At the Threshold Voltage for MOSCAP
	public static double Vtn(double Na,double QfQit,double Xox){
		System.out.print("Threshold Voltage: ");
		return C.s( Vfb(Na,Xox,QfQit)+2*phiFb(Na)+Math.sqrt(2*C.q*C.esi*Na*phiFb(Na))/Cox(Xox));
	}
	public static double VSC(double Na){
		System.out.print("Space charge Voltage: ");
		return C.s( 2*phiFb(Na));
	}
	public static double Wd (double Na){
		System.out.print("Width of the depletion region: ");
		return C.s( Math.sqrt(4*C.esi*phiFb(Na)/(C.q*Na)));
	}
	public static double Qsc(double Na){
		System.out.print("Space charge: ");
		return C.s( -Math.sqrt(4*C.q*C.esi*Na*phiFb(Na)));
	}
	public static double Qg(double Na,double QfQit){
		System.out.print("Charge on the gate: ");
		return C.s( Math.sqrt(4*C.q*C.esi*Na*phiFb(Na))-QfQit);
	}
	public static double Eox(double Na, double QfQit){
		System.out.print("Oxide Field: ");
		return C.s( Qg(Na,QfQit)/C.eox);
	}
	public static double Vox(double Xox, double Na, double QfQit){
		System.out.print("Voltage on the oxide: ");
		return C.s( Eox(Na,QfQit)*Xox);
	}
}
