package util;

public class Constants {

	// Electron Charge (C)
	public static double q = 1.612177 * EE(-19); 
	// Temperature in Kelvin
	public static double t = 300; 
	// Boltzmann's Constant (eV/K)
	public static double kb = 8.617385 * EE(-5); 
	// Electron Rest Mass (g)
	public static double mo = 9.909389 * EE(-28); 
	// Dielectric Permittivity of Vacuum (F/cm)
	public static double eo = 8.854187 * EE(-14); 
	// Dielectric Permittivity of Silicon (F/cm)
	public static double esi = 11.7 * eo; 
	// Dielectric Permittivity of Silicon Dioxide (F/cm)
	public static double eox = 3.9 * eo; 
	// Avogadro's Number (mole-1)
	public static double Navo = 6.022136 * EE(23);
	// Electron Volt (J)
	public static double eV = 1.602177 * EE(-19); 
	// Plank's Constant in joules*s. slide 1.5
	public static double hJ = 6.626075 * EE(-34); 
	// Plank's Constant in eV*s
	public static double hEV = 4.135669 * EE(-15); 
	// Reduced Plank's Constant (J*s)
	public static double hbarJ = 1.054572 * EE(-34); 
	// Reduced Plank's Constant (eV*s)
	public static double hbarEV = 6.582122 * EE(-16); 
	// (kbT/q) @ T=300K (V)
	public static double kbtq = 0.02585; 
	// Lattice Constant in angstroms.
	public static double latticeSi = 5.4037; 
	// Energy Gap in Si (Ec-Ev) at 300K (eV)
	public static double EgSi = 1.1242; 
	// Intrinsic Carrier Concentration (cm-3)
	public static double ni = 1.07 * EE(10); 
	// Electron Density of States Effective Mass at 300K
	public static double mndos = 1.090 * mo; 
	// Electron Conductivity Effective Mass at 300K
	public static double mn = .320 * mo; 
	// Hole Density of States Effective Mass at 300K
	public static double mpdos = 1.150 * mo; 
	// Hole Conductivity Effective Mass at 300K
	public static double mp = .386 * mo; 
	// Conduction Band Effective Density of States at 300K (cm-3)
	public static double Nc = 2.86E19; 
	// Valence Band Effective Density of States at 300K(cm-3)
	public static double Nv = 3.10E19; 


	public static double exp(double arg) {
		return p(Math.E, arg);
	}

	public static double EE(double sci) {
		return p(10, sci);
	}

	public static double p(double num, double othNum) {
		return Math.pow(num, othNum);
	}

	public static Double s(double d) {
		System.out.println(d);

		return d;
	}

}
