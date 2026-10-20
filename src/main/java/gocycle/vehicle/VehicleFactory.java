package gocycle.vehicle;

/**
 * Creates vehicles without callers depending on the concrete vehicle classes (Factory Method).
 * 
 */
public interface VehicleFactory {

    /**
     * Creates a new vehicle of the given type.
     *
     * @param type the kind of vehicle to create
     * @param id   the unique id for the new vehicle
     * @return the new vehicle
     */
    Vehicle create(VehicleType type, String id);
}
