package net.meerkat.instrument.interest.option;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.bond.Bond;
import net.meerkat.instrument.derivative.option.AbstractOption;
import net.meerkat.instrument.derivative.option.exercise.OptionExercise;
import net.meerkat.issue.Issue;
import net.meerkat.money.Money;

/**
 *
 * @author ollie
 */
public class BondOption extends AbstractOption<Bond> {

    private static final long serialVersionUID = 1L;

    private final Bond underlying;

    public BondOption(String name, InstrumentIds identifiers, Issue issue, OptionExercise exercise, Money<?> premium, Money<?> strike, Number contractMultiplier, final Bond underlying) {
        super(name, identifiers, issue, exercise, premium, strike, contractMultiplier);
        this.underlying = underlying;
    }

    @Override
    public Bond underlying() {
        return underlying;
    }

}
