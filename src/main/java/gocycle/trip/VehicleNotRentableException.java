package gocycle.trip;

/**
 * Thrown when a trip is started on a vehicle that cannot be rented,
 * e.g. it is out of service or its battery is below 20%.
 */
public class VehicleNotRentableException extends RuntimeException {

    /**
     * @param vehicleId the vehicle that was refused
     */
    public VehicleNotRentableException(String vehicleId) {
        super("Vehicle " + vehicleId + " cannot be rented");
    }
}
