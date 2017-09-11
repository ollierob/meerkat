package net.meerkat.pricing;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.InstrumentDefinition;

/**
 *
 * @author ollie
 */
public interface GenericInstrumentPricer
        extends InstrumentPricer<InstrumentDefinition> {

    @Override
    default <C extends CurrencyId> ShiftablePrice<C> price(
            @Nonnull final InstrumentDefinition instrument,
            @Nonnull final C currency) {
        return instrument.handleWith(this.pricingContext(currency));
    }

    <C extends CurrencyId> SecurityPriceContext<C> pricingContext(C currency);

    interface SecurityPriceContext<C extends CurrencyId> extends InstrumentDefinition.Handler<ShiftablePrice<C>> {

    }

}
