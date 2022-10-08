package flightleg;

public class SimpleFlightLeg implements FlightLeg {

	
	private String origin;
	private String destination;
	private double price;
	private int distance;
	public SimpleFlightLeg(String origin, String destination, double price, int distance) {
		this.origin = origin;
		this.destination = destination;
		this.price = price;
		this.distance = distance;
	
	}
	@Override
	public String getOrigin() {
		return origin;
	
	}
	@Override
	public String getDestination() {
		return destination;
	}
	
	@Override
	public double getPrice() {
		return price;
	
	}
	@Override 
	public int getDistance() {
		return distance;
	}
	
}
