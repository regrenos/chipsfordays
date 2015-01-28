
public class DepletionMOSCAP extends MOSCAP{
	//Depletion Bias Range for MOSCAP
	public static double Vsc(double Vgs,double Na, double Xox, double QfQit){
		System.out.print("Space charge voltage: ");
		return Constants.s( Vgs-Vfb(Na,Xox,QfQit)+(Constants.q*Constants.esi*Na)/Constants.p(Cox(Xox), 2)
				*(1-Math.sqrt(1+(2*Constants.p(Cox(Xox),2)/(Constants.q*Constants.esi*Na)*(Vgs-Vfb(Na,Xox,QfQit))))));
	}
	public static double Wd(double Na, double Vgs, double Xox, double QfQit){
		System.out.print("Width of the depletion region: ");
		return Constants.s( Math.sqrt((2*Constants.esi/(Constants.q*Na))*Vsc(Vgs,Na,Xox,QfQit)));
	}
	public static double Qsc(double Na,double Vgs,double Xox,double QfQit){
		System.out.print("Space charge region: ");
		return Constants.s( -Math.sqrt(2*Constants.q*Constants.esi*Na*Vsc(Vgs,Na,Xox,QfQit)));
	}
	public static double Qg(double Na,double Vgs,double Xox,double QfQit){
		System.out.print("Charge on the gate: ");
		return Constants.s( -Qsc(Na,Vgs,Xox,QfQit)-QfQit);
	} 
	public static double Eox(double Na,double Vgs,double Xox,double QfQit){
		System.out.print("Oxide Field: ");
		return Constants.s( Qg(Na,Vgs,Xox,QfQit)/Constants.eox);
	}
	public static double Vox(double Na,double Vgs,double Xox,double QfQit){
	System.out.print("Oxide Voltage: ");
		return Constants.s( Eox(Na,Vgs,Xox,QfQit)*Xox);	
	}
	public static double  Vgb(double Na, double Vgs, double Xox, double QfQit){
		System.out.print("Gate to bulk voltage: ");
		return Constants.s( -Qsc(Na,Vgs,Xox,QfQit)/Cox(Xox)+Vsc(Vgs,Na,Xox,QfQit)+Vfb(Na,Xox,QfQit));
	}
	public static double Csc(double Na,double Vgs,double Xox, double QfQit){
		System.out.print("Space charge capacitance: ");
		return Constants.s(Constants.esi/(Wd(Na,Vgs,Xox,QfQit)));
	}
	}
