package logic;

/**
 * Represents a vehicle, and stores the information necessary to
 * calculate carbon-dioxide emissions.
 * 
 * @author Rodrigo Roa (Previously Isak)
 * @version 2014-04-29
 *
 */
public class Vehicle {
	
	private final String name;
	private final int km_per_h; 
	private final double CO2_per_km;
	
//	/**
//	 * It is possible to calculate CO2_per_km per liter of gasoline, but adding additional controls 
//	 * to the program made it more difficult to use and less practical.
//	 */
//	private final double CO2_PER_L = 236; //Source: http://www.orebro.se/download/18.2e96e73312b3224f4fd80004412/Koldioxidjakten.pdf
//	
//	public Vehicle(String name, int km_per_h, double l_per_100km) {
//		this.name = name;
//		this.km_per_h = km_per_h;
//		this.CO2_per_km = (int) CO2_PER_L * l_per_100km / 100;
//	}
	
	public Vehicle(String name, int km_per_h, double CO2g_per_km) {
		this.name = name;
		this.km_per_h = km_per_h;
		this.CO2_per_km = CO2g_per_km;
	}
	public Vehicle(String name, int km_per_h) {
		this(name, km_per_h, 0);
	}

	public int getkmperh() {
		return km_per_h;
	}
	
	public double getCO2gperkm() {
		return CO2_per_km;
	}
	
	@Override
	public String toString(){
		return name;
	}
}
