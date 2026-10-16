package gocycle.fault;

/**
 * Notified when a fault is reported, e.g. the maintenance crew or the control room (Observer).
 * <p>
 * Stub for Person C.
 */
public interface FaultListener {

    /**
     * Called once for each new fault report.
     *
     * @param report the fault that was reported
     */
    void onFault(FaultReport report);
}
