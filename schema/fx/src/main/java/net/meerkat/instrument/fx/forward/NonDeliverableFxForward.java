package net.meerkat.instrument.fx.forward;

import java.time.LocalDate;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.fx.FxInstrument;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.time.calendar.settlement.SettlementDate;

/**
 *
 * @author ollie
 */
public interface NonDeliverableFxForward<B extends CurrencyId, C extends CurrencyId>
        extends FxInstrument<B, C> {

    @Nonnull
    Money<C> notionalAmount();

    @Nonnull
    ExchangeRate<B, C> spotRate();

    @Nonnull
    LocalDate fixingDate();

    @Override
    SettlementDate settlementDate();

    @Override
    default C counterCurrencyId() {
        return this.spotRate().to();
    }

    @Override
    default B baseCurrencyId() {
        return this.spotRate().from();
    }

    @Override
    default <R> R handleWith(final FxInstrument.Handler<R> handler) {
        return handler.handle(this);
    }

}
