# Implementation Coverage and Release Review

The requirements and use case model define the **complete proposed service**. The assignment asks teams to **implement the core** of this design in Java, not a deployed, multilingual commercial platform. Distinguish demonstrable functionality from planned capabilities.

| Requirement group | Prototype implementation | Notes |
|---|---|---|
| FR-03..09; Pricing | Implemented | Rider variants, Strategy, 120+ minute surcharge, simulated hold and charge, receipt |
| FR-10..12; Vehicles | Implemented | Three types and validated electric battery threshold |
| FR-13..17; Stations/returns | Core implemented | Docking and lock confirmation; nearest *geographical* free station helper (not road routing); still not an interactive UI |
| FR-18..21; Faults | Core implemented | FaultService, Observer recipients, low-battery threshold via registered telemetry monitor, explicit onboard electrical fault reporting |
| FR-22..23; Payments | Simulated | Three payment adapters, configurable zone white-list enforced by decorator; no third-party gateway |
| FR-24..26; Accessibility/branding | Modeled only | Not implemented as GUI; languages, speech and branding are future front-end work |
| FR-27..29; Operations | Partially demonstrated | Operator fault alerts exist; full fleet dashboard, rebalancing interface and service reinstatement workflow not implemented |
| FR-30..31; Receipts | Implemented as console adapters | Receipt issued after successful payment and shown via simulated email/app senders |

## Known exception to resolve in next iteration
A declined final charge after *physical lock confirmation* currently leaves `Trip` in `ACTIVE`. A production implementation should distinguish rental completion from financial settlement, for example using a `PAYMENT_PENDING` state, and preserve the physical lock timestamp. This is beyond the minimal patch because it changes Person A's existing tested lifecycle and requires a team decision. Do not describe this edge case as fully solved.

## Before packaging the ZIP
- Run `Maven clean`, `test` and `Main` on the freshly merged `main` branch; record actual results, do not invent counts.
- Fill in every name/UCID in `README.md` and every name/UCID/email in `TEAM.md` using verified member details.
- Review *complete-class-diagram.puml* against the merged code; render and include a readable diagram in the report.
- Merge report sections: cover; classification; use case diagram; main use cases; class diagram; 1-page rationale; Strategy/Decorator, Factory Method, Observer each with UML and trade-offs; references.
- Put the report PDF, `src/`, `README.md`, `TEAM.md` (member UCIDs and emails), repository URL and exported full commit log in **one ZIP**.
- Ensure only one group member uploads to the assignment Dropbox.
- List outside sources, including any external assistance permitted by course policy, in the submission comments; follow faculty AI-usage guidance.
