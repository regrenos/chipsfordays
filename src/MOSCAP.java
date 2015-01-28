
public class MOSCAP {
	
	/**Contact-Potential Different Between the P-type Substrate and the Backside Metal Contact (V) */
	public static double PhiPm(double Na){
		double a= -0.51165-C.kbtq*Math.log(Na/C.ni);
		System.out.print("Phi pm, Voltage difference to metal contact (V): ");
		return C.s(a);
	}
	
	/**Flatband Constant */
	public static double phiFb(double Na){
		double a= C.kbtq*Math.log(Na/C.ni);
		System.out.print("phi fb: ");

		return C.s(a);
	}
	
	//
	public static double Cox(double Xox){
		double a=  C.eox/Xox;
		System.out.print(Xox+" hehehehe Cocks: ");

		return C.s(a);
	}
	
	/**Flatband Voltage */
	public static double Vfb(double Na,double Xox, double QfQit){
		double a=  PhiPm(Na)-QfQit/Cox(Xox);
		System.out.print("Flat Band Voltage: ");
		return C.s(a);
	}
	
	/**Threshold Voltage */
	public static double Vtn(double Na, double QfQit, double Xox){
		double a= Vfb(Na,Xox,QfQit)+2*phiFb(Na)+Math.sqrt(4*C.q*C.esi*Na*phiFb(Na))/Cox(Xox);
		System.out.print("Threshold Voltage: ");

		return C.s(a);
	}
	
	//Vscb Lecture 2 Slide 38
	
	//Capacitances
}
