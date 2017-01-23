package net.ollie.meerkat.calculate.price;

import java.time.temporal.Temporal;

import javax.annotation.Nonnull;

import net.meerkat.money.currency.Currency;
import net.meerkat.instrument.InstrumentDefinition;

/**
 *
 * @author ollie
 */
public interface SecurityPriceCalculator<T extends Temporal> {

    default <C extends Currency> ShiftableSecurityPrice<C> price(
            @Nonnull final T valuation,
            @Nonnull final InstrumentDefinition security,
            @Nonnull final C currency) {
        return security.handleWith(this.pricingContext(valuation, currency));
    }

    <C extends Currency> SecurityPriceContext<C> pricingContext(T valuation, C currency);

    interface SecurityPriceContext<C extends Currency> extends InstrumentDefinition.Handler<ShiftableSecurityPrice<C>> {

    }

}
