package gocycle.vehicle;

/**
 * A rentable GoCycle vehicle (pedal bike, e-bike or e-scooter).
 * 
 * 
 */
public abstract class Vehicle {

    private final String id;
    private VehicleStatus status;

    /**
     * Creates a vehicle that is {@link VehicleStatus#AVAILABLE}.
     *
     * @param id the unique vehicle id
     */
    protected Vehicle(String id) {
        this.id = id;
        this.status = VehicleStatus.AVAILABLE;
    }

    /**
     * Decides whether a rider may take this vehicle now.
     *
     * @return {@code true} if the vehicle can be rented
     */
    public abstract boolean isRentable();

    /**
     * @return the unique vehicle id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the current rental state
     */
    public VehicleStatus getStatus() {
        return status;
    }

    /**
     * Changes the rental state, e.g. to take the vehicle out of service.
     *
     * @param status the new state
     */
    public void setStatus(VehicleStatus status) {
        this.status = status;
    }
}
