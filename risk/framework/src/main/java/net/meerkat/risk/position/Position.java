package net.meerkat.risk.position;

import java.util.Optional;

import javax.annotation.Nonnull;

import net.meerkat.Explainable;
import net.meerkat.calculate.sensitivity.UnitPriceSensitivities;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.price.Price;
import net.meerkat.risk.sensitivities.PositionSensitivities;

/**
 *
 * @author ollie
 */
public interface Position extends HasPositionId, Explainable {

    @Nonnull
    <C extends CurrencyId> Optional<Money<C>> value(Price<C> price);

    @Nonnull
    Optional<? extends PositionSensitivities> scale(UnitPriceSensitivities sensitivities);

}
