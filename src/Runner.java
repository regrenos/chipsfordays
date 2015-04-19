import java.util.List;

import javafx.geometry.Point2D;
import digital.MOSInverterEnhancementSaturationLoad;
import digital.MOSInverterResistiveLoad;






public class Runner {

	public static void main(String[] args) {
		
		double vDD = 2.5;
		double rL = 200e3;
		double kN = 300e-6;
		double vTN = 0.6;
		
		double vOL = MOSInverterResistiveLoad.outputLow(vDD, vTN, kN, rL);
		double vOH = MOSInverterResistiveLoad.outputHigh(vDD);
		double vIL = MOSInverterResistiveLoad.inputLow(vTN, kN, rL);
		double vIH = MOSInverterResistiveLoad.inputHigh(vTN, kN, rL, vDD);
		double snmL = MOSInverterResistiveLoad.staticNoiseMarginLow(vTN, kN, rL, vDD);
		double snmH = MOSInverterResistiveLoad.staticNoiseMarginHigh(vTN, kN, rL, vDD);
		double p = MOSInverterResistiveLoad.instantaneousPower(vOL, vTN, kN, rL, vDD);
		
		//System.out.println("vOL: " + vOL);
		//System.out.println("vOH: " + vOH);
		//System.out.println("vIL: " + vIL);
		//System.out.println("vIH: " + vIH);
		//System.out.println("snmL: " + snmL);
		//System.out.println("snmH: " + snmH);
		//System.out.println("p: " + p);
		
		double vinb = vTN + (Math.sqrt(1+2*kN*rL*vDD)-1)/(kN*rL);
		double voutb = vinb - vTN;
		
		//System.out.println("Vinb: " + vinb);
		//System.out.println("Voutb: " + voutb);
		
		problemTwo();
	}

	private static void problemTwo() {
		double vDD = 3.3;
		double gammaNL = .6;
		double phiFB = .3;
		double vTNL0 = .6;
		double vTND0 = .6;
		double kNL = 200e-6;
		double kND = 400e-6;
		double lambdaNL = 0.05;
		double lambdaND = 0.05;
		
		List<Point2D> transferCharacteristics = MOSInverterEnhancementSaturationLoad.powerSupplyCurrentCharacteristics(vTNL0, vTND0, gammaNL, phiFB, kNL, kND, lambdaNL, lambdaND, vDD);
		for(Point2D point: transferCharacteristics){
			System.out.println(point.getX() + "\t" + point.getY());
		}
		
		//System.out.println(MOSInverterEnhancementSaturationLoad.inputHigh(vTNL0, vTND0, gammaNL, phiFB, kNL, kND, lambdaNL, lambdaND, vDD));
		//System.out.println(MOSInverterEnhancementSaturationLoad.inputLow(vTNL0, vTND0, gammaNL, phiFB, kNL, kND, lambdaNL, lambdaND, vDD));
	}
}