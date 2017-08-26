package net.meerkat.instrument.interest.swap;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import net.coljate.list.List;
import net.meerkat.Explainable;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.CurrencyIds;
import net.meerkat.identifier.currency.HasCurrencyIds;
import net.meerkat.instrument.dates.Issued;
import net.meerkat.instrument.derivative.swap.Swap;
import net.meerkat.instrument.interest.InterestRateDerivative;

/**
 *
 * @author ollie
 */
public interface InterestRateSwap
        extends InterestRateDerivative, Swap, Issued, HasCurrencyIds, Explainable {

    @Override
    List<InterestRateSwapLeg<?, ?>> legs();

    @Override
    default CurrencyIds currencyIds() {
        final Set<CurrencyId> currencies = new HashSet<>(2);
        for (final InterestRateSwapLeg<?, ?> leg : this.legs()) {
            currencies.add(leg.payCurrency());
            currencies.add(leg.receiveCurrency());
        }
        return CurrencyIds.of(currencies);
    }

    default boolean isCrossCurrency() {
        return this.currencyIds().size() >= 2;
    }

    @Override
    @Deprecated
    default LocalDate issueDate() {
        throw new UnsupportedOperationException();
    }

}
