package net.meerkat.money.interest.curve;

import java.time.Period;

import net.meerkat.utils.HasName;

/**
 *
 * @author ollie
 */
public class NamedTenor extends Tenor implements HasName {

    private final String name;

    protected NamedTenor(final String name, final Period period) {
        super(period);
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public ExplanationBuilder explain() {
        return super.explain()
                .put("name", name);
    }

}
