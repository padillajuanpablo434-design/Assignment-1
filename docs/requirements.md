# GoCycle Requirements

This document classifies every requirement in the GoCycle client description. Each requirement has an ID, a one-sentence statement, and the phrase from the client description it comes from.

## Requirement types

| Type | Prefix | Meaning |
|------|--------|---------|
| Functional | FR | What the system must do: a behaviour or service it provides. |
| Quality | QR | How well the system must work, stated so it can be measured or checked. |
| Constraint | C | A boundary on the solution that limits how it may be built, not something the system does. |

IDs are numbered across the whole document, so they stay unique when referenced from [use-cases.md](use-cases.md).

## Trips & Pricing

| ID | Requirement | Source |
|----|-------------|--------|
| FR-01 | A rider can start a trip by taking an available vehicle from a docking station. | "Riders take a vehicle from a station" |
| FR-02 | A rider can identify themselves and start a trip either through the phone app or at the station screen with the card reader. | "Access via phone app OR station screen + card reader" |
| FR-03 | The system supports casual riders, who pay for each trip separately. | "casual (pay per trip)" |
| FR-04 | The system supports members, who pay a monthly fee and are charged a lower per-minute rate than casual riders. | "member (monthly, lower per-minute rate)" |
| FR-05 | The system supports student riders, who are charged a reduced rate. | "student (reduced rate)" |
| FR-06 | The system calculates a trip's cost as an unlock fee plus a per-minute charge, using the rates for the rider's type. | "Trip cost = unlock fee + per-minute charge" |
| FR-07 | The system adds a long-trip surcharge to any trip longer than 2 hours. | "trips over 2 hours add a long-trip surcharge" |
| FR-08 | The system places a refundable deposit hold on the rider's payment method when a trip starts. | "Refundable deposit hold placed at trip start" |
| FR-09 | The system releases the deposit hold when the trip ends. | "released at trip end" |
| QR-01 | Adding a new rider type requires only new classes; no existing pricing, rider or trip class is changed. | "New rider types will be added later — pricing must be easy to extend" |
| QR-02 | Every trip cost is exact to the cent: the total equals the unlock fee plus the per-minute charge plus any surcharge, with no rounding error. | "Trip cost = unlock fee + per-minute charge" |
| C-01 | Riders can reach the system through only two channels: the phone app, and the station screen with its card reader. | "Access via phone app OR station screen + card reader" |

## Vehicles

| ID | Requirement | Source |
|----|-------------|--------|
| FR-10 | The system supports three vehicle types: pedal bike, e-bike and e-scooter. | "Vehicles: pedal bike, e-bike, e-scooter" |
| FR-11 | The system tracks the battery level of each electric vehicle (e-bike and e-scooter). | "Electric ones have a battery" |
| FR-12 | The system refuses to rent an electric vehicle whose battery is below 20%. | "cannot be rented below 20%" |

## Stations & Returns

| ID | Requirement | Source |
|----|-------------|--------|
| FR-13 | The system manages docking stations, each made up of docking points. | "Docking stations, each with docking points" |
| FR-14 | A rider can return a vehicle to any station that has a free docking point, not only the station it was taken from. | "return it to any station with a free point" |
| FR-15 | The system ends a trip and stops charging only once the docking point confirms the vehicle is locked. | "the trip ends and charging stops only once the vehicle is confirmed locked" |
| FR-16 | If the chosen station has no free docking point, the system directs the rider to the nearest station that has one. | "If no free point, the rider is directed to the nearest station with one" |
| FR-17 | While a rider is being redirected, their trip stays active and continues to be charged. | "trip continues" |
| QR-03 | No trip ends without lock confirmation, and the billed time of every trip stops at the moment the lock is confirmed. | "the trip ends and charging stops only once the vehicle is confirmed locked" |
| C-02 | Each docking point holds at most one vehicle at a time. | "each point holds one vehicle" |
