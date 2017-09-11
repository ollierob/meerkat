package net.meerkat.calculate.sensitivity.greeks;

import net.meerkat.calculate.sensitivity.DollarSensitivity;
import net.meerkat.calculate.sensitivity.Sensitivity;
import net.meerkat.calculate.sensitivity.SensitivityId;
import net.meerkat.identifier.currency.CurrencyIso;
import net.meerkat.identifier.currency.USD;
import net.meerkat.money.Money;

/**
 *
 * @author ollie
 */
public class DeltaDollars extends DollarSensitivity implements Greek, Sensitivity.Summing<DeltaDollars> {

    public static final SensitivityId<DeltaDollars> ID = SensitivityId.summing(DeltaDollars.class);
    public static final DeltaDollars ZERO = new DeltaDollars(Money.zero(CurrencyIso.USD));

    public static DeltaDollars of(final Delta delta, final Money<USD> value) {
        return of(value.times(delta.value()));
    }

    public static DeltaDollars of(final Money<USD> value) {
        return value.isZero()
                ? ZERO
                : new DeltaDollars(value);
    }

    protected DeltaDollars(final Money<USD> dollars) {
        super(dollars);
    }

    @Override
    public DeltaDollars plus(final DeltaDollars that) {
        return that == null
                ? this
                : of(this.value().plus(that.value()));
    }

    @Override
    public SensitivityId<DeltaDollars> sensitivityId() {
        return ID;
    }

}
