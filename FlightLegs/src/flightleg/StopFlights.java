package flightleg;

public class StopFlights extends CombinedFlight {

	public StopFlights(FlightLeg f1, FlightLeg f2) {
		super(f1, f2);
		if(!f1.getDestination().equals(f2.getOrigin()))
			throw new RuntimeException();
		
	}

	@Override
	public double getPrice() {
		
		return leg1.getPrice() + leg2.getPrice();
	}


} 
