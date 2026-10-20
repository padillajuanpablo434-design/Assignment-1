package gocycle.vehicle;

/** Creator declares the Factory Method; subclasses decide the concrete Vehicle product. */
public abstract class VehicleCreator {
    public final Vehicle create(String id) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Vehicle id is required");
        return createVehicle(id);
    }
    protected abstract Vehicle createVehicle(String id);
}
