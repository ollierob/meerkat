package net.meerkat.instrument.equity.swap;

import net.coljate.collection.Collection;
import net.coljate.set.Set;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.AggregateInstrument;
import net.meerkat.instrument.derivative.swap.BlendedSwap;
import net.meerkat.instrument.equity.EquityDerivative;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRates;

/**
 *
 * @author ollie
 */
public interface BlendedEquitySwap
        extends BlendedSwap, EquityDerivative<AggregateInstrument> {

    @Override
    Set<? extends EquitySwap<?>> swaps();

    default <C extends CurrencyId> Money<C> meanNotional(final C currencyId, final ExchangeRates fxRates) {
        final Collection<Money<C>> monies = this.swaps()
                .transform(EquitySwap::notional)
                .transform(notional -> fxRates.convert(notional, currencyId));
        return Money.mean(monies);
    }

    @Override
    default <R> R handleWith(final EquityDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
