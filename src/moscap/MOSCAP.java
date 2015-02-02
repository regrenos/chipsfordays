package moscap;
import util.Constants;


public class MOSCAP {
	
	/**Contact-Potential Different Between the P-type Substrate and the Backside Metal Contact (V) */
	public static double PhiPm(double Na){
		double a= -0.51165-Constants.kbtq*Math.log(Na/Constants.ni);
		System.out.print("Phi pm, Voltage difference to metal contact (V): ");
		return Constants.s(a);
	}
	
	/**Flatband Constant */
	public static double phiFb(double Na){
		double a= Constants.kbtq*Math.log(Na/Constants.ni);
		System.out.print("phi fb: ");

		return Constants.s(a);
	}
	
	//
	public static double Cox(double Xox){
		double a=  Constants.eox/Xox;
		System.out.print(Xox+" hehehehe Cocks: ");

		return Constants.s(a);
	}
	
	/**Flatband Voltage */
	public static double Vfb(double Na,double Xox, double QfQit){
		double a=  PhiPm(Na)-QfQit/Cox(Xox);
		System.out.print("Flat Band Voltage: ");
		return Constants.s(a);
	}
	
	/**Threshold Voltage */
	public static double Vtn(double Na, double QfQit, double Xox){
		double a= Vfb(Na,Xox,QfQit)+2*phiFb(Na)+Math.sqrt(4*Constants.q*Constants.esi*Na*phiFb(Na))/Cox(Xox);
		System.out.print("Threshold Voltage: ");

		return Constants.s(a);
	}
	
	//Vscb Lecture 2 Slide 38
	
	//Capacitances
}
