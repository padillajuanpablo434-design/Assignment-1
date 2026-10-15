package gocycle.fault;

import gocycle.vehicle.Vehicle;

/**
 * A fault raised against a vehicle, either by a rider or by the vehicle itself.
 * <p>
 * Stub for Person C, who owns fault reporting.
 */
public class FaultReport {

    private final Vehicle vehicle;
    private final FaultType type;
    private final String description;
    private final boolean selfReported;

    /**
     * @param vehicle      the faulty vehicle
     * @param type         the kind of fault
     * @param description  free-text details
     * @param selfReported {@code true} if an electric vehicle raised it, {@code false} if a rider did
     */
    public FaultReport(Vehicle vehicle, FaultType type, String description, boolean selfReported) {
        this.vehicle = vehicle;
        this.type = type;
        this.description = description;
        this.selfReported = selfReported;
    }

    /**
     * @return the faulty vehicle
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * @return the kind of fault
     */
    public FaultType getType() {
        return type;
    }

    /**
     * @return free-text details
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return {@code true} if the vehicle raised the report itself
     */
    public boolean isSelfReported() {
        return selfReported;
    }
}
