package net.meerkat.calculate.sensitivity.greeks;

import net.meerkat.calculate.sensitivity.DollarSensitivity;
import net.meerkat.calculate.sensitivity.SensitivityId;
import net.meerkat.identifier.currency.USD;
import net.meerkat.money.Money;

/**
 *
 * @author ollie
 */
public class DeltaDollars
        extends DollarSensitivity<DeltaDollars>
        implements Greek {

    public static final SensitivityId<DeltaDollars> ID = SensitivityId.summing("Delta$", DeltaDollars.class);
    public static final DeltaDollars ZERO = new DeltaDollars(ZERO_USD);

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
    protected DeltaDollars with(final Money<USD> dollars) {
        return new DeltaDollars(dollars);
    }

    @Override
    public SensitivityId<DeltaDollars> sensitivityId() {
        return ID;
    }

}
