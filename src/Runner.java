import pn_junction.PNJunctions;
import util.Constants;
import util.Physics;
import moscap.MOSCAP;
import mosfet.Amplifiers;
import mosfet.MOSFET;


public class Runner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Physics.u_pN(3.25E16);
		Physics.u_nP(4.5E17);
		Physics.L_pN(Physics.Dp(437.84, Constants.t),Physics.tau_recN(3.25E16));
		Physics.L_nP(Physics.Dn(437.84, Constants.t), Physics.tau_recP(4.5E17));
		PNJunctions.Vbi(4.5E17, 3.25E16);
		
		MOSCAP.PhiPm(2.5E16);
		Amplifiers.gmsat(120E-6, 3.69, 0.40);
		Amplifiers.Idq(120E-6, 3.69, 0.40);
		Inverter.Vol(3.3, 0.5, 250E-6, 15000);
		
		MOSFET.vtn0(1.2E18, 22E-8, 3.2E10*Constants.q);
		MOSFET.VDsat(1.2, 0, 1.2E18, 22E-8, 3.2E10*Constants.q);
		MOSFET.IDsat(0, 154.11*MOSCAP.Cox(22E-8)*(4/.1),0.8409462560512138+0.5 , 1.2, 0, 1.2E18, 22E-8,3.2E10*Constants.q);
	}
	


}
