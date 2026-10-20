# GoCycle — SENG 300, Fall 2026 — Assignment 1

**University of Calgary — Department of Computer Science**

**Repository:** https://github.com/padillajuanpablo434-design/Assignment-1  
**Final report:** [GoCycle_Assignment1_Report.pdf](GoCycle_Assignment1_Report.pdf)

GoCycle is a Java 17 / Maven educational prototype for a citywide bike and scooter rental system. The project combines requirements analysis and use-case modeling with UML, SOLID design principles, and implementations of trips, prices, vehicles, docking stations, simulated payments, and fault notifications.

## Team

| Responsibility | Full name | UCID | University email |
|---|---|---|---|
| Person A — Riders, Trips & Pricing | Nazeeh Hammad | 30173895 | nazeeh.hammad@ucalgary.ca |
| Person B — Vehicles & Stations | Toby Allan | 30245907 | toby.allan@ucalgary.ca |
| Person C — Faults, Payments & Integration | Juan Pablo Padilla | 30229022 | juanpablo.padilla@ucalgary.ca |

See also [TEAM.md](TEAM.md) for the team details required in the submission.

## Requirements

- Java Development Kit (JDK) **17**
- Maven **3.6+** or IntelliJ IDEA with bundled Maven
- The repository opened as a Maven project using `pom.xml`

## Build and test

In a terminal with Java and Maven configured:

```bash
mvn clean test
```

In IntelliJ IDEA, select JDK 17, open the Maven tool window, and run **Lifecycle → clean**, then **Lifecycle → test**.

An earlier integrated-branch run completed **54 JUnit tests, 0 failures, 0 errors, 0 skipped**. Rerun the tests on the final merged `main` branch before submission. This README does not claim a separate fresh verification.

## Run the demonstration

```bash
mvn compile
java -cp target/classes gocycle.app.Main
```

Alternatively, open `src/main/java/gocycle/app/Main.java` in IntelliJ and select **Run 'Main.main()'**.

The demo illustrates a rental, physical lock confirmation, pricing and receipt creation, refundable payment hold, fault notifications, electric-battery alerts, and finding a free station.

## Project structure

```text
src/main/java/gocycle/
  app/       Main integration demonstration
  rider/     Casual, member, student riders
  pricing/   Fare Strategy and long-trip surcharge
  trip/      Rental lifecycle and receipts
  vehicle/   Vehicle inheritance, battery eligibility, factories
  station/   Docking and station-availability lookup
  payment/   Simulated payment methods and zone restrictions
  fault/     Fault reports, Observer listeners, telemetry monitor
src/test/java/gocycle/   JUnit 5 tests
docs/                    Requirements, use cases, UML and pattern reports
pom.xml                  Maven build configuration
```

## Design patterns

- **Strategy** — `PricingStrategy`, `CasualPricing`, `MemberPricing`, `StudentPricing`.
- **Decorator** — `LongTripSurchargePricing` wraps a pricing strategy.
- **Factory Method** — `VehicleCreator` subclasses construct pedal bikes, electric bikes, and scooters; `VehicleFactoryImpl` coordinates selection.
- **Observer** — `FaultService` publishes reports to `FaultListener` subscribers, including `MaintenanceCrew` and `OperatorConsole`.

## Scope and known limitations

The Java implementation is a console-based educational prototype, not a deployed app or live payment service. Payments and receipt delivery are simulated; real lock sensors, multilingual/audio interfaces, configurable branded screens, and a full fleet-rebalancing UI are not implemented. The nearest-station lookup is based on straight-line geographic distance. Final-payment failure after a confirmed lock requires a richer pending-settlement design. See [docs/coverage-and-limitations.md](docs/coverage-and-limitations.md) and the final report.

## Deliverables

The group submission ZIP contains the final report PDF, source and tests, Maven configuration, supporting documentation, README, TEAM, repository URL, and exported Git commit history.

If external sources or AI assistance were used, disclose them in the D2L submission comments in accordance with course requirements.
