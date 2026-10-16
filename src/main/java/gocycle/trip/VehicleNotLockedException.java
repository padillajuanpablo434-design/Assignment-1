package gocycle.trip;

/**
 * Thrown when a trip is ended but the vehicle is not confirmed locked at the docking point.
 * The trip stays active and the rider keeps being charged.
 */
public class VehicleNotLockedException extends RuntimeException {

    /**
     * @param vehicleId      the vehicle being returned
     * @param dockingPointId the docking point it was returned to
     */
    public VehicleNotLockedException(String vehicleId, String dockingPointId) {
        super("Vehicle " + vehicleId + " is not locked at docking point " + dockingPointId);
    }
}
