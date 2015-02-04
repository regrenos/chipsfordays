import bjt.BipolarJunctionTransistor;
import bjt.bias_range_current_approximations.CutOffRange;
import bjt.bias_range_current_approximations.ForwardActiveRange;



public class Runner {

	public static void main(String[] args) {
		String biasRange = BipolarJunctionTransistor.determineBiasRange(0.65, 0);
		System.out.println(biasRange);
		double betaR = BipolarJunctionTransistor.commonBaseToCommonEmitterGain(0.55);
		System.out.println("Beta R: " + betaR);
		double iS = ForwardActiveRange.saturationCurrentFromCollectorCurrent(275e-6, 0.65, 0.55);
		System.out.println("iS: " + iS);
		double betaF = ForwardActiveRange.betaFFromBaseCurrent(iS, 3e-6, 0.65);
		System.out.println("Beta F: " + betaF);
			
		biasRange = BipolarJunctionTransistor.determineBiasRange(0, -5);
		System.out.println(biasRange);
		double iCBS = CutOffRange.collectorCurrent(1e-16, BipolarJunctionTransistor.commonEmitterToCommonBaseGain(1));
		System.out.println("iCBS: " + iCBS);
		
		biasRange = BipolarJunctionTransistor.determineBiasRange(-1, 0);
		System.out.println(biasRange);
		betaF = ForwardActiveRange.betaFFromDefinition(5e-3, 60e-6);
		System.out.println("Beta F:" + betaF);
		betaF = ForwardActiveRange.betaFFromDefinition(7e-3, 80e-6);
		System.out.println("Beta F:" + betaF);
		betaF = ForwardActiveRange.betaFFromDefinition(10e-3, 100e-6);		
		System.out.println("Beta F:" + betaF);
	}
}
