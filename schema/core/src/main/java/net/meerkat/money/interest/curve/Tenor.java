package net.meerkat.money.interest.curve;

import java.time.Period;

import net.ollie.goat.numeric.Numeric;

/**
 *
 * @author Ollie
 */
public interface Tenor extends Numeric<Tenor> {
    
    Period period();

}
