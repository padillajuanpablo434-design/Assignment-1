package gocycle.fault;

import java.util.ArrayList;
import java.util.List;

/** Observer which collects jobs for the maintenance team. */
public final class MaintenanceCrew implements FaultListener {
    private final List<FaultReport> pendingJobs = new ArrayList<>();

    @Override
    public void onFault(FaultReport report) {
        pendingJobs.add(report);
        System.out.println("[Maintenance] Vehicle " + report.getVehicle().getId()
                + " requires attention: " + report.getType());
    }

    public List<FaultReport> getPendingJobs() {
        return List.copyOf(pendingJobs);
    }
}
