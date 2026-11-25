package com.acmemini.fraud;

import java.util.Set;

/** Since 1.3.0: a payee IBAN from outside DE, NL, FR, GB and IE adds 15 to the payment's score. */
public class CountryRisk {
    public static final Set<String> HOME = Set.of("DE", "NL", "FR", "GB", "IE");
    public static final int ADDS = 15;

    public int score(String payeeIban) {
        String country = payeeIban == null || payeeIban.length() < 2 ? "" : payeeIban.substring(0, 2);
        return HOME.contains(country) ? 0 : ADDS;
    }
}
