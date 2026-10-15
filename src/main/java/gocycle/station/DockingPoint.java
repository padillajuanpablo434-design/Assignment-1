package gocycle.station;

import gocycle.vehicle.Vehicle;

/**
 * A single dock at a station that holds at most one vehicle.
 * <p>
 * Stub for Person B, who owns stations and the return flow.
 */
public class DockingPoint {

    private final String id;
    private Vehicle docked;

    /**
     * Creates an empty docking point.
     *
     * @param id the unique docking point id
     */
    public DockingPoint(String id) {
        this.id = id;
    }

    /**
     * @return the unique docking point id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the docked vehicle, or {@code null} if the point is empty
     */
    public Vehicle getDocked() {
        return docked;
    }

    /**
     * @return {@code true} if no vehicle is docked here
     */
    public boolean isFree() {
        throw new UnsupportedOperationException("TODO: Person B");
    }

    /**
     * Places a vehicle in this point.
     *
     * @param vehicle the vehicle being returned
     */
    public void dock(Vehicle vehicle) {
        throw new UnsupportedOperationException("TODO: Person B");
    }

    /**
     * Reports whether the docked vehicle is confirmed locked.
     * A trip ends, and charging stops, only once this is {@code true}.
     *
     * @return {@code true} if a vehicle is docked and locked
     */
    public boolean isLocked() {
        throw new UnsupportedOperationException("TODO: Person B");
    }
}
