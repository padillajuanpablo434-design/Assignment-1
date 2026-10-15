package gocycle.trip;

import gocycle.vehicle.Vehicle;

/**
 * Test double whose rentability is set by the test.
 */
class FakeVehicle extends Vehicle {

    private boolean rentable;

    FakeVehicle(String id, boolean rentable) {
        super(id);
        this.rentable = rentable;
    }

    @Override
    public boolean isRentable() {
        return rentable;
    }

    void setRentable(boolean rentable) {
        this.rentable = rentable;
    }
}
