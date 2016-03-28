package net.ollie.meerkat.numeric;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author ollie
 */
public class PercentageTest {

    @Test
    public void testInverse() {
        assertThat(Percentage.ZERO_PERCENT.inverse(), is(Percentage.ONE_HUNDRED_PERCENT));
    }

}