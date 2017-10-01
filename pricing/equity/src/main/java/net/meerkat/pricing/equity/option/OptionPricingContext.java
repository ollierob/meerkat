package net.meerkat.pricing.equity.option;

import java.time.LocalDate;
import java.time.temporal.Temporal;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.HasCurrencyId;
import net.meerkat.instrument.derivative.option.Option;
import net.meerkat.money.Money;
import net.meerkat.pricing.option.OptionPriceShifts;
import net.meerkat.pricing.shifts.InstrumentPriceShifts;
import net.ollie.goat.temporal.date.years.Years;

/**
 *
 * @author ollie
 */
public interface OptionPricingContext<C extends CurrencyId, O extends Option<?>, T extends Temporal> extends HasCurrencyId {

    @Override
    C currencyId();

    @Nonnull
    O option();

    @Nonnull
    T valuationTime();

    default LocalDate valuationDate() {
        return LocalDate.from(this.valuationTime());
    }

    @Nonnull
    OptionPriceShifts optionShifts();

    @Nonnull
    default InstrumentPriceShifts underlyingShifts() {
        return this.optionShifts();
    }

    <F extends CurrencyId> Money<C> convert(@Nonnull Money<F> money);

    default Money<C> exercisePrice() {
        return this.convert(this.option().strike());
    }

    default Number contractMultiplier() {
        return this.option().exercise().contractMultiplier();
    }

    default Years yearsToExpiration() {
        return this.option().exercise().yearsToExpiration(this.valuationDate());
    }

}
