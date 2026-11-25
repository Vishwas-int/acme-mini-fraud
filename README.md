# mini-fraud 1.3.0

The ACME-MINI fraud check. mini-payments asks it for a score before booking; a score of **75 or more** is rejected.

The score is the amount score (10, 60 above 20,000, 90 above 50,000), plus, since 1.3.0:
- **velocity**: more than 3 payments from the same payer within 10 minutes adds 20 (`VelocityRule`);
- **country risk**: a payee IBAN from outside DE, NL, FR, GB and IE adds 15 (`CountryRisk`).

Made-up test data for an MSOCK refresh demo.
