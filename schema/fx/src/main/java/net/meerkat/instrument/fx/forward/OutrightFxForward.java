package net.meerkat.instrument.fx.forward;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.fx.FxInstrument;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRate;

/**
 *
 * @author Ollie
 */
public interface OutrightFxForward<B extends CurrencyId, C extends CurrencyId>
        extends FxInstrument<B, C> {

    @Nonnull
    Money<B> baseAmount();

    @Nonnull
    Money<C> counterAmount();

    @Override
    default B baseCurrencyId() {
        return this.baseAmount().currencyId();
    }

    @Override
    default C counterCurrencyId() {
        return this.counterAmount().currencyId();
    }

    @Nonnull
    default ExchangeRate<B, C> forwardRate() {
        return ExchangeRate.between(this.baseAmount(), this.counterAmount());
    }

    @Override
    default <R> R handleWith(final FxInstrument.Handler<R> handler) {
        return handler.handle(this);
    }

}
