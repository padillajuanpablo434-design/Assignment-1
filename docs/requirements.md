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

## Faults & Maintenance

| ID | Requirement | Source |
|----|-------------|--------|
| FR-18 | A rider can report a fault on a vehicle and choose its type, such as flat tire or broken brakes. | "Riders report faults (flat tire, broken brakes)" |
| FR-19 | Electric vehicles automatically report low battery and their own faults. | "electric vehicles self-report low battery/faults" |
| FR-20 | The system takes a vehicle out of service when a fault is reported on it. | "Broken vehicles go out of service" |
| FR-21 | The system notifies the maintenance crew and the control-room operators of every fault report. | "maintenance crew and control-room operators are notified" |
| QR-04 | A vehicle cannot be rented from the moment its fault is recorded: zero rentals of a vehicle with an open fault. | "Broken vehicles go out of service" |

## Payment & Zones

| ID | Requirement | Source |
|----|-------------|--------|
| FR-22 | A rider can pay by credit card, city transit card or phone wallet. | "Payment: credit card, city transit card, phone wallet" |
| FR-23 | Each zone has a configured list of allowed payment methods, and the system accepts only those methods for trips in that zone. | "Zones may allow only some methods" |
| C-03 | Payment is limited to the three method types the client names: credit card, city transit card and phone wallet. | "Payment: credit card, city transit card, phone wallet" |

## Accessibility & Branding

| ID | Requirement | Source |
|----|-------------|--------|
| FR-24 | Rider-facing screens can be shown in several languages, chosen by the rider. | "Screens: multiple languages" |
| FR-25 | Rider-facing screens can read their content aloud. | "read-aloud" |
| FR-26 | Screens show the brand colours and logo of the zone they are in. | "per-zone brand colours/logo" |
| QR-05 | 100% of rider-facing screen text is available in every supported language and through read-aloud. | "Screens: multiple languages, read-aloud" |
| QR-06 | Changing a zone's colours or logo requires only a configuration change, with no code change or rebuild. | "per-zone brand colours/logo from configuration" |
| C-04 | Zone branding must be loaded from configuration rather than written into the code. | "from configuration" |
