package gocycle.station;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/** Finds the nearest station with a free docking point. Coordinates are in degrees. */
public final class StationFinder {
    private StationFinder() { }

    public record Coordinates(double latitude, double longitude) {
        public Coordinates {
            if (!Double.isFinite(latitude) || !Double.isFinite(longitude)
                    || latitude < -90 || latitude > 90 || longitude < -180 || longitude > 180) {
                throw new IllegalArgumentException("Invalid geographical coordinates");
            }
        }
    }

    /** Excludes full stations, sorts by straight-line distance and returns no station if all are full. */
    public static Optional<Station> nearestAvailable(Coordinates from, Map<Station, Coordinates> stations) {
        Objects.requireNonNull(from, "from");
        Objects.requireNonNull(stations, "stations");
        return stations.entrySet().stream()
                .filter(e -> e.getKey() != null && e.getValue() != null && e.getKey().hasFreePoint())
                .min(Comparator.comparingDouble(e -> distanceKm(from, e.getValue())))
                .map(Map.Entry::getKey);
    }

    /** Great-circle distance, in kilometres; a city routing engine would replace this estimate. */
    public static double distanceKm(Coordinates a, Coordinates b) {
        Objects.requireNonNull(a, "a");
        Objects.requireNonNull(b, "b");
        double dLat = Math.toRadians(b.latitude() - a.latitude());
        double dLon = Math.toRadians(b.longitude() - a.longitude());
        double h = Math.pow(Math.sin(dLat / 2), 2)
                + Math.cos(Math.toRadians(a.latitude())) * Math.cos(Math.toRadians(b.latitude()))
                * Math.pow(Math.sin(dLon / 2), 2);
        return 6371.0 * 2 * Math.asin(Math.min(1.0, Math.sqrt(h)));
    }
}
