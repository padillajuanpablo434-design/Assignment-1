package gocycle.app;

import gocycle.fault.*;
import gocycle.payment.*;
import gocycle.rider.CasualRider;
import gocycle.station.*;
import gocycle.trip.*;
import gocycle.vehicle.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** Runnable end-to-end console demonstration of GoCycle's implemented core. */
public final class Main {
    private Main() { }

    public static void main(String[] args) {
        Zone downtown = new Zone("Z1", "Downtown", Set.of(CreditCardPayment.class, PhoneWalletPayment.class));
        CreditCardPayment card = new CreditCardPayment("Credit card ending 4242");
        PaymentMethod payment = new ZoneRestrictedPaymentMethod(downtown, card);
        VehicleFactory factory = new VehicleFactoryImpl();
        Vehicle vehicle = factory.create(VehicleType.PEDAL_BIKE, "BIKE-001");
        DockingPoint departure = new DockingPoint("DOCK-001");
        DockingPoint returnDock = new DockingPoint("DOCK-002");
        departure.dock(vehicle);
        Station startStation = new Station("S1", "Central", "Calgary Centre", List.of(departure));
        Station returnStation = new Station("S2", "Library", "Near Library", List.of(returnDock));
        Trip trip = new Trip("TRIP-001",
                new CasualRider("R-001", "Demo Rider", "rider@example.com"), vehicle, payment);

        departure.undock();
        Instant start = Instant.parse("2026-10-18T12:00:00Z");
        trip.start(start);
        System.out.println("Started trip " + trip.getId() + " / " + trip.getState());
        try {
            trip.end(returnDock, start.plus(25, ChronoUnit.MINUTES));
            throw new AssertionError("An unlocked vehicle must not end a trip");
        } catch (VehicleNotLockedException expected) {
            System.out.println("Return rejected: lock not confirmed; trip remains " + trip.getState());
        }
        Station selected = StationFinder.nearestAvailable(
                new StationFinder.Coordinates(51.0400, -114.0700),
                Map.of(startStation, new StationFinder.Coordinates(51.0460, -114.0770),
                       returnStation, new StationFinder.Coordinates(51.0420, -114.0690))).orElseThrow();
        System.out.println("Nearest station with a free point: " + selected.getName());
        returnDock.dock(vehicle);
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

        ElectricVehicle eBike = (ElectricVehicle) factory.create(VehicleType.E_BIKE, "EBIKE-002");
        new ElectricFaultMonitor(faults).monitor(eBike);
        eBike.setBatteryPercent(19); // Onboard telemetry automatically notifies the fault system.
        System.out.println("Low-battery e-bike rentable? " + eBike.isRentable());
        System.out.println("Electric self-reported fault count: " + faults.getReports().size());
    }
}
