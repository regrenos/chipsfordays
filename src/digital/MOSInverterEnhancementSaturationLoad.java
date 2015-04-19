package digital;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.geometry.Point2D;

public class MOSInverterEnhancementSaturationLoad {
	
	/**
	 * Logical high output.
	 * 
	 * @param vDD
	 *            - source bias
	 * @param vTNL0
	 *            - 0-bias threshold for load FET
	 * @param gammaNL
	 *            - parameter for threshold for load FET
	 * @param phiFB
	 *            - another parameter for thresohld for load FET
	 * @return vOL
	 */
	public static double outputHigh(double vDD, double vTNL0, double gammaNL,
			double phiFB) {
		return .25*Math.pow(Math.sqrt(Math.pow(gammaNL, 2)+4*(vDD-vTNL0+gammaNL*Math.sqrt(2*phiFB)+2*phiFB))-gammaNL, 2)-2*phiFB;
	}
	
	
	/**
	 * Generates a list of Point2D objects where the x value is vin and y is vout.
	 * 
	 * @param vTNL0 - zero-bias threshold voltage for the load fet
	 * @param vTND0 - zero-bias threshold voltage for the driver fet
	 * @param gammaNL - load fet parameter
	 * @param phiFB - load fet flat band parameter
	 * @param kNL - load fet parameter
	 * @param kND - driver fet parameter
	 * @param lambdaNL - load fet channel length modulation factor
	 * @param lambdaND - driver fet channel length modulation factor
	 * @param vDD - supply bias
	 * @return
	 */
	public static List<Point2D> voltageTransferCharateristics(double vTNL0, double vTND0, double gammaNL, double phiFB, double kNL, double kND, double lambdaNL, double lambdaND, double vDD){
		double[] inputVoltages = new double[1000];
		for(int i=0; i<1000; i++){
			inputVoltages[i] = i*(vDD)/1000.0;
		}
		List<Point2D> transferCharacteristics = new ArrayList<Point2D>();
		
		// Region 1: Driver in cut-off
		double vOH = MOSInverterEnhancementSaturationLoad.outputHigh(vDD, vTNL0, gammaNL, phiFB);
		int i = 0;
		while (inputVoltages[i]<vTND0){
			transferCharacteristics.add(new Point2D(inputVoltages[i], vOH));
			//System.out.println(inputVoltages[i] + "\t" + vOH);
			i++;
		}
		
		//Point a
		double vin_a = vTND0;
		double vout_a = vOH;
		transferCharacteristics.add(new Point2D(vin_a, vout_a));
		//System.out.println(vin_a + "\t" + vout_a);
		
		//Point b calculations
		double vout_b = findBPointVout(vTNL0, vTND0, lambdaND, lambdaNL, vDD, gammaNL, phiFB, kNL, kND);
		double vin_b = vout_b + vTND0;
		//System.out.println("point b\t" + vin_b + "\t\t\t" + vout_b);
		
		//Region 2: Driver in enhancement	
		double[] possibleVout = new double[5000];
		for(int j=0;j<possibleVout.length;j++){
			possibleVout[j] = vout_a + j * (vout_b - vout_a) / 5000.0;
		}
		int k = 0;
		while(inputVoltages[i]<vin_b){
			double this_vin = findVinFromVoutInRange2(possibleVout[k], vTNL0, vTND0, lambdaND, lambdaNL, vDD, gammaNL, phiFB, kNL, kND, vin_a, vin_b);
			transferCharacteristics.add(new Point2D(this_vin,possibleVout[k]));
			//System.out.println(this_vin + "\t" + possibleVout[k]);
			while(inputVoltages[i]<this_vin){
				i++;
				if (i==1000){
					break;
				}
			}
			k++;
			if(i==1000 | k==5000){
				break;
			}
		}
		
		// Add b point
		//transferCharacteristics.add(new Point2D(vin_b, vout_b));
		//System.out.println(vin_b + "\t" + vout_b);

		//Region 3:
		double[] possibleVout3 = new double[5000];
		for(int j=0;j<possibleVout3.length;j++){
			possibleVout3[j] = vout_b + j * (0 - vout_b) / 5000.0;
		}
		k = 0;
		while(inputVoltages[i]<vDD){
			double this_vin = findVinFromVoutInRange3(possibleVout3[k], vTNL0, vTND0, lambdaND, lambdaNL, vDD, gammaNL, phiFB, kNL, kND, vin_b, vDD);
			transferCharacteristics.add(new Point2D(this_vin,possibleVout3[k]));
			//System.out.println(this_vin + "\t" + possibleVout[k]);
			while(i<1000 & inputVoltages[i]<this_vin){
				i++;
				if (i==1000){
					break;
				}
			}
			k++;
			if(i==1000 | k==5000){
				break;
			}
		}
		
				
		return transferCharacteristics;
	}
	
