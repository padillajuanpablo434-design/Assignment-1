# GoCycle Use Case Model

This document describes who uses GoCycle and the main ways they use it. Requirement IDs (FR, QR, C) refer to [requirements.md](requirements.md).

## Actors

| Actor | Kind | Description |
|-------|------|-------------|
| Rider | Primary, human | A casual rider, member or student who rents vehicles through the phone app or a station screen. |
| Operator | Primary, human | Control-room staff who monitor all stations and vehicles, rebalance vehicles and take vehicles in or out of service. |
| Maintenance Crew | Secondary, human | Field staff who are notified of faults, repair vehicles and physically move vehicles during rebalancing. |
| Electric Vehicle | Primary, system | An e-bike or e-scooter that reports its own low battery and faults to GoCycle. |
| Payment Provider | Secondary, system | The external service behind credit cards, city transit cards and phone wallets; it places holds and processes charges. |

## Use case diagram

![GoCycle use case diagram](images/use-case-diagram.png)

```plantuml
@startuml use-case-diagram
left to right direction
skinparam packageStyle rectangle
skinparam actorStyle awesome

actor Rider
actor "Electric Vehicle" as EV <<system>>
actor Operator
actor "Maintenance Crew" as Crew
actor "Payment Provider" as PP <<system>>

rectangle "GoCycle System" {
  usecase "Start Trip" as UC1
  usecase "Place Deposit Hold" as Hold
  usecase "Return Vehicle" as UC2
  usecase "Redirect to\nNearest Station" as Redirect
  usecase "Pay for Trip" as UC4
  usecase "Send Receipt" as Receipt
  usecase "Report Fault" as UC3
  usecase "Notify Maintenance\nand Operators" as Notify
  usecase "View Stations\nand Vehicles" as View
  usecase "Rebalance Vehicles" as UC5
  usecase "Take Vehicle In/Out\nof Service" as Service
}

Rider --> UC1
Rider --> UC2
Rider --> UC3
EV --> UC3

Operator --> View
Operator --> UC5
Operator --> Service

UC1 ..> Hold : <<include>>
UC2 ..> UC4 : <<include>>
UC4 ..> Receipt : <<include>>
UC3 ..> Notify : <<include>>
UC5 ..> View : <<include>>
Redirect ..> UC2 : <<extend>>

Hold --> PP
UC4 --> PP
Notify --> Crew
Notify --> Operator
UC5 --> Crew
@enduml
```

The diagram shows these relationships:

- **Start Trip** always includes **Place Deposit Hold**.
- **Return Vehicle** always includes **Pay for Trip**, which always includes **Send Receipt**.
- **Report Fault** always includes **Notify Maintenance and Operators**, whether the rider or the electric vehicle raises the report.
- **Redirect to Nearest Station** extends **Return Vehicle** only when the station has no free docking point.
