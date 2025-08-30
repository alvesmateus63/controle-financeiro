package br.com.alvesmateus.controleFinanceiro.core.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class CurrencyUtils {
    private CurrencyUtils() {}

    /**
     * Converte um valor BigDecimal (ex: 123.45) para um Long em centavos (ex: 12345).
     * @param decimalValue O valor em formato decimal.
     * @return O valor correspondente em centavos, ou null se a entrada for null.
     */
    public static Long toCents(BigDecimal decimalValue) {
        if(decimalValue == null) {
            return null;
        }

        return decimalValue.multiply(new BigDecimal("100")).longValue();
    }

    /**
     * Converte um valor Long em centavos (ex: 12345) para um BigDecimal (ex: 123.45).
     * @param centsValue O valor em centavos.
     * @return O valor correspondente em formato decimal, ou null se a entrada for null.
     */
    public static BigDecimal fromCents(Long centsValue) {
        if(centsValue == null) {
            return null;
        }

        return new BigDecimal(centsValue).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
    }
}
