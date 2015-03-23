import bjt.BipolarJunctionTransistor;
import bjt.amplifiers.DifferentialPair;


public class Runner {

	public static void main(String[] args) {
		
		problemOne();
		problemTwo();
		problemThree();
		problemFour();
		problemFive();
		
	}

	private static void problemOne() {
		// 15.1
		double vCC = 15;
		double vEE = 15;
		double rEE = 270e3;
		double rC = 330e3;
		double bF = 100;
		double vA = 70;
		double aF = BipolarJunctionTransistor.commonEmitterToCommonBaseGain(bF);
		double vIN = 0;
		
		// a)
		double iC = DifferentialPair.collectorCurrent(vIN, vEE, rEE, aF);
		double iB = DifferentialPair.baseCurrent(vIN, vEE, rEE, aF, bF);
		double iE = DifferentialPair.emitterCurrent(vIN, vEE, rEE);
		double vCE = DifferentialPair.collectorToEmitterBias(vCC, rC, vIN, vEE, rEE, aF);
		
		// b)
		double Add = DifferentialPair.differentialModeGain(vIN, vEE, rEE, aF, rC);
		double Rid = DifferentialPair.differentialModeInputResistance(vIN, vEE, rEE, aF, bF);
		double Rod = DifferentialPair.differentialModeOutputResistance(rC, vA, vIN, vEE, rEE, aF);
		
		// c)
		double Acc = DifferentialPair.commonModeGain(rC, bF, vIN, vEE, rEE, aF, vA);
		double Ric = DifferentialPair.commonModeInputResistance(bF, vIN, vEE, rEE, aF);
		double CMMR = DifferentialPair.commonModeRejectionRatio(rC, bF, vIN, vEE, rEE, aF, vA);
		
		System.out.println("===================== Problem 15.1 =====================");
		System.out.println("a) Quiescent Operating Point");
		System.out.println("\t Collector Current: \t" + iC);
		System.out.println("\t Base Current: \t\t" + iB);
		System.out.println("\t Emitter Current: \t" + iE);
		System.out.println("\t Collector/Emitter Bias:" + vCE);
		System.out.println("b) Differential-Mode Operation");
		System.out.println("\t Gain: \t\t\t" + Add);
		System.out.println("\t Input Resistance: \t" + Rid);
		System.out.println("\t Output Resistance: \t" + Rod);
		System.out.println("c) Common-Mode Operation");
		System.out.println("\t Gain: \t\t\t" + Acc);
		System.out.println("\t Input Resistance: \t" + Ric);
		System.out.println("\t CMMR: \t\t\t" + CMMR);
	}

	private static void problemTwo() {
		// 15.13
		double vCC = 12;
		double vEE = 12;
		double bF = 120;
		double iEE = 200e-6;
		double rC = 100e3;
		double vA = 70;
		
		// a)
		
		// b)
		
	}

	private static void problemThree() {
		// 15.14
		
	}

	private static void problemFour() {
		// 15.33
		
	}

	private static void problemFive() {
		// 15.45
		
	}
}