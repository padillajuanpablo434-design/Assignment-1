package gocycle.trip;

/**
 * The lifecycle of a {@link Trip}: {@code NOT_STARTED -> ACTIVE -> COMPLETED}.
 */
public enum TripState {
    /** Created, but the vehicle has not been unlocked. */
    NOT_STARTED,
    /** The vehicle is unlocked and the rider is being charged. */
    ACTIVE,
    /** The vehicle is confirmed locked and the rider has been charged. */
    COMPLETED
}
