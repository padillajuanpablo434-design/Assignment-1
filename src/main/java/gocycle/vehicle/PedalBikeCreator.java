package gocycle.vehicle;

public final class PedalBikeCreator extends VehicleCreator {
    @Override protected Vehicle createVehicle(String id) { return new PedalBike(id); }
}
