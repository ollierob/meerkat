package net.ollie.meerkat.calculate.price;

import java.time.temporal.Temporal;

import javax.annotation.Nonnull;

import net.meerkat.instrument.InstrumentDefinition;
import net.meerkat.money.currency.CurrencyId;

/**
 *
 * @author ollie
 */
public interface InstrumentPricer<T extends Temporal> {

    default <C extends CurrencyId> ShiftableInstrumentPrice<C> price(
            @Nonnull final T valuation,
            @Nonnull final InstrumentDefinition security,
            @Nonnull final C currency) {
        return security.handleWith(this.pricingContext(valuation, currency));
    }

    <C extends CurrencyId> SecurityPriceContext<C> pricingContext(T valuation, C currency);

    interface SecurityPriceContext<C extends CurrencyId> extends InstrumentDefinition.Handler<ShiftableInstrumentPrice<C>> {

    }

}
