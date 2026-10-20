package gocycle.trip;

import gocycle.fault.FaultService;
import gocycle.fault.FaultType;
import gocycle.rider.CasualRider;
import gocycle.vehicle.VehicleStatus;
import java.time.Instant;
import java.time.Duration;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FaultyVehicleReturnTest {
    @Test void faultRaisedDuringTripSurvivesConfirmedReturn() {
        gocycle.vehicle.PedalBike vehicle = new gocycle.vehicle.PedalBike("V-BROKEN");
        FakePaymentMethod payment = new FakePaymentMethod();
        FakeDockingPoint dock = new FakeDockingPoint("D1");
        Trip trip = new Trip("T1", new CasualRider("R1", "Student", "s@example.com"), vehicle, payment);
        Instant start = Instant.parse("2026-10-19T12:00:00Z");
        trip.start(start);
        new FaultService().report(vehicle, FaultType.BROKEN_BRAKES, "Reported during trip", false);
        dock.set(vehicle, true);
        Receipt receipt = trip.end(dock, start.plus(Duration.ofMinutes(15)));
        assertEquals(TripState.COMPLETED, trip.getState());
        assertEquals(VehicleStatus.OUT_OF_SERVICE, vehicle.getStatus());
        assertFalse(vehicle.isRentable());
        assertNotNull(receipt);
    }
}
