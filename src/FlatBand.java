
public class FlatBand extends MOSCAP {
//34
	public static double Eox(double QfQit, double Xox){
		System.out.print("Electric Field Oxide: ");
		return C.s(QfQit/C.eox);
	}
	public static double Vox(double Xox, double QfQit){
		System.out.print("Voltage across the Oxide: ");
		return C.s( -QfQit/Cox(Xox));
	}
	public static double Qsc(){
		System.out.print("Space charge: ");
		return C.s(0);
	}
	public static double Vsc(){
		System.out.print("Space charge building: ");
		return C.s( 0);
	}
	public static double nb(double Na){
		System.out.print("Electron concentration in bulk:  ");
		return C.s(C.p(C.ni,2)/Na);
	}
	public static double pb(double Na){
		System.out.print("Hole concentration in bulk: ");
		return C.s(Na);
	}
}
