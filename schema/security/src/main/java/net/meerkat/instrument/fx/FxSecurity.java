package net.meerkat.instrument.fx;

import java.util.Set;

import javax.annotation.Nonnull;

import net.meerkat.money.Money;
import net.meerkat.money.currency.Currency;
import net.meerkat.money.currency.HasCurrencies;
import net.meerkat.money.fx.ExchangeRate;
import net.ollie.goat.collection.Sets;
import net.ollie.goat.temporal.date.interim.CompleteInterval;
import net.meerkat.instrument.InstrumentDefinition;

/**
 *
 * @author Ollie
 */
public interface FxSecurity extends InstrumentDefinition, HasCurrencies {

    Money<?> base();

    Money<?> counter();

    @Nonnull
    ExchangeRate<?, ?> exchangeRate();

    @Nonnull
    CompleteInterval settlement();

    @Override
    default Set<? extends Currency> currencies() {
        return Sets.asSet(this.base().currency(), this.counter().currency());
    }

    @Deprecated
    default FxSecurity security() {
        return this;
    }

    @Override
    default <R> R handleWith(final InstrumentDefinition.Handler<R> handler) {
        return handler instanceof FxSecurity.Handler
                ? this.handleWith((FxSecurity.Handler<R>) handler)
                : handler.handle(this);
    }

    <R> R handleWith(FxSecurity.Handler<R> handler);

    interface Handler<R>
            extends InstrumentDefinition.Handler<R> {

        R handle(FxForward forward);

    }

}
