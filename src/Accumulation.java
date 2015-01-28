
public class Accumulation extends MOSCAP {

	//Accumulation Bias Range for MOSCAP
	//Vgb<Vfb
	
	public static double Eox(double Vgb, double Na, double Nd, double Xox){
		System.out.print("Electric field in the oxide: ");
		return C.s( (Vgb-PhiPm(Na))/Xox);
	}
	public static double Qsc(double Xox,double Vgb, double Na, double QfQit){
		System.out.print("Space charge: ");
		return C.s( Cox(Xox)*(Vgb-Vfb(Na,Xox,QfQit)));
	}
	public static double Qg(double Xox,double Na, double Vgb){
		System.out.print("Charge on the gate: ");
		return C.s( Cox(Xox)*(Vgb-PhiPm(Na)));
	}
	public static double Cgb(double Xox){
		System.out.print("Gate to Bulk capacitance: ");
		return C.s(Cox(Xox));
	}
}
