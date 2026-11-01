package com.acmemini.fraud;

/** Scores a payment by its amount: above 50,000 scores 90 (rejected, the limit is 70); anything else scores 10. */
public class FraudRule {
    public static final int REJECT_AT = 70;

    public int score(long amount) {
        return amount > 50_000 ? 90 : 10;
    }

    public boolean rejects(long amount) {
        return score(amount) >= REJECT_AT;
    }
}
