package net.meerkat.risk.sensitivities;

import javax.annotation.Nonnull;

import net.meerkat.calculate.sensitivity.PriceSensitivities;
import net.meerkat.calculate.sensitivity.UnitPriceSensitivities;
import net.meerkat.money.price.Price;
import net.meerkat.risk.position.Position;

/**
 *
 * @author Ollie
 */
public interface PositionSensitivities extends PriceSensitivities {

    @Nonnull
    Position position();

    @Nonnull
    UnitPriceSensitivities instrumentSensitivities();

    @Override
    default Price.Evaluated<?> price() {
        return this.instrumentSensitivities().price();
    }

}
