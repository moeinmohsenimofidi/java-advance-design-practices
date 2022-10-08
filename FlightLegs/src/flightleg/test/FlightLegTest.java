package flightleg.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import flightleg.FlightLeg;
import flightleg.FlightsConnection;
import flightleg.SimpleFlightLeg;
import flightleg.StopFlights;


class FlightLegTest {

	@Test
	void testConectionFlight() {
		FlightLeg f1 = new SimpleFlightLeg("GRU","LGW",1000.0,2000);
		FlightLeg f2 = new SimpleFlightLeg("LHR","MPX",500.0,500);
		FlightLeg connecting = new FlightsConnection(f1, f2, 40);
		
		assertEquals("GRU", connecting.getOrigin());
		assertEquals("MPX", connecting.getDestination());
		assertEquals(2500, connecting.getDistance());
		assertEquals(900.0, connecting.getPrice());
	}

	@Test
	void testStopFlight() {
		FlightLeg f1 = new SimpleFlightLeg("GRU","HTW",1000.0,2000);
		FlightLeg f2 = new SimpleFlightLeg("HTW","MPX",500.0,500);
		FlightLeg stopFlight = new StopFlights(f1, f2);
		
		assertEquals("GRU", stopFlight.getOrigin());
		assertEquals("MPX", stopFlight.getDestination());
		assertEquals(2500, stopFlight.getDistance());
		assertEquals(1500.0, stopFlight.getPrice());
	}

	@Test
	void testStopFlightWithNonMachingAirports() {
		FlightLeg f1 = new SimpleFlightLeg("GRU","LGW",1000.0,2000);
		FlightLeg f2 = new SimpleFlightLeg("HTW","MPX",500.0,500);
		assertThrows(RuntimeException.class, 
				() -> new StopFlights(f1, f2));
	}

	@Test
	void complicatedFlight() {
		FlightLeg f1 = new SimpleFlightLeg("GRU","HTW",1000.0,2000);
		FlightLeg f2 = new SimpleFlightLeg("HTW","MPX",500.0,500);
		FlightLeg f3 = new SimpleFlightLeg("MPX","NRT",1500.0,2500);
		FlightLeg stopFlight = new StopFlights(f1, f2);
		FlightLeg allCombined = new FlightsConnection(stopFlight, f3, 20);
		
		assertEquals("GRU", allCombined.getOrigin());
		assertEquals("NRT", allCombined.getDestination());
		assertEquals(5000, allCombined.getDistance());
		assertEquals(2400.0, allCombined.getPrice());
	}	
}
