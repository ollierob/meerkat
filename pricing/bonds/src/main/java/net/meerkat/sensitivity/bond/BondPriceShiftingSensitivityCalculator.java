package net.meerkat.sensitivity.bond;

import java.time.temporal.Temporal;

import net.meerkat.Explained;
import net.meerkat.calculate.sensitivity.yield.DollarDuration;
import net.meerkat.identifier.currency.CurrencyIso;
import net.meerkat.identifier.currency.USD;
import net.meerkat.instrument.bond.Bond;
import net.meerkat.money.Money;
import net.meerkat.pricing.bond.BondPrice;
import net.meerkat.pricing.bond.EvaluatedBondPrice;
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
    public BondUnitPriceSensitivities sensitivities(final T date, final Bond bond) {
        final BondPrice.Shiftable<USD> price = pricer.price(date, bond, CurrencyIso.USD);
        return new BondSensitivitiesCalculation(date, bond, price);
    }

    class BondSensitivitiesCalculation implements BondUnitPriceSensitivities {

        private final T date;
        private final Bond bond;
        private final BondPrice.Shiftable<USD> price;

        BondSensitivitiesCalculation(final T date, Bond bond, final BondPrice.Shiftable<USD> price) {
            this.date = date;
            this.bond = bond;
            this.price = price;
        }

        @Override
        public EvaluatedBondPrice<?> price() {
            return price.evaluate();
        }

        Explained<DollarDuration> explainDollarDuration() {
            final BondPrice.Shiftable<USD> shiftedPrice = price.shift(ONE_BP_YIELD_SHIFT);
            final Money<USD> difference = shiftedPrice.dirty().minus(price.dirty());
            return new Explained<>(
                    new DollarDuration(difference),
                    ex -> ex.put("base price", price)
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
