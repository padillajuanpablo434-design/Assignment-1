package gocycle.trip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import gocycle.rider.CasualRider;
import gocycle.vehicle.VehicleStatus;
import java.time.Instant;
import org.junit.jupiter.api.Test;

class TripStartTest {

    private static final Instant T0 = Instant.parse("2026-10-15T09:00:00Z");

    private final FakeVehicle vehicle = new FakeVehicle("V1", true);
    private final FakePaymentMethod payment = new FakePaymentMethod();
    private final Trip trip = new Trip("T1", new CasualRider("R1", "Casey", "casey@example.com"),
            vehicle, payment);

    @Test
    void newTripIsNotStarted() {
        assertEquals(TripState.NOT_STARTED, trip.getState());
    }

    @Test
    void startPlacesDepositHoldAndMarksVehicleInUse() {
        trip.start(T0);

        assertEquals(TripState.ACTIVE, trip.getState());
        assertEquals(Trip.DEPOSIT_HOLD, payment.heldAmount);
        assertEquals(VehicleStatus.IN_USE, vehicle.getStatus());
        assertEquals(T0, trip.getStartTime());
    }

    @Test
    void startRejectedWhenVehicleNotRentable() {
        vehicle.setRentable(false);

        assertThrows(VehicleNotRentableException.class, () -> trip.start(T0));
        assertEquals(TripState.NOT_STARTED, trip.getState());
        assertNull(payment.heldAmount);
        assertEquals(VehicleStatus.AVAILABLE, vehicle.getStatus());
    }

    @Test
    void startRejectedWhenDepositHoldDeclined() {
        payment.approveHold = false;

        assertThrows(PaymentDeclinedException.class, () -> trip.start(T0));
        assertEquals(TripState.NOT_STARTED, trip.getState());
        assertEquals(VehicleStatus.AVAILABLE, vehicle.getStatus());
    }

    @Test
    void cannotStartTwice() {
        trip.start(T0);

        assertThrows(IllegalStateException.class, () -> trip.start(T0));
    }
}
