package net.meerkat.security.interest.future;

import java.time.YearMonth;

import net.meerkat.identifier.currency.CurrencyIso;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.interest.future.InterestRateFuture;
import net.meerkat.instrument.interest.future.InterestRateFutureContract;
import net.meerkat.money.Money;
import net.ollie.goat.numeric.percentage.Percentage;

import org.apache.commons.math3.fraction.Fraction;

/**
 *
 * @author ollie
 */
public class AsxInterestRateFutures {

    public static InterestRateFuture thirtyDayInterbankCash(
            final String name,
            final InstrumentIds identifiers,
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
