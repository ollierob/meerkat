package net.ollie.meerkat.calculate.price;

import java.time.temporal.Temporal;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.security.SecurityDefinition;

/**
 *
 * @author ollie
 */
public interface SecurityPricer<T extends Temporal> {

    default <C extends CurrencyId> SecurityPrice<C> price(
            final T valuation,
            final SecurityDefinition security,
            final C currency) {
        return security.handleWith(this.priceContext(valuation, currency));
    }

    <C extends CurrencyId> SecurityPriceContext<C> priceContext(T valuation, C currency);

    interface SecurityPriceContext<C extends CurrencyId> extends SecurityDefinition.Handler<SecurityPrice<C>> {

    }

}
