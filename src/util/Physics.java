package util;

public class Physics {

	public static final double NC_300K = 2.86 * Constants.EE(19); // cm^-3
	public static final double NV_300K = 3.10 * Constants.EE(19); // cm^-3

	/** Density of States in the Conduction Band */
	public static double gc(double E_minus_Ec) { // Review 1.16
		System.out.print("Density of states in conduction band (cm^-3/eV): ");
		double h2 = Constants.p(Constants.hbarEV, 2);
		double a = 1 / (2 * Constants.p(Math.PI, 2));
		double b = Constants.p(2 * Constants.mndos / h2, 3 / 2);
		double c = Math.sqrt(E_minus_Ec);
		return Constants.s(a * b * c);
	}

	/** Density of States in the Valence Band */
	public static double gv(double Ev_minus_E) { // Review 1.16
		System.out.print("Density of states in valence band (cm^-3/eV): ");
		double h2 = Constants.p(Constants.hbarEV, 2);
		double a = 1 / (2 * Constants.p(Math.PI, 2));
		double b = Constants.p(2 * Constants.mpdos / h2, 3 / 2);
		double c = Math.sqrt(Ev_minus_E);
		return Constants.s(a * b * c);
	}

	/** Fermi-Dirac Probability At Energy E */
	public static double fFD(double E_minus_Ef, double T) { // Review 1.17
		System.out.print("Fermi-Dirac probability at energy E (unitless): ");
		double e = Math.exp((E_minus_Ef) / (Constants.kb * T));
		return Constants.s(1 / (1 + e));
	}

	/** Maxwell Boltzmann Probility at Energy E */
	public static double fMB(double E_minus_Ef, double T) { // Review 1.18
		System.out
				.print("Maxwell-Boltzmann approximation of Fermi-Dirac at energy E (unitless): ");
		return Constants.s(Math.exp(-(E_minus_Ef) / (Constants.kb * T)));
	}

	/** Electron Concentration at Thermal Equilibirum */
	public static double n_thermaleq(double Ec_minus_Ef, double T, double Nc) { // Review
																				// 1.20
		double prob = fMB(Ec_minus_Ef, T);
		System.out
				.print("Electron concentration at thermal equilibrium (cm^-3): ");
		return Constants.s(Nc * prob);
	}

	/** Conduction-band Effective Density of States */
	public static double Nc(double T) { // Review 1.20 (other eqn on 1.22)
		double a = 2 * Math.PI * Constants.mndos * Constants.kb * T;
		double h3 = Math.pow(Constants.hEV, 3);
		System.out.print("Conduction-band effective density of states: ");
		return Constants.s(2 * Math.pow(a, 3 / 2) / h3);
	}

	/** Hole Concentration at Thermal Equilibrium */
	public static double p_thermaleq(double Ef_minus_Ev, double T, double Nv) { // Review
																				// 1.21
		double prob = fMB(Ef_minus_Ev, T);
		System.out.print("Hole concentration at thermal equilibrium (cm^-3): ");
		return Constants.s(Nv * prob);
	}

	/** Valence-band Effective Density of States */
	public static double Nv(double T) { // Review 1.21 (other eqn on 1.22)
		double a = 2 * Math.PI * Constants.mpdos * Constants.kb * T;
		double h3 = Math.pow(Constants.hEV, 3);
		System.out.print("Valence-band effective density of states: ");
		return Constants.s(2 * Math.pow(a, 3 / 2) / h3);
	}

	/** Electron Concentration at Thermal Equilibrium In Intrinsic Silicon */
	public static double n_I() { // Review 1.24
		System.out
				.print("Electron concentration at thermal eq in intrinsic Si (cm^-3): ");
		return Constants.s(Constants.ni);
	}

	/** Hole Concentration At Thermal Equilibrium in Intrinsic Silicon */
	public static double p_I() { // Review 1.25
		System.out
				.print("Hole concentration at thermal eq in intrinsic Si (cm^-3): ");
		return Constants.s(Constants.ni);
	}

	/** Hole Concentration At Thermal Equilibrium In P-Silicon */
	public static double p_P(double Na) { // Review 1.31
		System.out.print("Hole concentration at thermal eq in p-Si (cm^-3): ");
		return Constants.s(Na);
	}

	/** Electron Concentration At Thermal Equilibrium in P-Silicon */
	public static double n_P(double Na) { // Review 1.31
		System.out
				.print("Electron concentration at thermal eq in p-Si (cm^-3): ");
		return Constants.s(Math.pow(Constants.ni, 2) / Na);
	}

	/** Electron Concentration At Thermal Equilibrium in N-Silicon */
	public static double n_N(double Nd) { // Review 1.31
		System.out
				.print("Electron concentration at thermal eq in n-Si (cm^-3): ");
		return Constants.s(Nd);
	}

