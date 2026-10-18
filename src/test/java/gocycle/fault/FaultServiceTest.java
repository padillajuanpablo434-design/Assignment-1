package gocycle.fault;

import gocycle.vehicle.Vehicle;
import gocycle.vehicle.VehicleStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FaultServiceTest {
    private static class Bike extends Vehicle {
        Bike() { super("BIKE-1"); }
        @Override public boolean isRentable() { return getStatus() == VehicleStatus.AVAILABLE; }
    }

    @Test void notifiesBothObserversAndDisablesVehicle() {
        FaultService service = new FaultService();
        MaintenanceCrew crew = new MaintenanceCrew();
        OperatorConsole console = new OperatorConsole();
        service.addListener(crew);
        service.addListener(console);
        Bike bike = new Bike();
        service.report(bike, FaultType.BROKEN_BRAKES, "Brake lever broken", false);
        assertEquals(VehicleStatus.OUT_OF_SERVICE, bike.getStatus());
        assertFalse(bike.isRentable());
        assertEquals(1, crew.getPendingJobs().size());
        assertEquals(1, console.getAlerts().size());
        assertEquals(1, service.getReports().size());
    }

    @Test void unsubscribedObserverReceivesNoFutureAlerts() {
        FaultService service = new FaultService();
        MaintenanceCrew crew = new MaintenanceCrew();
        service.addListener(crew);
        service.removeListener(crew);
        service.report(new Bike(), FaultType.FLAT_TIRE, "Puncture", false);
        assertTrue(crew.getPendingJobs().isEmpty());
    }
}
