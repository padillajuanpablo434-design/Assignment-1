package gocycle.rider;

import gocycle.pricing.LongTripSurchargePricing;
import gocycle.pricing.StudentPricing;

/**
 * A student rider with reduced rates.
 */
public class StudentRider extends Rider {

    /**
     * @param id    the unique rider id
     * @param name  the rider's name
     * @param email where receipts are sent
     */
    public StudentRider(String id, String name, String email) {
        super(id, name, email, new LongTripSurchargePricing(new StudentPricing()));
    }
}
