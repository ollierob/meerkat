package net.meerkat.instrument.interest.option;

import net.meerkat.instrument.bond.Bond;
import net.meerkat.instrument.derivative.option.AbstractOption;

/**
 *
 * @author ollie
 */
public class BondOption extends AbstractOption<Bond> {

    private Bond underlying;

    @Override
    public Bond underlying() {
        return underlying;
    }

}
