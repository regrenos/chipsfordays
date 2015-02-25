import mosfet.amplifiers.CommonSourceBypassedSourceResistance;



public class Runner {

	public static void main(String[] args) {

		double kN = 250e-6;
		double vTN = 1;
		double vDD = 16;
		double rSig = 1e3;
		double r1 = 1e6;
		double r2 = 2.7e6;
		double rD = 82e3;
		double rS = 27e3;
		double rL = 470e3;
		double lambdaN = 0;
		
		double idsat = 9.145e-5;
		double vDS = 6.032;
		double vGS = 1.855;
		
		double rdssat = CommonSourceBypassedSourceResistance.outputResistance(lambdaN, vDS, vGS, vTN, idsat);
		double gmsat = CommonSourceBypassedSourceResistance.forwardTransconductance(kN, idsat, lambdaN, vDS, vDS, vTN);
		double rin = CommonSourceBypassedSourceResistance.smallSignalInputResistance(r1, r2);
		double rout = CommonSourceBypassedSourceResistance.smallSignalOutputResistance(rdssat, rD);
		double aV = CommonSourceBypassedSourceResistance.voltageGain(gmsat, rdssat, rD, rL, r1, r2, rSig);
		double aVt = CommonSourceBypassedSourceResistance.terminalVoltageGain(gmsat, rdssat, rdssat, rL);
		double ai = CommonSourceBypassedSourceResistance.currentGain(gmsat, rdssat, rdssat, rL, r1, r2);
		
		System.out.println(gmsat);
		System.out.println(CommonSourceBypassedSourceResistance.rP(rdssat, rD, rL));
		
		System.out.println("Input resistance: "+rin);
		System.out.println("Output resistance: "+rout);
		System.out.println("Voltage gain: " + aV);
		System.out.println("Terminal voltage gain: " + aVt);
		System.out.println("Current gain: " + ai);
				
	}
}
