package gocycle.trip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import gocycle.pricing.Money;
import gocycle.rider.CasualRider;
import gocycle.vehicle.VehicleStatus;
import java.time.Duration;
import java.time.Instant;
import org.junit.jupiter.api.Test;

class TripEndTest {

    private static final Instant T0 = Instant.parse("2026-10-15T09:00:00Z");
    private static final Instant T10 = T0.plus(Duration.ofMinutes(10));

    private final FakeVehicle vehicle = new FakeVehicle("V1", true);
    private final FakePaymentMethod payment = new FakePaymentMethod();
    private final FakeDockingPoint point = new FakeDockingPoint("D1");
    private final Trip trip = new Trip("T1", new CasualRider("R1", "Casey", "casey@example.com"),
            vehicle, payment);

    @Test
    void endChargesReleasesHoldAndFreesVehicle() {
        trip.start(T0);
        point.set(vehicle, true);

        Receipt receipt = trip.end(point, T10);

        assertEquals(TripState.COMPLETED, trip.getState());
        assertEquals(Money.of(5, 0), payment.chargedAmount);
        assertTrue(payment.holdReleased);
        assertEquals(VehicleStatus.AVAILABLE, vehicle.getStatus());
        assertEquals(T10, trip.getEndTime());
        assertEquals(Money.of(5, 0), receipt.getTotal());
    }

    @Test
    void endRejectedWhenNotLockedAndTripStaysActive() {
        trip.start(T0);
        point.set(vehicle, false);

        assertThrows(VehicleNotLockedException.class, () -> trip.end(point, T10));
        assertEquals(TripState.ACTIVE, trip.getState());
        assertNull(payment.chargedAmount);
        assertFalse(payment.holdReleased);
        assertEquals(VehicleStatus.IN_USE, vehicle.getStatus());
    }

    @Test
    void endRejectedWhenDifferentVehicleIsLockedAtPoint() {
        trip.start(T0);
        point.set(new FakeVehicle("V2", true), true);

        assertThrows(VehicleNotLockedException.class, () -> trip.end(point, T10));
        assertEquals(TripState.ACTIVE, trip.getState());
    }

    @Test
    void lockedAfterFailedAttemptEndsTrip() {
        trip.start(T0);
        point.set(vehicle, false);
        assertThrows(VehicleNotLockedException.class, () -> trip.end(point, T10));

        point.set(vehicle, true);
        trip.end(point, T10);

        assertEquals(TripState.COMPLETED, trip.getState());
    }

    @Test
    void declinedChargeKeepsTripActiveAndHoldInPlace() {
        trip.start(T0);
        point.set(vehicle, true);
        payment.approveCharge = false;

        assertThrows(PaymentDeclinedException.class, () -> trip.end(point, T10));
        assertEquals(TripState.ACTIVE, trip.getState());
        assertFalse(payment.holdReleased);
    }

    @Test
    void cannotEndBeforeStart() {
        point.set(vehicle, true);

        assertThrows(IllegalStateException.class, () -> trip.end(point, T10));
    }

    @Test
    void cannotEndTwice() {
        trip.start(T0);
        point.set(vehicle, true);
        trip.end(point, T10);

        assertThrows(IllegalStateException.class, () -> trip.end(point, T10));
    }

    @Test
    void receiptBreaksDownLongTrip() {
        trip.start(T0);
        point.set(vehicle, true);

        Receipt receipt = trip.end(point, T0.plus(Duration.ofMinutes(121)));

        assertEquals("T1", receipt.getTripId());
        assertEquals("V1", receipt.getVehicleId());
        assertEquals(121, receipt.getMinutes());
        assertEquals(Money.of(1, 50), receipt.getUnlockFee());
        assertEquals(Money.of(42, 35), receipt.getTimeCharge());
        assertEquals(Money.of(5, 0), receipt.getSurcharge());
        assertTrue(receipt.hasSurcharge());
        assertEquals(Money.of(48, 85), receipt.getTotal());
        assertEquals(payment.chargedAmount, receipt.getTotal());
    }

    @Test
    void receiptHasNoSurchargeForShortTrip() {
        trip.start(T0);
        point.set(vehicle, true);

        Receipt receipt = trip.end(point, T10);

        assertFalse(receipt.hasSurcharge());
        assertEquals(Money.ZERO, receipt.getSurcharge());
    }
}
