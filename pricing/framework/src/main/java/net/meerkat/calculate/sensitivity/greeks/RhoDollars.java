package net.meerkat.calculate.sensitivity.greeks;

import net.meerkat.calculate.sensitivity.DollarSensitivity;
import net.meerkat.calculate.sensitivity.SensitivityId;
import net.meerkat.identifier.currency.USD;
import net.meerkat.money.Money;

/**
 * Sensitivity of derivative price to a change in the risk-free interest rate.
 *
 * @author ollie
 */
public class RhoDollars
        extends DollarSensitivity<RhoDollars>
        implements Greek {

    public static final SensitivityId<RhoDollars> ID = SensitivityId.summing("Rho$", RhoDollars.class);
    public static final RhoDollars ZERO = new RhoDollars(ZERO_USD);

    public RhoDollars(final Money<USD> dollars) {
        super(dollars);
    }

    @Override
    public SensitivityId<RhoDollars> sensitivityId() {
        return ID;
    }

    @Override
    protected RhoDollars with(final Money<USD> dollars) {
        return new RhoDollars(dollars);
    }

}
