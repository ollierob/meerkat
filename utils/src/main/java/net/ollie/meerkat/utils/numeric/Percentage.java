package net.ollie.meerkat.utils.numeric;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public abstract class Percentage extends Number implements Numeric.Summable<Percentage> {

    private static final long serialVersionUID = 1L;

    public abstract boolean isNegative();

    @Nonnull
    public abstract Percentage inverse();

}
