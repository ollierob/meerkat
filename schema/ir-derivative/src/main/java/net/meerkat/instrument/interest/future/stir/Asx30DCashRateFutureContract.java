package net.meerkat.instrument.interest.future.stir;

import net.meerkat.identifier.currency.AUD;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.NamedInstrument;
import net.meerkat.money.Money;
import net.meerkat.temporal.date.months.Months;

import java.time.Period;

/**
 * @author ollie
 */
public class Asx30DCashRateFutureContract
        extends NamedInstrument
        implements ShortTermInterestRateFutureContract<AUD> {

    private static final long serialVersionUID = 1L;
    private static final Money<AUD> NOTIONAL = Money.of(AUD.AUD, 3_000_000);

    public Asx30DCashRateFutureContract(final String name, final InstrumentIds ids) {
        super(name, ids);
    }

    @Override
    public Money<AUD> notional() {
        return NOTIONAL;
    }

    @Override
    public Period term() {
        return Period.ofDays(30);
    }

    @Override
    public Months deliveryMonths() {
        return ALL_MONTHS;
    }

}
