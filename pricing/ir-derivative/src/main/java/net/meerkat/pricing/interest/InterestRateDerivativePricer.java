package net.meerkat.pricing.interest;

import java.time.temporal.Temporal;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.interest.InterestRateDerivative;
import net.meerkat.money.price.Price;
import net.meerkat.pricing.InstrumentPriceException;
import net.meerkat.pricing.InstrumentPricer;

/**
 *
 * @author Ollie
 */
public interface InterestRateDerivativePricer<T extends Temporal, D extends InterestRateDerivative>
        extends InstrumentPricer<T, D> {

    @Override
    default <C extends CurrencyId> Price.Valued<C> price(final T temporal, final D instrument, final C currency) throws InstrumentPriceException {
        return this.price(temporal, instrument, currency, InterestRateDerivativeShifts.NONE);
    }

    <C extends CurrencyId> Price.Valued<C> price(T temporal, D instrument, C currency, InterestRateDerivativeShifts shifts) throws InstrumentPriceException;

}
