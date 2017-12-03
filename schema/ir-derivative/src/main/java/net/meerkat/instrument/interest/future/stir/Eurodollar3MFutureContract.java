package net.meerkat.instrument.interest.future.stir;

import net.meerkat.identifier.currency.USD;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.NamedInstrument;
import net.meerkat.money.Money;
import net.meerkat.temporal.date.Periods;
import net.meerkat.temporal.date.months.Months;

import java.time.Period;

/**
 *
 * @author ollie
 */
public class Eurodollar3MFutureContract
        extends NamedInstrument
        implements ShortTermInterestRateFutureContract<USD> {

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
