package net.meerkat.position;

import java.util.Optional;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.price.Price;

/**
 *
 * @author ollie
 */
public interface Position extends HasPositionId {

    @Nonnull
    <C extends CurrencyId> Optional<Money<C>> value(Price<C> price);

}
