# Design Rationale — GoCycle (Draft; confirm against the final UML and code)

GoCycle separates riders, pricing, trips, vehicles, stations, faults, and payments because each part changes for a different reason. The `Trip` class coordinates the lifecycle of one rental but asks its `PricingStrategy` to calculate fees and its `PaymentMethod` to process the deposit hold and final charge. A new rider category or payment provider can therefore be added with limited effects elsewhere. Vehicle eligibility and docking checks remain in vehicle and station objects, rather than being duplicated across payment and pricing logic.

**Single Responsibility (SRP).** `Trip` handles rental lifecycle; `PricingStrategy` handles charges; `FaultService` reports faults; `MaintenanceCrew` manages maintenance notifications; `OperatorConsole` records operator alerts; `Zone` contains payment permissions. Although `Trip` coordinates several components, it delegates their specialized work.

**Open/Closed (OCP).** New pricing options implement `PricingStrategy`; new payment methods implement `PaymentMethod`; new fault destinations implement `FaultListener`. These capabilities can be extended without adding conditionals to established flows.

**Liskov Substitution (LSP).** Casual, member, and student riders should be usable anywhere a `Rider` is required, and concrete vehicle subclasses should respect the `Vehicle.isRentable()` contract. Alternative payment implementations should honor the hold/charge/release semantics defined by `PaymentMethod`. These properties must also be verified against Person B's completed implementation.

**Interface Segregation (ISP).** `FaultListener` has only the callback needed by notification receivers; pricing and receipt delivery likewise expose focused interfaces. A consumer does not need to implement unrelated vehicle or payment operations.

**Dependency Inversion (DIP).** `Trip` relies on `PricingStrategy` and `PaymentMethod` abstractions, not a particular card provider or rider price calculator. `FaultService` knows only `FaultListener`, not its concrete listeners.

The **Strategy** pattern selects the rider's pricing rules. The **Factory Method** pattern is planned to encapsulate creation of pedal bikes, e-bikes, and scooters without forcing callers to construct concrete vehicle types directly; the group should confirm the final factory implementation meets the claimed pattern. The **Observer** pattern decouples fault reporting from the maintenance team and the operator control room. These patterns add interfaces and small classes, but improve testability and reduce change propagation. The final class diagram should include associations and composition relationships between the implemented classes, not only inheritance.

**Important integration guard:** `Trip.end()` currently sets every returned vehicle to `AVAILABLE`. If the fault is raised during an active rental, ending that rental may accidentally reactivate a damaged vehicle. The team should coordinate a small change so a faulty vehicle remains `OUT_OF_SERVICE` after being returned. Zone restrictions must likewise be checked before a trip starts: the `ZoneRestrictedPaymentMethod` wrapper in the Person C patch provides that enforcement without changing `Trip.start()`.
