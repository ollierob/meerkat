package net.ollie.meerkat.numeric.interest;

import net.ollie.goat.money.interest.InterestRate;

import java.time.LocalDate;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlTransient;

import net.ollie.meerkat.numeric.interest.curve.InterestRateCurve;
import net.ollie.goat.numeric.percentage.Percentage;

/**
 *
 * @author ollie
 */
@XmlTransient
public abstract class FixedInterestRate implements InterestRate, Comparable<FixedInterestRate> {

    @Nonnull
    public abstract Percentage annualRate();

    public boolean isNegative() {
        return this.annualRate().isNegative();
    }

    @Override
    @Deprecated
    public Percentage fixing(final LocalDate date) {
        return this.annualRate();
    }

    @Override
    public FixedInterestRate plus(final Percentage bump) {
        return this.with(this.annualRate().plus(bump));
    }

    @Override
    public int compareTo(final FixedInterestRate that) {
        return this.annualRate().compareTo(that.annualRate());
    }

    public abstract FixedInterestRate with(Percentage rate);

    @Nonnull
    public InterestRateCurve toCurve() {
        return InterestRateCurve.flat(this.annualRate());
    }

}
