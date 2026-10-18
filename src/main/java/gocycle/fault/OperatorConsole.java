package gocycle.fault;

import java.util.ArrayList;
import java.util.List;

/** Observer which records fault alerts for the control room. */
public final class OperatorConsole implements FaultListener {
    private final List<FaultReport> alerts = new ArrayList<>();

    @Override
    public void onFault(FaultReport report) {
        alerts.add(report);
        System.out.println("[Operator] Fault alert: vehicle "
                + report.getVehicle().getId() + " / " + report.getDescription());
    }

    public List<FaultReport> getAlerts() {
        return List.copyOf(alerts);
    }
}
