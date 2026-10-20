package gocycle.app;

import gocycle.fault.*;
import gocycle.payment.*;
import gocycle.rider.CasualRider;
import gocycle.station.DockingPoint;
import gocycle.trip.*;
import gocycle.vehicle.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;

/**
 * GoCycle end-to-end demonstration. TEMPORARY demo-specific vehicle/dock
 * adapters must be replaced with Person B's concrete classes before submission.
 */
public final class Main {
    private Main() { }

    public static void main(String[] args) {
        Zone downtown = new Zone("Z1", "Downtown",
                Set.of(CreditCardPayment.class, PhoneWalletPayment.class));
        CreditCardPayment card = new CreditCardPayment("Credit card ending 4242");
        PaymentMethod payment = new ZoneRestrictedPaymentMethod(downtown, card);
        Vehicle vehicle = new PedalBike("BIKE-001");
        DockingPoint returnDock = new DemoDockingPoint("DOCK-002");
        Trip trip = new Trip("TRIP-001",
                new CasualRider("R-001", "Demo Rider", "rider@example.com"), vehicle, payment);

        Instant start = Instant.parse("2026-10-18T12:00:00Z");
        trip.start(start);
        System.out.println("Started trip " + trip.getId() + " / " + trip.getState());
        try {
            trip.end(returnDock, start.plus(25, ChronoUnit.MINUTES));
            throw new AssertionError("Trip should not finish without a locked vehicle");
        } catch (VehicleNotLockedException expected) {
            System.out.println("Return rejected: lock not confirmed; trip remains " + trip.getState());
        }

        ((DemoDockingPoint) returnDock).dock(vehicle);
        Receipt receipt = trip.end(returnDock, start.plus(26, ChronoUnit.MINUTES));
        new EmailReceiptSender().send(receipt);
        System.out.println("Hold released: " + !card.hasActiveHold());

        FaultService faults = new FaultService();
        MaintenanceCrew maintenance = new MaintenanceCrew();
        OperatorConsole controlRoom = new OperatorConsole();
        faults.addListener(maintenance);
        faults.addListener(controlRoom);
        faults.report(vehicle, FaultType.FLAT_TIRE, "Rider reported a flat tire after return", false);
        System.out.println("Vehicle after fault: " + vehicle.getStatus());
        System.out.println("Maintenance jobs: " + maintenance.getPendingJobs().size());
        System.out.println("Operator alerts: " + controlRoom.getAlerts().size());
        System.out.println("Transit card allowed downtown? "
                + downtown.allows(new TransitCardPayment("Transit card ending 0012")));
    }

    /** Temporary until Person B finishes physical-lock simulation. */
    private static final class DemoDockingPoint extends DockingPoint {
        private Vehicle stored;
        DemoDockingPoint(String id) { super(id); }
        @Override public Vehicle getDocked() { return stored; }
        @Override public boolean isFree() { return stored == null; }
        @Override public void dock(Vehicle vehicle) {
            if (!isFree()) throw new IllegalStateException("Dock is already occupied");
            this.stored = vehicle;
        }
        @Override public boolean isLocked() { return stored != null; }
    }
}
