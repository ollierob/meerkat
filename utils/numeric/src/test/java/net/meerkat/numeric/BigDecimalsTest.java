package net.meerkat.numeric;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class BigDecimalsTest {

    @Test
    public void shouldConvert_Long_Wrap() {
        final long l = (long) Integer.MAX_VALUE + (long) Integer.MAX_VALUE + 2L;
        assertTrue(l > 0);
        assertTrue((int) l == 0);
        assertThat(BigDecimals.toBigDecimal(l), not(BigDecimal.ZERO));
    }

}
