# Factory Method Pattern for Vehicle Creation

## Problem
GoCycle supports three vehicle types: pedal bikes, e-bikes, and e-scooters. The system needs to create these vehicles without forcing client code to depend on the concrete classes directly. If callers instantiate `PedalBike`, `ElectricBike`, or `ElectricScooter` themselves, the code becomes tightly coupled to implementation details and becomes harder to extend when new vehicle types are added (like non-electric scooters).

## Why this pattern fits
The Factory Method pattern is appropriate because the creation logic is conceptually the same for all vehicle types, but the concrete product differs. The client only needs to say which `VehicleType` it wants, and the factory creates the correct object. This keeps the calling code focused on behavior rather than construction details and follows the dependency inversion idea that high-level code should depend on abstractions, not concrete implementations.

## UML snippet

![Factory Method UML](images/factory-method-uml-snippet.png)

## Benefits and trade-offs
This design improves flexibility by centralizing object creation in one place and letting the factory decide which implementation to instantiate. It also makes the code easier to extend when adding a new `VehicleType`, because the change is localized to the factory instead of being spread across the application. The main trade-off is a small amount of indirection: callers must use the factory, and the factory must know about each vehicle implementation. This is usually a worthwhile cost because it keeps creation logic consistent and reduces coupling.

The Factory Method design also aligns well with the SOLID principles. The Single Responsibility Principle is respected because creation logic is isolated in the factory rather than mixed into trip logic or vehicle logic. The Open/Closed Principle is supported because new vehicle types can be added by extending the factory logic without modifying callers that already use the `Vehicle` abstraction. The Liskov Substitution Principle is maintained because every concrete vehicle object can be used wherever a `Vehicle` is expected; callers do not need to know whether the instance is a `PedalBike`, `ElectricBike`, or `ElectricScooter`. The Interface Segregation Principle is satisfied by keeping the factory interface focused on one responsibility (creating a vehicle). Finally, the Dependency Inversion Principle is supported because the client depends on the `VehicleFactory` and `Vehicle` abstractions rather than the concrete implementations themselves, reducing coupling and making the design easier to evolve.
