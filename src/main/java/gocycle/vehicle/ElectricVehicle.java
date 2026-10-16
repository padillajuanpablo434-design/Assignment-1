package gocycle.vehicle;

/**
 * A vehicle with a battery (e-bike or e-scooter).
 * <p>
 * Electric vehicles cannot be rented below 20% battery.
 * Stub for Person B, who owns the vehicle hierarchy.
 */
public abstract class ElectricVehicle extends Vehicle {

    private int batteryPercent;

    /**
     * @param id             the unique vehicle id
     * @param batteryPercent the starting charge, from 0 to 100
     */
    protected ElectricVehicle(String id, int batteryPercent) {
        super(id);
        this.batteryPercent = batteryPercent;
    }

    /**
     * @return the current charge, from 0 to 100
     */
    public int getBatteryPercent() {
        return batteryPercent;
    }
}
