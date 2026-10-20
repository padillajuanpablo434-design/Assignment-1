package gocycle.vehicle;

public final class ElectricScooterCreator extends VehicleCreator {
    @Override protected Vehicle createVehicle(String id) { return new ElectricScooter(id, 100); }
}
