package net.meerkat.instrument.bond.option;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.money.Money;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.numeric.quantity.Quantity;
import net.meerkat.instrument.bond.BondDerivative;
import net.meerkat.instrument.interest.future.BondFuture;
import net.meerkat.instrument.derivative.option.AbstractOption;
import net.meerkat.instrument.derivative.option.exercise.OptionExercise;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class BondFutureOption
        extends AbstractOption<BondFuture>
        implements BondDerivative<BondFuture> {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "future")
    private BondFuture future;

    @Deprecated
    BondFutureOption() {
    }

    public BondFutureOption(
            final String name,
            final InstrumentIds identifiers,
            final OptionExercise exercise,
            final Money<?> premium,
            final Money<?> strike,
            final Quantity contractMultiplier,
            final BondFuture future) {
        super(name, identifiers, exercise, premium, strike, contractMultiplier);
        this.future = future;
    }

    @Override
    public BondFuture underlying() {
        return future;
    }

    @Override
    public <R> R handleWith(final BondDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
