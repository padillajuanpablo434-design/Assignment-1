package gocycle.trip;

import gocycle.station.DockingPoint;
import gocycle.vehicle.Vehicle;

/**
 * Test double whose docked vehicle and lock state are set by the test.
 */
class FakeDockingPoint extends DockingPoint {

    private Vehicle docked;
    private boolean locked;

    FakeDockingPoint(String id) {
        super(id);
    }

    /**
     * Puts a vehicle in this point, locked or not.
     */
    void set(Vehicle vehicle, boolean locked) {
        this.docked = vehicle;
        this.locked = locked;
    }

    @Override
    public Vehicle getDocked() {
        return docked;
    }

    @Override
    public boolean isFree() {
        return docked == null;
    }

    @Override
    public void dock(Vehicle vehicle) {
        set(vehicle, true);
    }

    @Override
    public boolean isLocked() {
        return docked != null && locked;
    }
}
