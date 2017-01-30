package net.meerkat.calculate.price;

import java.time.temporal.Temporal;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.InstrumentDefinition;

/**
 *
 * @author ollie
 */
public interface GenericInstrumentPricer<T extends Temporal>
        extends InstrumentPricer<T, InstrumentDefinition> {

    @Override
    default <C extends CurrencyId> ShiftableInstrumentPrice<C> price(
            @Nonnull final T valuation,
            @Nonnull final InstrumentDefinition instrument,
            @Nonnull final C currency) {
        return instrument.handleWith(this.pricingContext(valuation, currency));
    }

    <C extends CurrencyId> SecurityPriceContext<C> pricingContext(T valuation, C currency);

    interface SecurityPriceContext<C extends CurrencyId> extends InstrumentDefinition.Handler<ShiftableInstrumentPrice<C>> {

    }

}
