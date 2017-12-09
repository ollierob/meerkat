package net.meerkat.pricing.bond;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.price.Price;
import net.meerkat.numeric.percentage.BigDecimalFractionPercentage;
import net.meerkat.numeric.percentage.Percentage;
import net.meerkat.pricing.ShiftablePrice;
import net.meerkat.pricing.bond.shifts.BondShifts;
import net.meerkat.pricing.shifts.InstrumentPriceShifts;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 * @see BondSensitivities
 */
public interface BondPrice<C extends CurrencyId>
        extends Price<C> {

    @Nonnull
    Money<C> par();

    @Nonnull
    Money<C> clean();

    @Nonnull
    Money<C> dirty();

    @Override
    default C currencyId() {
        return this.clean().currencyId();
    }

    @Nonnull
    default Percentage cleanPercent() {
        return BigDecimalFractionPercentage.of(this.par().amount(), this.clean().amount());
    }

    @Nonnull
    default Percentage dirtyPercent() {
        return BigDecimalFractionPercentage.of(this.par().amount(), this.dirty().amount());
    }

    default boolean isPremium() {
        return this.dirty().compareTo(this.par()) > 0;
    }

    @Nonnull
    default Money<C> accruedInterest() {
        return this.dirty().minus(this.clean());
    }

    @Nonnull
    Percentage yieldToMaturity();

    @Nonnull
    default Money<C> marketValue(final Number quantity) {
        return this.dirty().times(quantity);
    }

    @Override
    default EvaluatedBondPrice<C> evaluate() {
        return new EvaluatedBondPrice<>(this.clean(), this.dirty(), this.par(), this.yieldToMaturity());
    }

    @Override
    default ExplanationBuilder explain() {
        return this.explanationBuilder()
                .put("clean", this.clean())
                .put("dirty", this.dirty())
                .put("par", this.par());
    }

    interface Shiftable<C extends CurrencyId>
            extends BondPrice<C>, ShiftablePrice<C> {

        @Override
        default BondPrice.Shiftable<C> shift(final InstrumentPriceShifts shifts) {
            return this.shift(BondShifts.cast(shifts));
        }

        @CheckReturnValue
        BondPrice.Shiftable<C> shift(BondShifts shifts);

    }

}
