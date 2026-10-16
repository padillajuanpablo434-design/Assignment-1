package gocycle.rider;

import gocycle.pricing.PricingStrategy;

/**
 * A person who rents GoCycle vehicles.
 * <p>
 * Subclasses represent rider types (casual, member, student) and each one is
 * charged through its own {@link PricingStrategy}.
 */
public abstract class Rider {

    private final String id;
    private final String name;
    private final String email;
    private final PricingStrategy pricing;

    /**
     * @param id      the unique rider id
     * @param name    the rider's name
     * @param email   where receipts are sent
     * @param pricing how this rider is charged
     */
    protected Rider(String id, String name, String email, PricingStrategy pricing) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pricing = pricing;
    }

    /**
     * @return the unique rider id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the rider's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return where receipts are sent
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return how this rider is charged
     */
    public PricingStrategy getPricing() {
        return pricing;
    }
}
