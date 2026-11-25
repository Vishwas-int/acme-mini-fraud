package com.acmemini.fraud;

import java.time.Instant;

/** Scores a payment. Amount: above 50,000 scores 90; above 20,000 and up to 50,000 scores 60; anything else 10.
 *  Since 1.3.0 the score also adds the velocity rule (+20) and the payee country risk (+15). A score of 75 or more
 *  is rejected. */
public class FraudRule {
    public static final int REJECT_AT = 75;

    private final VelocityRule velocity = new VelocityRule();
    private final CountryRisk countries = new CountryRisk();

    public int amountScore(long amount) {
        if (amount > 50_000) {
            return 90;
        }
        return amount > 20_000 ? 60 : 10;
    }

    public int score(long amount, String payerIban, String payeeIban, Instant at) {
        return amountScore(amount) + velocity.score(payerIban, at) + countries.score(payeeIban);
    }

    public boolean rejects(long amount, String payerIban, String payeeIban, Instant at) {
        return score(amount, payerIban, payeeIban, at) >= REJECT_AT;
    }
}