	/** Hole Concentration At Thermal Equilibrium In N-Silicon */
	public static double p_N(double Nd) { // Review 1.31
		System.out.print("Hole concentration at thermal eq in n-Si (cm^-3): ");
		return Constants.s(Math.pow(Constants.ni, 2) / Nd);
	}

	// Electron RMS velocity, l_col, t_col - Review 1.36

	/** Low Field Hole Mobility in P-Silicon */
	public static double u_pP(double Na) { // Review 1.42
		double denom = 1 + Math.pow(Na / (1.6 * Constants.EE(17)), .70);
		System.out.print("Low-field hole mobility in p-Si (cm^2/(V*s): ");
		return Constants.s(49.7 + (418.3 / denom));
	}

	/** Low Field Electron Mobility in P-Silicon */
	public static double u_nP(double Na) { // Review 1.42
		double denom = 1 + Math.pow(Na / (8.0 * Constants.EE(16)), .90);
		System.out.print("Low-field electron mobility in p-Si (cm^2/(V*s): ");
		return Constants.s(232 + (1180 / denom));
	}

	/** Low Field Electron Mobility in N-Silicon */
	public static double u_nN(double Nd) { // Review 1.43
		double denom = 1 + Math.pow(Nd / (1.3 * Constants.EE(17)), .91);
		System.out.print("Low-field electron mobility in n-Si (cm^2/(V*s): ");
		return Constants.s(92 + (1268 / denom));
	}

	/** Low Field Hole Mobility in N-Silicon */
	public static double u_pN(double Nd) { // Review 1.43
		double denom = 1 + Math.pow(Nd / (8.0 * Constants.EE(17)), 1.25);
		System.out.print("Low-field hole mobility in n-Si (cm^2/(V*s): ");
		return Constants.s(130 + (370 / denom));
	}

	// //start at 1.44 -> drift current density

	// conductivity -> 1.45

	// check beginning
	public static double rho_N(double Nd) {
		double u = u_nN(Nd);
		;
		double n = n_N(Nd);
		double denom = Constants.q * u * n;
		System.out.print("Resistivity in n-Si (ohm*cm): ");
		return Constants.s(1 / denom);
	}

	public static double rho_P(double Na) {
		double u = u_pP(Na);
		double p = p_P(Na);
		double denom = Constants.q * u * p;
		System.out.print("Resistivity in p-Si (ohm*cm): ");
		return Constants.s(1 / denom);
	}

	public static double R(double rho, double L, double A) {
		System.out.print("Resistance (ohm): ");
		return Constants.s(rho * L / A);
	}

	public static double v_drift(double u, double E) {
		System.out.print("Drift velocity (cm/s): ");
		return Constants.s(u * E);
	}

	public static double t_drift(double L, double v_drift) {
		System.out.print("Average drift time (s): ");
		return Constants.s(L / v_drift);
	}

	// check end

	public static double Dn(double u_n, double T) { // Review 1.51
		System.out.print("Electron diffusion coefficient: ");
		return Constants.s(u_n * Constants.kbtq);
	}

	public static double Dp(double u_p, double T) { // Review 1.51
		System.out.print("Hole diffusion coefficient: ");
		return Constants.s(u_p * Constants.kbtq);
	}

	public static double tau_recP(double Na) { // Review 1.57
		System.out
				.print("Minority carrier recombination lifetime in p-Si (s): ");
		return Constants.s(1 / (3.45 * Constants.EE(-12) * Na + 9.50
				* Constants.EE(-32) * Math.pow(Na, 2)));
	}

	public static double tau_recN(double Nd) { // Review 1.57
		System.out
				.print("Minority carrier recombination lifetime in n-Si (s): ");
		return Constants.s(1 / (7.80 * Constants.EE(-13) * Nd + 1.80
				* Constants.EE(-31) * Math.pow(Nd, 2)));
	}

	// tau_gen is 50 to 100 times tau_rec

	public static double L_nP(double D_nP, double tau_recP) { // Review 1.59
		System.out.print("Diffusion length of minority electrons in p-Si: ");
		return Constants.s(Math.sqrt(D_nP * tau_recP));
	}

	public static double L_pN(double D_pN, double tau_recN) { // Review 1.60
		System.out.print("Diffusion length of minority holes in n-Si: ");
		return Constants.s(Math.sqrt(D_pN * tau_recN));
	}

	public static double tau_diel(double sigma) { // Review 1.62
		System.out.print("Dielectric relaxation time: ");
		return Constants.s(Constants.esi / sigma);
	}

	public static double L_DP(double D_pP, double tau_dielP) { // Review 1.63
		System.out.print("Diffusion length of minority electrons in p-Si: ");
		return Constants.s(Math.sqrt(D_pP * tau_dielP));
	}

	public static double L_DN(double D_nN, double tau_dielN) { // Review 1.63
		System.out.print("Diffusion length of minority holes in n-Si: ");
		return Constants.s(Math.sqrt(D_nN * tau_dielN));
	}
}
