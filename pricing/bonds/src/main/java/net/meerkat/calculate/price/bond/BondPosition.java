package net.meerkat.calculate.price.bond;

import java.util.Optional;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.position.PositionWithQuantity;
import net.meerkat.money.Money;
import net.meerkat.money.Price;

/**
 *
 * @author ollie
 */
public interface BondPosition extends PositionWithQuantity {

    @Override
    @Deprecated
    default <C extends CurrencyId> Optional<Money<C>> marketValue(final Price<C> price) {
        return price instanceof BondPrice
                ? Optional.of(this.marketValue((BondPrice<C>) price))
                : Optional.empty();
    }

    @Nonnull
    default <C extends CurrencyId> Money<C> marketValue(@Nonnull final BondPrice<C> price) {
        return price.dirty().times(this.quantity());
    }

}
