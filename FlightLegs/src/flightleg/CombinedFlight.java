package flightleg;

public abstract class CombinedFlight implements FlightLeg {

	protected FlightLeg leg1;
	protected FlightLeg leg2;

	public CombinedFlight(FlightLeg f1, FlightLeg f2) {
		leg1 = f1;
		leg2 = f2;
	}

	@Override
	public String getOrigin() {
		return leg1.getOrigin();
	}

	@Override
	public String getDestination() {
		return leg2.getDestination();
	}

	@Override
	public int getDistance() {
		return leg1.getDistance() + leg2.getDistance();
	}

}