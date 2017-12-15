package net.meerkat.instrument.equity.option;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.derivative.option.AbstractOption;
import net.meerkat.instrument.derivative.option.exercise.OptionExercise;
import net.meerkat.instrument.equity.Equity;
import net.meerkat.issuer.IssuerId;
import net.meerkat.money.Money;

/**
 *
 * @author ollie
 */
public abstract class AbstractEquityOption<E extends Equity>
        extends AbstractOption<E>
        implements EquityOption<E> {

    protected AbstractEquityOption(
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
