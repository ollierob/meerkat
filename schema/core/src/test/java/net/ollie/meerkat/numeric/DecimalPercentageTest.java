package net.ollie.meerkat.numeric;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author ollie
 */
public class DecimalPercentageTest {

    @Test
    public void testInverse() {
        assertThat(DecimalPercentage.ZERO_PERCENT.inverse(), is(DecimalPercentage.ONE_HUNDRED_PERCENT));
    }

}
