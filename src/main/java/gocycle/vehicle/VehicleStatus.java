package gocycle.vehicle;

/**
 * The rental state of a vehicle.
 */
public enum VehicleStatus {
    /** Docked and ready to be rented. */
    AVAILABLE,
    /** Currently on a trip. */
    IN_USE,
    /** Broken or under maintenance; cannot be rented. */
    OUT_OF_SERVICE
}
