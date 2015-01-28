
public class DepletionMOSCAP extends MOSCAP{
	//Depletion Bias Range for MOSCAP
	public static double Vsc(double Vgs,double Na, double Xox, double QfQit){
		System.out.print("Space charge voltage: ");
		return C.s( Vgs-Vfb(Na,Xox,QfQit)+(C.q*C.esi*Na)/C.p(Cox(Xox), 2)
				*(1-Math.sqrt(1+(2*C.p(Cox(Xox),2)/(C.q*C.esi*Na)*(Vgs-Vfb(Na,Xox,QfQit))))));
	}
	public static double Wd(double Na, double Vgs, double Xox, double QfQit){
		System.out.print("Width of the depletion region: ");
		return C.s( Math.sqrt((2*C.esi/(C.q*Na))*Vsc(Vgs,Na,Xox,QfQit)));
	}
	public static double Qsc(double Na,double Vgs,double Xox,double QfQit){
		System.out.print("Space charge region: ");
		return C.s( -Math.sqrt(2*C.q*C.esi*Na*Vsc(Vgs,Na,Xox,QfQit)));
	}
	public static double Qg(double Na,double Vgs,double Xox,double QfQit){
		System.out.print("Charge on the gate: ");
		return C.s( -Qsc(Na,Vgs,Xox,QfQit)-QfQit);
	} 
	public static double Eox(double Na,double Vgs,double Xox,double QfQit){
		System.out.print("Oxide Field: ");
		return C.s( Qg(Na,Vgs,Xox,QfQit)/C.eox);
	}
	public static double Vox(double Na,double Vgs,double Xox,double QfQit){
	System.out.print("Oxide Voltage: ");
		return C.s( Eox(Na,Vgs,Xox,QfQit)*Xox);	
	}
	public static double  Vgb(double Na, double Vgs, double Xox, double QfQit){
		System.out.print("Gate to bulk voltage: ");
		return C.s( -Qsc(Na,Vgs,Xox,QfQit)/Cox(Xox)+Vsc(Vgs,Na,Xox,QfQit)+Vfb(Na,Xox,QfQit));
	}
	public static double Csc(double Na,double Vgs,double Xox, double QfQit){
		System.out.print("Space charge capacitance: ");
		return C.s(C.esi/(Wd(Na,Vgs,Xox,QfQit)));
	}
	}
