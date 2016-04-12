package net.ollie.meerkat.calculate.price;

import java.time.temporal.Temporal;

import javax.annotation.Nonnull;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.security.SecurityDefinition;

/**
 *
 * @author ollie
 */
public interface SecurityPriceCalculator<T extends Temporal> {

    default <C extends CurrencyId> ShiftableSecurityPrice<C> price(
            @Nonnull final T valuation,
            @Nonnull final SecurityDefinition security,
            @Nonnull final C currency) {
        return security.handleWith(this.priceContext(valuation, currency));
    }

    <C extends CurrencyId> SecurityPriceContext<C> priceContext(T valuation, C currency);

    interface SecurityPriceContext<C extends CurrencyId> extends SecurityDefinition.Handler<ShiftableSecurityPrice<C>> {

    }

}
