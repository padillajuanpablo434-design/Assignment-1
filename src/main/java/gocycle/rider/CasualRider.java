package gocycle.rider;

import gocycle.pricing.CasualPricing;
import gocycle.pricing.LongTripSurchargePricing;

/**
 * A rider who pays per trip with no membership.
 */
public class CasualRider extends Rider {

    /**
     * @param id    the unique rider id
     * @param name  the rider's name
     * @param email where receipts are sent
     */
    public CasualRider(String id, String name, String email) {
        super(id, name, email, new LongTripSurchargePricing(new CasualPricing()));
    }
}
