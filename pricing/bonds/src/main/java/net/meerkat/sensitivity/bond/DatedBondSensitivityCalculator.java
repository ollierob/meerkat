package net.meerkat.sensitivity.bond;

import java.time.LocalDate;
import java.util.Optional;

import net.meerkat.Explained;
import net.meerkat.calculate.sensitivity.Sensitivity;
import net.meerkat.calculate.sensitivity.SensitivityId;
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
public class DatedBondSensitivityCalculator
        implements GenericBondSensitivityCalculator<LocalDate> {

    private static final BondShifts ONE_BP_YIELD_SHIFT = BondShifts.absoluteYield(Percentage.oneBasisPoint());

    private final GenericBondPricer<LocalDate> pricer;

    public DatedBondSensitivityCalculator(final GenericBondPricer<LocalDate> pricer) {
        this.pricer = pricer;
    }

    @Override
    public BondSensitivities sensitivities(final LocalDate date, final Bond bond) {
        return new DatedBondSensitivities(date, bond);
    }

    class DatedBondSensitivities implements BondSensitivities {

        private final LocalDate date;
        private final Bond bond;

        DatedBondSensitivities(LocalDate date, Bond bond) {
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
        public <S extends Sensitivity> Optional<S> get(final SensitivityId<S> sensitivityId) {
            throw new UnsupportedOperationException("Not supported yet.");
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
