package gocycle.rider;

import gocycle.pricing.LongTripSurchargePricing;
import gocycle.pricing.MemberPricing;

/**
 * A rider with a monthly membership and a lower per-minute rate.
 */
public class MemberRider extends Rider {

    /**
     * @param id    the unique rider id
     * @param name  the rider's name
     * @param email where receipts are sent
     */
    public MemberRider(String id, String name, String email) {
        super(id, name, email, new LongTripSurchargePricing(new MemberPricing()));
    }
}
