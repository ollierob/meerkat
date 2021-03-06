package net.meerkat.instrument.interest.option;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.bond.Bond;
import net.meerkat.instrument.derivative.option.AbstractOption;
import net.meerkat.instrument.derivative.option.exercise.OptionExercise;
import net.meerkat.issuer.IssuerId;
import net.meerkat.money.Money;

/**
 *
 * @author ollie
 */
public class BondOption extends AbstractOption<Bond> {

    public BondOption(
            final String name,
            final InstrumentIds identifiers,
            final IssuerId issuerId,
            final OptionExercise exercise,
            final Money<?> premium,
            final Money<?> strike,
            final InstrumentIds underlyingIds) {
        super(name, identifiers, issuerId, exercise, premium, strike, underlyingIds);
    }

}
