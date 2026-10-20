package gocycle.station;

import java.util.List;

/**
 * A docking station where riders take and return vehicles.
 * <p>
 * Stub for Person B, who owns stations and the return flow.
 */
public class Station {

    private final String id;
    private final String name;
    private final String location;
    private final List<DockingPoint> points;

    /**
     * @param id       the unique station id
     * @param name     the display name shown to riders
     * @param location where the station is (Person B may replace this with coordinates)
     * @param points   the docking points at this station
     */
    public Station(String id, String name, String location, List<DockingPoint> points) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.points = points;
    }

    /**
     * @return the unique station id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the display name shown to riders
     */
    public String getName() {
        return name;
    }

    /**
     * @return where the station is
     */
    public String getLocation() {
        return location;
    }

    /**
     * @return the docking points at this station
     */
    public List<DockingPoint> getPoints() {
        return points;
    }

    /**
     * @return {@code true} if at least one docking point can accept a returned vehicle
     */
    public boolean hasFreePoint() {
        for (DockingPoint dockingPoint : points) {
            if(dockingPoint.isFree()){
                return true;
            }
        }
        return false;
    }
}
