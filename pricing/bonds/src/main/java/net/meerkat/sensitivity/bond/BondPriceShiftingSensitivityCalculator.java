package net.meerkat.sensitivity.bond;

import java.time.temporal.Temporal;

import net.meerkat.Explained;
import net.meerkat.calculate.sensitivity.yield.DollarDuration;
import net.meerkat.identifier.currency.CurrencyIso;
import net.meerkat.identifier.currency.USD;
import net.meerkat.instrument.bond.Bond;
import net.meerkat.money.Money;
import net.meerkat.pricing.bond.BondPrice;
import net.meerkat.pricing.bond.GenericBondPricer;
import net.meerkat.pricing.bond.shifts.BondShifts;
import net.ollie.goat.numeric.percentage.Percentage;

/**
 *
 * @author ollie
 */
public class BondPriceShiftingSensitivityCalculator<T extends Temporal>
        implements GenericBondSensitivityCalculator<T> {

    private static final BondShifts ONE_BP_YIELD_SHIFT = BondShifts.absoluteYield(Percentage.oneBasisPoint());

    private final GenericBondPricer<T> pricer;

    public BondPriceShiftingSensitivityCalculator(final GenericBondPricer<T> pricer) {
        this.pricer = pricer;
    }

    @Override
    public BondSensitivities sensitivities(final T date, final Bond bond) {
        return new BondSensitivitiesCalculation(date, bond);
    }

    class BondSensitivitiesCalculation implements BondSensitivities {

        private final T date;
        private final Bond bond;

        BondSensitivitiesCalculation(final T date, Bond bond) {
            this.date = date;
            this.bond = bond;
        }

        BondPrice.Shiftable<USD> bondPrice() {
            return pricer.price(date, bond, CurrencyIso.USD);
        }

        Explained<DollarDuration> explainDollarDuration() {
            final BondPrice.Shiftable<USD> price = this.bondPrice();
            final BondPrice.Shiftable<USD> shiftedPrice = price.shift(ONE_BP_YIELD_SHIFT);
            final Money<USD> difference = shiftedPrice.dirty().minus(price.dirty());
            return new Explained<>(
                    new DollarDuration(difference),
                    new ExplanationBuilder()
                            .put("base price", price)
                            .put("shifted price", shiftedPrice)
                            .put("difference", difference));
        }

        @Override
        public DollarDuration dollarDuration() {
            return this.explainDollarDuration().value();
        }

        @Override
        public ExplanationBuilder explain() {
            return this.explanationBuilder()
                    .put("date", date)
                    .put("bond", bond)
                    .put(DollarDuration.ID.name(), this.explainDollarDuration());
        }

    }

}
