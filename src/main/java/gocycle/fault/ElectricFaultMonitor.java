package gocycle.fault;

import gocycle.vehicle.ElectricVehicle;
import java.util.Objects;

/** Connects electric-vehicle telemetry to the Observer-based fault reporting service. */
public final class ElectricFaultMonitor {
    private final FaultService faultService;

    public ElectricFaultMonitor(FaultService faultService) {
        this.faultService = Objects.requireNonNull(faultService, "faultService");
    }

    /** Register once. New battery readings below 20% generate a low-battery fault automatically. */
    public void monitor(ElectricVehicle vehicle) {
        Objects.requireNonNull(vehicle, "vehicle");
        vehicle.addBatteryListener(percent -> {
            if (percent < 20) {
                faultService.report(vehicle, FaultType.LOW_BATTERY,
                        "Electric vehicle battery fell below 20% (" + percent + "%)", true);
            }
        });
        if (vehicle.getBatteryPercent() < 20) {
            faultService.report(vehicle, FaultType.LOW_BATTERY,
                    "Electric vehicle initially below 20% (" + vehicle.getBatteryPercent() + "%)", true);
        }
    }

    /** Called by an onboard diagnostic system when the vehicle flags an electrical fault. */
    public void onboardElectricalFault(ElectricVehicle vehicle, String details) {
        faultService.report(Objects.requireNonNull(vehicle, "vehicle"),
                FaultType.ELECTRICAL_FAULT, Objects.requireNonNull(details, "details"), true);
    }
}
