# Observer Pattern — GoCycle Fault Reporting

## Problem
A rider can report a damaged vehicle, while an electric vehicle can report its own low battery or a malfunction. Both maintenance staff and the operator control room need to learn about faults. Hard-coding both receivers into the fault-reporting code would create unnecessary dependencies.

## Chosen solution
`FaultService` acts as the **Subject**. The `FaultListener` interface defines the **Observer** contract. `MaintenanceCrew` and `OperatorConsole` implement the interface. When `report(...)` is called, the service creates a `FaultReport`, changes the affected vehicle status to `OUT_OF_SERVICE`, saves the report, and notifies all subscribed listeners. Listeners can be added or removed without modifying `FaultService`.

The accompanying UML snippet is `pattern-observer.puml`.

## Benefits and trade-offs
Observer supports the Open/Closed and Dependency Inversion principles: adding another notification target (for example, a monitoring dashboard) requires another `FaultListener` implementation, not edits to the reporter. It also keeps maintenance and operator responsibilities separate. Its cost is an extra interface and notification lifecycle management; multiple subscribers must handle duplicate alerts and errors sensibly. In this classroom implementation, notifications are synchronous and in memory rather than delivered through an external service.
