package net.meerkat.instrument.derivative.swap;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.CurrencyIds;
import net.meerkat.identifier.currency.HasCurrencyIds;
import net.meerkat.temporal.date.HasDate;

import javax.annotation.Nonnull;
import java.time.LocalDate;

/**
 *
 * @author ollie
 */
public interface SwapLeg<P extends CurrencyId, R extends CurrencyId>
        extends HasDate, HasCurrencyIds {

    @Nonnull
    P payCurrency();

    @Nonnull
    R receiveCurrency();

    @Nonnull
    LocalDate deliveryDate();

    @Nonnull
    @Override
    default LocalDate date() {
        return this.deliveryDate();
    }

    @Override
    default CurrencyIds currencyIds() {
        return CurrencyIds.of(this.payCurrency(), this.receiveCurrency());
    }

}
