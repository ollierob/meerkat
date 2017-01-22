package net.ollie.meerkat.calculate.price;

import java.time.temporal.Temporal;

import javax.annotation.Nonnull;

import net.meerkat.money.currency.Currency;
import net.meerkat.security.SecurityDefinition;

/**
 *
 * @author ollie
 */
public interface SecurityPriceCalculator<T extends Temporal> {

    default <C extends Currency> ShiftableSecurityPrice<C> price(
            @Nonnull final T valuation,
            @Nonnull final SecurityDefinition security,
            @Nonnull final C currency) {
        return security.handleWith(this.priceContext(valuation, currency));
    }

    <C extends Currency> SecurityPriceContext<C> priceContext(T valuation, C currency);

    interface SecurityPriceContext<C extends Currency> extends SecurityDefinition.Handler<ShiftableSecurityPrice<C>> {

    }

}