	private static double findVinFromVoutInRange3(double vOut, double vTNL0,
			double vTND0, double lambdaND, double lambdaNL, double vDD,
			double gammaNL, double phiFB, double kNL, double kND, double vin_min, double vin_max) {
		double vTNL = MOSInverterEnhancementSaturationLoad.loadThresholdFromOutputBias(vTNL0, gammaNL, phiFB, vOut);
		double[] possibleVin = new double[1000];
		for(int i=0;i<possibleVin.length;i++){
			possibleVin[i] = vin_min + i * vin_max / 1000.0;
		}
		double[] error = new double[1000];
		for(int i=0;i<error.length;i++){
			error[i] = Math.abs(kND*((possibleVin[i]-vTND0)*vOut-0.5*Math.pow(vOut, 2))- 
								kNL/2.0*Math.pow(vDD-vOut-vTNL, 2)*(1+lambdaNL*(vDD-vOut-possibleVin[i]+vTNL)));
		}
		
		return possibleVin[minIndex(error)];
	}


	private static double findVinFromVoutInRange2(double vOut, double vTNL0, double vTND0,
			double lambdaND, double lambdaNL, double vDD, double gammaNL,
			double phiFB, double kNL, double kND, double vin_min, double vin_max) {
		double[] possibleVin = new double[1000];
		for(int i=0;i<possibleVin.length;i++){
			possibleVin[i] = vin_min + i * vin_max / 1000.0;
		}
		double vTNL = MOSInverterEnhancementSaturationLoad.loadThresholdFromOutputBias(vTNL0, gammaNL, phiFB, vOut);
		double[] error = new double[1000];
		for(int i=0;i<error.length;i++){
			error[i] = Math.abs(kND*Math.pow(possibleVin[i]-vTND0,2)*(1+lambdaND*(vOut-possibleVin[i]+vTND0))- 
								kNL*Math.pow(vDD-vOut-vTNL, 2)*(1+lambdaNL*(vDD-vOut-possibleVin[i]+vTNL)));
		}
		
		return possibleVin[minIndex(error)];
	}

	private static double findBPointVout(double vTNL0, double vTND0,
			double lambdaND, double lambdaNL, double vDD, double gammaNL,
			double phiFB, double kNL, double kND) {
		double[] possibleVout= new double[1000];
		for(int i=0;i<possibleVout.length;i++){
			possibleVout[i] = i * vDD / 1000.0;
		}
		double[] error = new double[1000];
		for(int i=0;i<1000;i++){
			double vTNL = MOSInverterEnhancementSaturationLoad.loadThresholdFromOutputBias(vTNL0, gammaNL, phiFB, possibleVout[i]);
			double intermediate = vDD - possibleVout[i] - vTNL;
			error[i] = Math.abs(kND / 2.0 * Math.pow(possibleVout[i],2) - kNL / 2.0 * Math.pow(intermediate,2) * (1 + lambdaNL * (intermediate - possibleVout[i] - vTND0)));
		}
		
		return possibleVout[minIndex(error)];
	}
	
