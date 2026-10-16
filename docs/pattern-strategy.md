# Design Pattern: Strategy (Rider Pricing)

## Problem

GoCycle charges each rider type differently:

| Rider type | Unlock fee | Per minute |
|------------|-----------|------------|
| Casual | $1.50 | $0.35 |
| Member | $0.00 | $0.20 |
| Student | $1.00 | $0.25 |

Every type also pays a $5.00 surcharge on trips longer than 2 hours (FR-03 to FR-07). The client has said new rider types will be added later and that pricing must be easy to extend (QR-01). The design therefore has to let a new way of pricing be added without editing the code that already calculates prices or runs trips.
