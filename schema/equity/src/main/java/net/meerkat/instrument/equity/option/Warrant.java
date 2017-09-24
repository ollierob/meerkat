package net.meerkat.instrument.equity.option;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.derivative.option.AbstractOption;
import net.meerkat.instrument.derivative.option.exercise.OptionExercise;
import net.meerkat.instrument.equity.CommonStock;
import net.meerkat.instrument.equity.EquityDerivative;
import net.meerkat.issuer.IssuerId;
import net.meerkat.money.Money;

/**
 *
 * @author ollie
 */
public class Warrant
        extends AbstractOption<CommonStock>
        implements EquityDerivative<CommonStock> {

    private final CommonStock underlying;

    public Warrant(
            final String name,
            final InstrumentIds identifiers,
            final IssuerId issuerId,
            final OptionExercise exercise,
            final Money<?> premium,
            final Money<?> strike,
            final Number contractMultiplier,
            final CommonStock underlying) {
        super(name, identifiers, issuerId, exercise, premium, strike, contractMultiplier);
        this.underlying = underlying;
    }

    @Override
    public CommonStock underlying() {
        return underlying;
    }

    @Override
    public <R> R handleWith(final EquityDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
