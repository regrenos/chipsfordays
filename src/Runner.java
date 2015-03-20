import bjt.amplifiers.CommonEmitterACBypassedEmitterResistance;
import bjt.amplifiers.CommonEmitterACUnbypassedEmitterResistance;





public class Runner {

	public static void main(String[] args) {
		
		double vCC = 15;
		double rC = 20e3;
		double rE = 20e3;
		double rE_1 = 2e3;
		double r1 = 300e3;
		double r2 = 180e3;
		double betaF = 100;
		double vA = Double.MAX_VALUE;
		double rSig_1 = 2e3;
		double rL_2 = 100e3;
		
		double iC_1 = CommonEmitterACUnbypassedEmitterResistance.collectorCurrentApproximation(r1, r2, vCC, betaF, rE);
		double iB_1 = CommonEmitterACUnbypassedEmitterResistance.baseCurrentApproximation(r1, r2, vCC, betaF, rE);
		double vBC_1 = CommonEmitterACUnbypassedEmitterResistance.baseToCollectorBiasApproximation(vCC, betaF, rC, rE, r1, r2);
		double vCE_1 = CommonEmitterACUnbypassedEmitterResistance.collectorToEmitterBiasApproximation(vCC, betaF, rC, rE, r1, r2);
		
		double iC_2 = CommonEmitterACBypassedEmitterResistance.collectorCurrentApproximation(r1, r2, vCC, betaF, rE);
		double iB_2 = CommonEmitterACBypassedEmitterResistance.baseCurrentApproximation(r1, r2, vCC, betaF, rE);
		double vBC_2 = CommonEmitterACBypassedEmitterResistance.baseToCollectorBiasApproximation(vCC, betaF, rC, rE, r1, r2);
		double vCE_2 = CommonEmitterACBypassedEmitterResistance.collectorToEmitterBiasApproximation(vCC, betaF, rC, rE, r1, r2);	
		
		double rout_1 = CommonEmitterACUnbypassedEmitterResistance.outputResistance(vA, r1, r2, vCC, betaF, rE_1, rC, rSig_1);
		double rSig_2 = rout_1;
		
		double avt_2 = CommonEmitterACBypassedEmitterResistance.terminalVoltageGain(vA, r1, r2, vCC, betaF, rE, rC, rL_2);
		double av_2 = CommonEmitterACBypassedEmitterResistance.voltageGain(vA, r1, r2, vCC, betaF, rE, rC, rL_2, rSig_2);
		double rin_2 = CommonEmitterACBypassedEmitterResistance.inputResistance(r1, r2, vCC, betaF, rE);
		double rL_1 = rin_2;
		
		double avt_1 = CommonEmitterACUnbypassedEmitterResistance.terminalVoltageGain(vA, r1, r2, vCC, betaF, rE_1, rC, rL_1);
		double av_1 = CommonEmitterACUnbypassedEmitterResistance.voltageGain(vA, r1, r2, vCC, betaF, rE_1, rC, rL_1, rSig_1);
		
		double rin_1 = CommonEmitterACUnbypassedEmitterResistance.inputResistance(r1, r2, vCC, betaF, rE_1, rC, rL_1, vA);
		double rout_2 = CommonEmitterACBypassedEmitterResistance.outputResistance(vA, r1, r2, vCC, betaF, rE, rC);
		
		double ai_1 = CommonEmitterACUnbypassedEmitterResistance.currentGain(vA, r1, r2, vCC, betaF, rE_1, rC, rL_1);
		double ai_2 = CommonEmitterACBypassedEmitterResistance.currentGain(vA, r1, r2, vCC, betaF, rE, rC, rL_2);
		
		double av = av_1*av_2;
		double ai = ai_1*ai_2;
		double rin = rin_1;
		double rout = rout_2;
		
		System.out.println("============================== Problem 1 =============================");
		System.out.println("\t \t --- Quiescent Point ---");
		System.out.println("\t \t \t|-------- Q1 -------|\t|-------- Q2 -------|");
		System.out.println("Collector Current: \t " + iC_1 + "\t " + iC_2);
		System.out.println("Base Current: \t \t " + iB_1 + "\t " + iB_2);
		System.out.println("Base/Collector Bias: \t " + vBC_1 + "\t " + vBC_2);
		System.out.println("Collector/Emitter Bias:\t " + vCE_1 + "\t " + vCE_2);
		System.out.println(" \t \t --- Small Signal Paramteters ---");
		System.out.println("Terminal Gain: \t \t " + avt_1 + "\t " + avt_2);
		System.out.println("Voltage Gain: \t \t " + av_1 + "\t " + av_2);
		System.out.println("Current Gain: \t \t " + ai_1 + "\t " + ai_2);
		System.out.println("Input Resistance: \t " + rin_1 + "\t " + rin_2);
		System.out.println("Output Resistance:\t " + rout_1 + "\t \t " + rout_2);
		System.out.println("\t \t --- Overall Characteristics ---");
		System.out.println("Voltage Gain: " + av);
		System.out.println("Current Gain: " + ai);
		System.out.println("Input Resistance: " + rin);
		System.out.println("Output Resistance: " + rout);
		
	}
}