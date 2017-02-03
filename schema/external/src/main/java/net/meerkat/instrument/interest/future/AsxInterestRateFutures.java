package net.meerkat.instrument.interest.future;

import java.time.YearMonth;

import net.meerkat.identifier.currency.AUD;
import net.meerkat.identifier.currency.CurrencyIso;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.derivative.forward.FutureDeliveryDates;
import net.meerkat.issue.Issue;
import net.meerkat.money.DecimalMoney;
import net.meerkat.money.Money;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.temporal.date.count.DateArithmetic;

/**
 *
 * @author ollie
 */
public class AsxInterestRateFutures {

    public static final DecimalMoney<AUD> THREE_MILLION_AUD = Money.of(3_000_000, CurrencyIso.AUD);

    public static InterestRateFuture thirtyDayInterbankCash(
            final String name,
            final InstrumentIds ids,
            final YearMonth deliveryMonth,
            final Percentage yieldToMaturity,
            final Issue issue) {
        final InterestRateFutureContract contract = new InterestRateFutureContract(
                name,
                ids,
                DateArithmetic.THIRTY_360,
                THREE_MILLION_AUD);
        final FutureDeliveryDates delivery = FutureDeliveryDates.any(deliveryMonth);
        return new InterestRateFuture(name, ids, contract, delivery, issue);
    }

}
