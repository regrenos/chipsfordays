import mosfet.amplifiers.CommonSourceBypassedSourceResistance;



public class Runner {

	public static void main(String[] args) {

		double kN = 500e-6;
		double vTN = 1;
		double vDD = 10;
		double rSig = 1e3;
		double r1 = 430e3;
		double r2 = 560e3;
		double rD = 43e3;
		double rS = 20e3;
		double rL = 100e3;
		double lambdaN = 0.0133;
		
		double idsat = 131.193e-6;
		double vDS = 1.735;
		double vGS = 1.720;
		
		double rdssat = CommonSourceBypassedSourceResistance.outputResistance(lambdaN, vDS, vGS, vTN, idsat);
		double gmsat = CommonSourceBypassedSourceResistance.forwardTransconductance(kN, idsat, lambdaN, vDS, vDS, vTN);
		double rin = CommonSourceBypassedSourceResistance.smallSignalInputResistance(r1, r2);
		double rout = CommonSourceBypassedSourceResistance.smallSignalOutputResistance(rdssat, rD);
		double aV = CommonSourceBypassedSourceResistance.voltageGain(gmsat, rdssat, rD, rL, r1, r2, rSig);
		double aVt = CommonSourceBypassedSourceResistance.terminalVoltageGain(gmsat, rdssat, rD, rL);
		double ai = CommonSourceBypassedSourceResistance.currentGain(gmsat, rdssat, rD, rL, r1, r2);
				
		System.out.println("========Bypassed Source Resistance========");
		System.out.println("Input resistance: "+rin);
		System.out.println("Output resistance: "+rout);
		System.out.println("Voltage gain: " + aV);
		System.out.println("Terminal voltage gain: " + aVt);
		System.out.println("Current gain: " + ai);				
	}
}
