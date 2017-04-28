package net.meerkat.instrument.interest.future.stir;

import java.time.Month;
import java.time.Period;
import java.util.Set;

import net.meerkat.identifier.currency.AUD;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.NamedInstrument;
import net.meerkat.money.Money;

/**
 *
 * @author ollie
 */
public class Asx30DCashRateFutureContract
        extends NamedInstrument
        implements InterestRateFutureContract<AUD> {

    private static final long serialVersionUID = 1L;
    private static final Money<AUD> NOTIONAL = Money.of(3_000_000, AUD.AUD);

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
    public Set<Month> deliveryMonths() {
        return ALL_MONTHS;
    }

}
