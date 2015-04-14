package digital;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;

public class MOSInverterEnhancementSaturationLoad {
	
	/**
	 * Logical high output.
	 * 
	 * @param vDD - source bias
	 * @param vTNL0 - 0-bias threshold for load FET
	 * @param gammaNL - parameter for threshold for load FET
	 * @param phiFB - another parameter for thresohld for load FET
	 * @return vOL
	 */
	public static double outputHigh(double vDD, double vTNL0, double gammaNL, double phiFB){
		return (1/4)*Math.pow(Math.sqrt(Math.pow(gammaNL, 2)+4*(vDD-vTNL0+gammaNL*Math.sqrt(2*phiFB)+2*phiFB))-gammaNL, 2)-2*phiFB;
	}
	
	
	public static List<Point2D> voltageTransferCharateristics(double vTNL0, double vTND0, double gammaNL, double phiFB, double vOut, double kNL, double kND, double lambdaNL, double lambdaND, double vDD){
		double[] inputVoltages = new double[10000];
		for(int i=0; i<10000; i++){
			inputVoltages[i] = i*(vDD)/10000;
		}
		List<Point2D> transferCharacteristics = new ArrayList<Point2D>();
		
		// Region 1: Driver in cut-off
		double vOH = MOSInverterEnhancementSaturationLoad.outputHigh(vDD, vTNL0, gammaNL, phiFB);
		int i = 0;
		while (inputVoltages[i]<vTND0){
			transferCharacteristics.add(new Point2D(inputVoltages[i], vOH));
			i++;
		}
		
		//Point a
		transferCharacteristics.add(new Point2D(vTND0, vOH));
		
		//Point b calculations
		
		
		//Region 2: Driver in enhancement		
		while(inputVoltages[i]<vinb	)
		
		
		
		
		return transferCharacteristics;
	}
	
	/**
	 * Threshold voltage of the load MOSFET as a function of the output of the inverter.
	 * 
	 * @param vTNL0 - zero-bias load transistor threshold
	 * @param gammaNL - load transistor parameter
	 * @param phiFB - load transistor paramter
	 * @param vOut - output voltage
	 * @return vTNL
	 */
	public static double loadThresholdFromOutputBias(double vTNL0, double gammaNL, double phiFB, double vOut){
		return vTNL0 + gammaNL*(Math.sqrt(2*phiFB+vOut)-Math.sqrt(2*phiFB));
	}
	
}
