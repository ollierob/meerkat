package net.meerkat.instrument.fx.option;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.derivative.option.AbstractOption;
import net.meerkat.instrument.derivative.option.exercise.OptionExercise;
import net.meerkat.instrument.fx.FxDerivative;
import net.meerkat.issue.Issue;
import net.meerkat.money.Money;

/**
 *
 * @author Ollie
 */
public class FxOption
        extends AbstractOption<FxOptionRate<?, ?>>
        implements FxDerivative {

    private static final long serialVersionUID = 1L;

    private final FxOptionRate<?, ?> rate;

    public FxOption(final String name, InstrumentIds identifiers, Issue issue, OptionExercise exercise, Money<?> premium, Money<?> strike, Number contractMultiplier, final FxOptionRate<?, ?> rate) {
        super(name, identifiers, issue, exercise, premium, strike, contractMultiplier);
        this.rate = rate;
    }

    @Override
    public FxOptionRate<?, ?> underlying() {
        return rate;
    }

    @Override
    public <R> R handleWith(final FxDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
