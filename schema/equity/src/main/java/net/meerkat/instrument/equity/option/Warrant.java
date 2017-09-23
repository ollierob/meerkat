package net.meerkat.instrument.equity.option;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.derivative.option.AbstractOption;
import net.meerkat.instrument.derivative.option.exercise.OptionExercise;
import net.meerkat.instrument.equity.CommonStock;
import net.meerkat.instrument.equity.EquityDerivative;
import net.meerkat.issue.Issue;
import net.meerkat.money.Money;

/**
 *
 * @author ollie
 */
public class Warrant
        extends AbstractOption<CommonStock>
        implements EquityDerivative<CommonStock> {

    private final CommonStock underlying;

    public Warrant(final String name, InstrumentIds identifiers, Issue issue, OptionExercise exercise, Money<?> premium, Money<?> strike, Number contractMultiplier, final CommonStock underlying) {
        super(name, identifiers, issue, exercise, premium, strike, contractMultiplier);
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
