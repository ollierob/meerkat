package net.meerkat.security.fx;

import java.util.Set;

import javax.annotation.Nonnull;

import net.meerkat.money.Money;
import net.meerkat.money.currency.Currency;
import net.meerkat.money.currency.HasCurrencies;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.security.SecurityDefinition;
import net.ollie.goat.collection.Sets;
import net.ollie.goat.temporal.date.interim.CompleteInterval;

/**
 *
 * @author Ollie
 */
public interface FxSecurity extends SecurityDefinition, HasCurrencies {

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
    default <R> R handleWith(final SecurityDefinition.Handler<R> handler) {
        return handler instanceof FxSecurity.Handler
                ? this.handleWith((FxSecurity.Handler<R>) handler)
                : handler.handle(this);
    }

    <R> R handleWith(FxSecurity.Handler<R> handler);

    interface Handler<R>
            extends SecurityDefinition.Handler<R> {

        R handle(FxForward forward);

    }

}
