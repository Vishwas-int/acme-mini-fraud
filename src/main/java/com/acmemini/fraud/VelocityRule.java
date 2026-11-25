package com.acmemini.fraud;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/** Since 1.3.0: a payer sending more than 3 payments within 10 minutes adds 20 to the payment's score. */
public class VelocityRule {
    public static final int MAX_IN_WINDOW = 3;
    public static final Duration WINDOW = Duration.ofMinutes(10);
    public static final int ADDS = 20;

    private final Map<String, Deque<Instant>> recent = new HashMap<>();

    public int score(String payerIban, Instant at) {
        Deque<Instant> times = recent.computeIfAbsent(payerIban, k -> new ArrayDeque<>());
        while (!times.isEmpty() && times.peekFirst().isBefore(at.minus(WINDOW))) {
            times.pollFirst();
        }
        times.addLast(at);
        return times.size() > MAX_IN_WINDOW ? ADDS : 0;
    }
}
