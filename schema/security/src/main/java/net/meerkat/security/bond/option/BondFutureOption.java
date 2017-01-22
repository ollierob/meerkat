package net.meerkat.security.bond.option;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.money.Money;
import net.meerkat.identifier.security.SecurityIds;
import net.meerkat.numeric.quantity.Quantity;
import net.meerkat.security.bond.BondDerivative;
import net.meerkat.security.interest.future.BondFuture;
import net.meerkat.security.derivative.option.AbstractOption;
import net.meerkat.security.derivative.option.exercise.OptionExercise;

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
            final SecurityIds identifiers,
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