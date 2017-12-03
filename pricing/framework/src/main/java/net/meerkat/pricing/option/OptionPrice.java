package net.meerkat.pricing.option;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.derivative.option.Option;
import net.meerkat.money.Money;
import net.meerkat.money.price.MoneyPrice;
import net.meerkat.money.price.Price;
import net.meerkat.pricing.ShiftablePrice;
import net.meerkat.pricing.shifts.InstrumentPriceShifts;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface OptionPrice<C extends CurrencyId> extends MoneyPrice<C> {

    @Nonnull
    Price<C> underlyingPrice();

    /**
     * @return the difference between the underlying price and the {@link Option#strike strike price}.
     * @see <a href="https://en.wikipedia.org/wiki/Intrinsic_value_(finance)">Intrinsic value</a>
     */
    @Nonnull
    Money<C> intrinsicValue();

    /**
     * @return the "uncertainty" premium.
     * @see <a href="https://en.wikipedia.org/wiki/Option_time_value">Time value</a>
     */
    @Nonnull
    Money<C> extrinsicValue();

    /**
     * @return the intrinsic value plus the time value.
     */
    @Override
    default Money<C> value() {
        return this.intrinsicValue().plus(this.extrinsicValue());
    }

    @Override
    default EvaluatedOptionPrice<C> evaluate() {
        return new EvaluatedOptionPrice<>(this.underlyingPrice(), this.intrinsicValue(), this.value());
    }

    interface Shiftable<C extends CurrencyId> extends OptionPrice<C>, ShiftablePrice<C> {

        @Nonnull
        @CheckReturnValue
        Shiftable<C> shift(OptionPriceShifts shifts);

        @Override
        @Deprecated
        default ShiftablePrice<C> shift(final InstrumentPriceShifts shifts) {
            return this.shift(OptionPriceShifts.cast(shifts));
        }

    }

}