	private static int minIndex(double[] input){
		int index = 0;
		double value = input[0];
		for (int i=0;i<input.length;i++){
			if(input[i]<value){
				index = i;
				value = input[i];
			}
		}
		return index;
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
	
	public static List<Point2D> powerSupplyCurrentCharacteristics(double vTNL0, double vTND0, double gammaNL, double phiFB, double kNL, double kND, double lambdaNL, double lambdaND, double vDD){
		List<Point2D> voltageTransfer = MOSInverterEnhancementSaturationLoad.voltageTransferCharateristics(vTNL0, vTND0, gammaNL, phiFB, kNL, kND, lambdaNL, lambdaND, vDD);
		
		List<Point2D> currentCharacteristics = new ArrayList<>();
		for (Point2D point: voltageTransfer){
			double vin = point.getX();
			double vOut = point.getY();
			double vTNL = MOSInverterEnhancementSaturationLoad.loadThresholdFromOutputBias(vTNL0, gammaNL, phiFB, vOut);
			double idd = kNL/2.0*Math.pow(vDD-vOut-vTNL, 2)*(1+lambdaNL*(vDD-vOut-vin-vTNL));
			currentCharacteristics.add(new Point2D(vin,idd));
		}
		return currentCharacteristics;
	}
	
	public static double outputLow(double vTNL0, double vTND0, double gammaNL, double phiFB, double kNL, double kND, double lambdaNL, double lambdaND, double vDD){
		List<Point2D> voltageTransfer = MOSInverterEnhancementSaturationLoad.voltageTransferCharateristics(vTNL0, vTND0, gammaNL, phiFB, kNL, kND, lambdaNL, lambdaND, vDD);
		
		double[] diff = new double[voltageTransfer.size()];
		double[] vol = new double[voltageTransfer.size()];
		
		double voh = MOSInverterEnhancementSaturationLoad.outputHigh(vDD, vTNL0, gammaNL, phiFB);
		
		for(int i=0;i<voltageTransfer.size();i++){
			diff[i] = Math.abs(voltageTransfer.get(i).getX() - voh);
			vol[i] = voltageTransfer.get(i).getY();
		}
		
		return vol[minIndex(diff)];
	}
	
	public static double inputLow(double vTNL0, double vTND0, double gammaNL, double phiFB, double kNL, double kND, double lambdaNL, double lambdaND, double vDD){
		List<Point2D> voltageTransfer = MOSInverterEnhancementSaturationLoad.voltageTransferCharateristics(vTNL0, vTND0, gammaNL, phiFB, kNL, kND, lambdaNL, lambdaND, vDD);
		double[] slopes = slopes(voltageTransfer);
		double[] error = new double[slopes.length];
		for(int i=0;i<error.length;i++){
			error[i] = Math.abs(slopes[i]+1);
		}
		
		return voltageTransfer.get(minIndex(Arrays.copyOfRange(error, error.length/2, error.length-1))).getX();
	}
	
	/**
	 * Returns the threshold of the driver fet as the slope is undefined at that transition, so the slope closest to -1 is nowhere near where it needs to be.
	 * 
	 * @param vTNL0
	 * @param vTND0
	 * @param gammaNL
	 * @param phiFB
	 * @param kNL
	 * @param kND
	 * @param lambdaNL
	 * @param lambdaND
	 * @param vDD
	 * @return
	 */
	public static double inputHigh(double vTNL0, double vTND0, double gammaNL, double phiFB, double kNL, double kND, double lambdaNL, double lambdaND, double vDD){
		return vTND0;
	}
	
	private static double[] slopes(List<Point2D> input){
		double[] slopes = new double[input.size()-1];
		for(int i=0;i<input.size()-1;i++){
			Point2D first = input.get(i);
			Point2D second = input.get(i+1);
			slopes[i] = (second.getY()-first.getY())/(second.getX()-first.getX());
		}
		return slopes;
	}
	
	public static double staticNoiseMarginLow(double vTNL0, double vTND0, double gammaNL, double phiFB, double kNL, double kND, double lambdaNL, double lambdaND, double vDD){
		return MOSInverterEnhancementSaturationLoad.inputLow(vTNL0, vTND0, gammaNL, phiFB, kNL, kND, lambdaNL, lambdaND, vDD) - 
				MOSInverterEnhancementSaturationLoad.outputLow(vTNL0, vTND0, gammaNL, phiFB, kNL, kND, lambdaNL, lambdaND, vDD);
	}
	
	public static double staticNoiseMarginHigh(double vTNL0, double vTND0, double gammaNL, double phiFB, double kNL, double kND, double lambdaNL, double lambdaND, double vDD){
		return MOSInverterEnhancementSaturationLoad.inputHigh(vTNL0, vTND0, gammaNL, phiFB, kNL, kND, lambdaNL, lambdaND, vDD) -
				MOSInverterEnhancementSaturationLoad.outputHigh(vDD, vTNL0, gammaNL, phiFB);
	}
	
}
