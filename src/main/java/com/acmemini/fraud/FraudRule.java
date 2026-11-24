package com.acmemini.fraud;

/** Scores a payment by its amount: above 50,000 scores 90 (rejected, the limit is 70); since 1.1.0 a middle band
 *  above 20,000 and up to 50,000 scores 60 (accepted, but shown to the fraud team); anything else scores 10. */
public class FraudRule {
    public static final int REJECT_AT = 70;

    public int score(long amount) {
        if (amount > 50_000) {
            return 90;
        }
        return amount > 20_000 ? 60 : 10;
    }

    public boolean rejects(long amount) {
        return score(amount) >= REJECT_AT;
    }
}
