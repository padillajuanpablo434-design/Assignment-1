package gocycle.fault;

import gocycle.vehicle.ElectricBike;
import gocycle.vehicle.VehicleStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ElectricFaultMonitorTest {
    @Test void lowBatteryReportsFaultAndNotifiesObservers() {
        FaultService faults = new FaultService();
        MaintenanceCrew crew = new MaintenanceCrew();
        OperatorConsole operators = new OperatorConsole();
        faults.addListener(crew);
        faults.addListener(operators);
        ElectricBike bike = new ElectricBike("EB1", 85);
        new ElectricFaultMonitor(faults).monitor(bike);
        bike.setBatteryPercent(19);
        assertEquals(VehicleStatus.OUT_OF_SERVICE, bike.getStatus());
        assertEquals(FaultType.LOW_BATTERY, faults.getReports().get(0).getType());
        assertTrue(faults.getReports().get(0).isSelfReported());
        assertEquals(1, crew.getPendingJobs().size());
        assertEquals(1, operators.getAlerts().size());
    }
}
