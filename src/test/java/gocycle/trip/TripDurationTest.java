package gocycle.trip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import gocycle.rider.CasualRider;
import java.time.Duration;
import java.time.Instant;
import org.junit.jupiter.api.Test;

class TripDurationTest {

    private static final Instant T0 = Instant.parse("2026-10-15T09:00:00Z");

    private final FakeVehicle vehicle = new FakeVehicle("V1", true);
    private final FakeDockingPoint point = new FakeDockingPoint("D1");
    private final Trip trip = new Trip("T1", new CasualRider("R1", "Casey", "casey@example.com"),
            vehicle, new FakePaymentMethod());

    private long billedMinutesFor(Duration rideLength) {
        trip.start(T0);
        point.set(vehicle, true);
        return trip.end(point, T0.plus(rideLength)).getMinutes();
    }

    @Test
    void wholeMinutesAreNotRoundedUp() {
        assertEquals(10, billedMinutesFor(Duration.ofMinutes(10)));
    }

    @Test
    void oneSecondOverRoundsUpToNextMinute() {
        assertEquals(11, billedMinutesFor(Duration.ofMinutes(10).plusSeconds(1)));
    }

    @Test
    void secondsShortOfAMinuteRoundUp() {
        assertEquals(10, billedMinutesFor(Duration.ofMinutes(9).plusSeconds(59)));
    }

    @Test
    void anyFractionOfASecondRoundsUp() {
        assertEquals(1, billedMinutesFor(Duration.ofMillis(1)));
    }

    @Test
    void zeroLengthTripIsZeroMinutes() {
        assertEquals(0, billedMinutesFor(Duration.ZERO));
    }

    @Test
    void endBeforeStartIsRejected() {
        trip.start(T0);
        point.set(vehicle, true);

        assertThrows(IllegalArgumentException.class, () -> trip.end(point, T0.minusSeconds(1)));
    }
}
