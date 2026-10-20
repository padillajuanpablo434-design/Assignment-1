# Consistency edits for Person A's use cases

UC-03 **Report Fault**, alternate flow **2a**, currently says that a faulty vehicle is removed from service *once docked*. This contradicts the client's rule that a broken vehicle must not remain rentable when reported and contradicts `FaultService.report(...)`. Replace step 2 with:

> 2. The system immediately marks the vehicle OUT_OF_SERVICE and notifies maintenance and operators. The current trip remains active until the rider securely docks the vehicle. Returning that vehicle must not restore it to AVAILABLE.

UC-02 **Return Vehicle**, alternate **Payment fails**, currently implies that the payment gateway failure should be handled without acknowledging that the physical lock has occurred. The implemented core does not yet separate `COMPLETED` from `PAYMENT_PENDING`. Mark this as a prototype limitation; do not claim exact production handling until the team modifies and tests the payment lifecycle.
