package net.meerkat.instrument.derivative.swap;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyIds;
import net.meerkat.identifier.currency.HasCurrencyIds;
import net.meerkat.instrument.InstrumentDefinition;
import net.meerkat.instrument.Security;

/**
 *
 * @author Ollie
 */
public interface Swap extends Security, InstrumentDefinition, HasCurrencyIds {

    @Nonnull
    SwapLegs<?> legs();

    default boolean isPerpetual() {
        return !this.legs().numLegs().isPresent();
    }

    default boolean isBullet() {
        return this.legs().numLegs().orElse(0) == 1;
    }

    @Override
    default CurrencyIds currencyIds() {
        return this.legs().currencyIds();
    }

}
