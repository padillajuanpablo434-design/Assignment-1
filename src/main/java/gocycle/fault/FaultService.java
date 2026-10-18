package gocycle.fault;

import gocycle.vehicle.Vehicle;
import gocycle.vehicle.VehicleStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Observable subject for vehicle fault notifications (Observer pattern). */
public final class FaultService {
    private final List<FaultListener> listeners = new ArrayList<>();
    private final List<FaultReport> reports = new ArrayList<>();

    public void addListener(FaultListener listener) {
        Objects.requireNonNull(listener, "listener");
        if (!listeners.contains(listener)) listeners.add(listener);
    }

    public void removeListener(FaultListener listener) {
        listeners.remove(listener);
    }

    /** Marks the vehicle out of service, records a fault, and notifies subscribers. */
    public FaultReport report(Vehicle vehicle, FaultType type, String description, boolean selfReported) {
        Objects.requireNonNull(vehicle, "vehicle");
        Objects.requireNonNull(type, "type");
        FaultReport report = new FaultReport(vehicle, type,
                Objects.requireNonNull(description, "description"), selfReported);
        vehicle.setStatus(VehicleStatus.OUT_OF_SERVICE);
        reports.add(report);
        // Work from a snapshot so observers can unsubscribe while receiving an update.
        RuntimeException notificationFailure = null;
        for (FaultListener listener : List.copyOf(listeners)) {
            try {
                listener.onFault(report);
            } catch (RuntimeException ex) {
                // One broken listener must not prevent other observers from receiving the alert.
                if (notificationFailure == null) {
                    notificationFailure = new IllegalStateException("One or more fault notifications failed");
                }
                notificationFailure.addSuppressed(ex);
            }
        }
        if (notificationFailure != null) throw notificationFailure;
        return report;
    }

    public List<FaultReport> getReports() {
        return List.copyOf(reports);
    }
}
