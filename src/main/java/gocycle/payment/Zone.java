package gocycle.payment;

import java.util.Set;

/**
 * An area of the city that may accept only some payment methods.
 * <p>
 * Stub for Person C, who owns payments and zones.
 */
public class Zone {

    private final String id;
    private final String name;
    private final Set<Class<? extends PaymentMethod>> allowedMethods;

    /**
     * @param id             the unique zone id
     * @param name           the display name
     * @param allowedMethods the payment method types accepted in this zone
     */
    public Zone(String id, String name, Set<Class<? extends PaymentMethod>> allowedMethods) {
        this.id = id;
        this.name = name;
        this.allowedMethods = allowedMethods;
    }

    /**
     * @return the unique zone id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the display name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the payment method types accepted in this zone
     */
    public Set<Class<? extends PaymentMethod>> getAllowedMethods() {
        return allowedMethods;
    }

    /**
     * @param method the rider's chosen payment method
     * @return {@code true} if this zone accepts that kind of payment
     */
    public boolean allows(PaymentMethod method) {
        throw new UnsupportedOperationException("TODO: Person C");
    }
}
