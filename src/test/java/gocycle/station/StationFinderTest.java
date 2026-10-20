package gocycle.station;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class StationFinderTest {
    @Test void redirectsToNearestFreeStationAndSkipsFullStation() {
        DockingPoint busy = new DockingPoint("B1");
        busy.dock(new gocycle.vehicle.PedalBike("P1"));
        Station full = new Station("FULL", "Full", "Downtown", List.of(busy));
        Station near = new Station("NEAR", "Near", "Downtown", List.of(new DockingPoint("N1")));
        Station far = new Station("FAR", "Far", "Downtown", List.of(new DockingPoint("F1")));
        Map<Station, StationFinder.Coordinates> stations = Map.of(
                full, new StationFinder.Coordinates(51.0400, -114.0700),
                near, new StationFinder.Coordinates(51.0410, -114.0690),
                far, new StationFinder.Coordinates(51.0800, -114.1500));
        assertEquals(near, StationFinder.nearestAvailable(
                new StationFinder.Coordinates(51.0401, -114.0700), stations).orElseThrow());
        assertFalse(full.hasFreePoint());
    }
    @Test void returnsEmptyWhenAllStationsAreFull() {
        DockingPoint point = new DockingPoint("B1");
        point.dock(new gocycle.vehicle.PedalBike("P1"));
        Station full = new Station("FULL", "Full", "Downtown", List.of(point));
        assertTrue(StationFinder.nearestAvailable(new StationFinder.Coordinates(51, -114),
                Map.of(full, new StationFinder.Coordinates(51, -114))).isEmpty());
    }
}
