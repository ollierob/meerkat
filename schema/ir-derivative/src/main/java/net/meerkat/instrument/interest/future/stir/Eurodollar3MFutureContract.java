package net.meerkat.instrument.interest.future.stir;

import java.time.Month;
import java.time.Period;
import java.util.Set;

import net.meerkat.identifier.currency.USD;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.NamedInstrument;
import net.meerkat.money.Money;
import net.ollie.goat.temporal.date.Periods;

/**
 *
 * @author ollie
 */
public class Eurodollar3MFutureContract
        extends NamedInstrument
        implements InterestRateFutureContract<USD> {

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
    public Set<Month> deliveryMonths() {
        return MAR_JUN_SEP_DEC;
    }

}
