package net.meerkat.money.interest;

import net.meerkat.objects.HasName;

import javax.annotation.Nonnull;

/**
 * @author ollie
 */
public class NamedInterestRateId implements InterestRateId, HasName {

    private final String name;

    public NamedInterestRateId(final String name) {
        this.name = name;
    }

    @Nonnull
    @Override
    public String name() {
        return name;
    }

}
