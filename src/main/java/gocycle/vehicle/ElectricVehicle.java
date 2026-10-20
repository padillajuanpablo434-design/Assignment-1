package gocycle.vehicle;

/**
 * A vehicle with a battery (e-bike or e-scooter).
 * Electric vehicles cannot be rented below 20% battery.
 */
public abstract class ElectricVehicle extends Vehicle {

    private int batteryPercent;
    private final java.util.List<java.util.function.IntConsumer> batteryListeners = new java.util.ArrayList<>();

    /** Subscribe to battery threshold transitions for vehicle telemetry. */
    public void addBatteryListener(java.util.function.IntConsumer listener) {
        batteryListeners.add(java.util.Objects.requireNonNull(listener, "listener"));
    }

    /**
     * @param id             the unique vehicle id
     * @param batteryPercent the starting charge, from 0 to 100
     */
    protected ElectricVehicle(String id, int batteryPercent) {
        super(id);
        setBatteryPercent(batteryPercent);
    }

    /**
     * @return the current charge, from 0 to 100
     */
    public int getBatteryPercent() {
        return batteryPercent;
    }


    /**
     * 
     * @param batteryPercent set the charge between 0 to 100
     */
    public void setBatteryPercent(int batteryPercent) {
        if (batteryPercent < 0 || batteryPercent > 100) {
            throw new IllegalArgumentException("Battery must be between 0 and 100");
        }
        int previous = this.batteryPercent;
        this.batteryPercent = batteryPercent;
        if (previous >= 20 && batteryPercent < 20) {
            for (java.util.function.IntConsumer listener : java.util.List.copyOf(batteryListeners)) {
                listener.accept(batteryPercent);
            }
        }
    }

    /**    (non-Javadoc)
     * 
     * @see gocycle.vehicle.Vehicle#isRentable()
     * @return rentability status based on availability and battery charge
     */
    @Override
    public boolean isRentable() {
        return getStatus() == VehicleStatus.AVAILABLE && batteryPercent >= 20;
    }
}
