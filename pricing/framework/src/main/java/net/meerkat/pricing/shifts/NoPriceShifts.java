package net.meerkat.pricing.shifts;

import java.util.Map;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;

/**
 *
 * @author Ollie
 */
public interface NoPriceShifts extends PriceShifts {

    NoPriceShifts INSTANCE = new NoPriceShifts() {

        @Override
        public Map<String, Object> explain() {
            return this.explanationBuilder();
        }

    };

    @Override
    default <C extends CurrencyId> Money<C> shift(Money<C> price) {
        return price;
    }

}
