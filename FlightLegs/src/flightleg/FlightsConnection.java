package flightleg;

public class FlightsConnection extends CombinedFlight implements FlightLeg {
	
	private int percentageDiscount;

	public FlightsConnection(FlightLeg f1, FlightLeg f2, int percentageDiscount) {
		super(f1, f2);
		this.percentageDiscount = percentageDiscount;
		
	}

	@Override
	public double getPrice() {
		return (leg1.getPrice()+ leg2.getPrice())*(100-percentageDiscount)/100;
	}

}
