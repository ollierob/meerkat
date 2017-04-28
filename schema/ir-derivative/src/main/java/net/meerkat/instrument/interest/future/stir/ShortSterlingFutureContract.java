package net.meerkat.instrument.interest.future.stir;

import java.time.Period;

import net.meerkat.identifier.currency.GBP;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.NamedInstrument;
import net.meerkat.money.Money;
import net.ollie.goat.temporal.date.Periods;
import net.ollie.goat.temporal.date.months.Months;

/**
 *
 * @author ollie
 */
public class ShortSterlingFutureContract
        extends NamedInstrument
        implements ShortTermInterestRateFutureContract<GBP> {

    private static final long serialVersionUID = 1L;
    private static final Money<GBP> NOTIONAL = Money.of(500_000, GBP.GBP);

    public ShortSterlingFutureContract(final InstrumentIds ids) {
        this("Short Sterling", ids);
    }

    public ShortSterlingFutureContract(final String name, final InstrumentIds ids) {
        super(name, ids);
    }

    @Override
    public Money<GBP> notional() {
        return NOTIONAL;
    }

    @Override
    public Period term() {
        return Periods.THREE_MONTHS;
    }

    @Override
    public Months deliveryMonths() {
        return MAR_JUN_SEP_DEC;
    }

}
