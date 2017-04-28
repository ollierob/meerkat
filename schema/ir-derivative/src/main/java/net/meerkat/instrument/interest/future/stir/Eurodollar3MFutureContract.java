package net.meerkat.instrument.interest.future.stir;

import java.time.Period;

import net.meerkat.identifier.currency.USD;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.NamedInstrument;
import net.meerkat.money.Money;
import net.ollie.goat.temporal.date.Periods;
import net.ollie.goat.temporal.date.months.Months;

/**
 *
 * @author ollie
 */
public class Eurodollar3MFutureContract
        extends NamedInstrument
        implements ShortTermInterestRateFutureContract<USD> {

    private static final long serialVersionUID = 1L;

    public Eurodollar3MFutureContract(final String name, final InstrumentIds ids) {
        super(name, ids);
    }

    @Override
    public Money<USD> notional() {
        return USD.ONE_MILLION;
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
