package net.meerkat.money.interest.curve;

import net.meerkat.objects.HasName;

import java.time.Period;

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
    public String toTenorString() {
        return name;
    }

    @Override
    public ExplanationBuilder explain() {
        return super.explain()
                .put("name", name);
    }

}
