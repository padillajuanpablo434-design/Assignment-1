package gocycle.vehicle;

public final class ElectricBikeCreator extends VehicleCreator {
    @Override protected Vehicle createVehicle(String id) { return new ElectricBike(id, 100); }
}
