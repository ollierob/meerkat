package net.ollie.meerkat.numeric;

import net.ollie.goat.numeric.fraction.DecimalFraction;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class DecimalFractionTest {

    @Test
    public void testToDouble() {
        assertThat(DecimalFraction.of(1, 2).doubleValue(), is(0.5d));
        assertThat(DecimalFraction.of(1, 4).doubleValue(), is(0.25d));
    }

    @Test
    public void testEquality() {
        assertThat(DecimalFraction.of(2, 4), is(DecimalFraction.of(1, 2)));
    }

    @Test
    public void testMultiplication() {
        assertThat(DecimalFraction.of(2, 3).times(DecimalFraction.of(5, 7)), is(DecimalFraction.of(10, 21)));
    }

    @Test
    public void testCompare() {
        final DecimalFraction d1 = DecimalFraction.of(1, 2);
        final DecimalFraction d2 = DecimalFraction.of(1, 3);
        assertThat(d1.compareTo(d1), is(0));
        assertTrue(d1.compareTo(d2) > 0);
        assertTrue(d2.compareTo(d1) < 0);
    }

}
