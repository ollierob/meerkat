package net.ollie.meerkat.security.interest.future;

import java.time.YearMonth;

import net.ollie.goat.money.Money;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.meerkat.identifier.currency.CurrencyIso;
import net.ollie.meerkat.identifier.security.SecurityIds;

import org.apache.commons.math3.fraction.Fraction;

/**
 *
 * @author ollie
 */
public class AsxInterestRateFutures {

    public static InterestRateFuture thirtyDayInterbankCash(
            final String name,
            final SecurityIds identifiers,
            final YearMonth deliveryMonth,
            final Percentage yieldToMaturity) {
        final InterestRateFutureContract contract = new InterestRateFutureContract(
                new Fraction(30, 365),
                Money.of(3_000_000, CurrencyIso.AUD));
//        final FutureDeliveryDates delivery = FutureDeliveryDates.any(deliveryMonth);
//        return new InterestRateFuture(name, identifiers, contract, delivery);
        throw new UnsupportedOperationException();
    }

}