package net.meerkat.instrument.bond.call;

import net.meerkat.numeric.percentage.Percentage;

import java.time.LocalDate;

/**
 *
 * @author ollie
 */
public class StandardBondCall implements BondCall {

    private final LocalDate earliest;
    private final Percentage premium;

    public StandardBondCall(LocalDate earliest, Percentage premium) {
        this.earliest = earliest;
        this.premium = premium;
    }

    @Override
    public LocalDate earliest() {
        return earliest;
    }

    @Override
    public Percentage premium() {
        return premium;
    }

}
