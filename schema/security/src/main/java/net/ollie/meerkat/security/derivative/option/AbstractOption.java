package net.ollie.meerkat.security.derivative.option;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.identifier.security.SecurityIds;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.numeric.quantity.Quantity;
import net.ollie.meerkat.security.NamedSecurity;
import net.ollie.meerkat.security.Security;
import net.ollie.meerkat.security.derivative.option.exercise.OptionExercise;

/**
 *
 * @author ollie
 */
public abstract class AbstractOption<S extends Security>
        extends NamedSecurity
        implements Option<S> {

    @XmlElementRef(name = "exercise")
    private OptionExercise exercise;

    @XmlElement(name = "premium")
    private Money<?> premium;

    @XmlElement(name = "strike")
    private Money<?> strike;

    @XmlElementRef(name = "multiplier")
    private Quantity contractMultiplier;

    @Deprecated
    protected AbstractOption() {
    }

    public AbstractOption(
            final String name,
            final SecurityIds identifiers,
            final OptionExercise exercise,
            final Money<?> premium,
            final Money<?> strike,
            final Quantity contractMultiplier) {
        super(name, identifiers);
        this.exercise = exercise;
        this.premium = premium;
        this.strike = strike;
        this.contractMultiplier = contractMultiplier;
    }

    @Override
    public OptionExercise exercise() {
        return exercise;
    }

    @Override
    public Money<?> strike() {
        return strike;
    }

    @Override
    public Money<?> premium() {
        return premium;
    }

    @Override
    public Quantity contractMultiplier() {
        return contractMultiplier;
    }

}
