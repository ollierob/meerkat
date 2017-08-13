package net.meerkat.risk.bond;

import java.util.Optional;

import javax.annotation.Nonnull;

import net.meerkat.pricing.bond.BondPrice;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.price.Price;
import net.meerkat.risk.PositionWithQuantity;

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
        return price.marketValue(this.quantity());
    }

}
