package net.meerkat.numeric.decimal;

import net.meerkat.numeric.Numeric;
import net.meerkat.numeric.NumericTest;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class SmallDecimalTest extends NumericTest {

    private static final byte ONE_DP = (byte) 1;

    @Override
    protected Numeric<?> valueOf(final long l) {
        return SmallDecimal.valueOf(l);
    }

    @Test
    public void testDecimalValue() {
        assertThat(new SmallDecimal(0.001, ONE_DP).decimalValue().toString(), is("0.0"));
    }

    @Test
    public void testIsZero_Ones() {
        assertFalse(new SmallDecimal(0.1, ONE_DP).isZero());
        assertTrue(new SmallDecimal(0.01, ONE_DP).isZero());
        assertTrue(new SmallDecimal(0.001, ONE_DP).isZero());
    }

    @Test
    public void testIsZero_Nines() {
        assertFalse(new SmallDecimal(0.9, ONE_DP).isZero());
        System.out.println(new SmallDecimal(0.09, ONE_DP).decimalValue());
        assertFalse(new SmallDecimal(0.09, ONE_DP).decimalValue().signum() == 0, "0.09 -> BigDecimal should be zero");
        assertFalse(new SmallDecimal(0.09, ONE_DP).isZero(), "0.09 to 1dp should be zero");
        assertTrue(new SmallDecimal(0.009, ONE_DP).isZero());
    }

    @Test
    public void testPlus_UseMinScale() {
        final SmallDecimal s1 = new SmallDecimal(1.2, ONE_DP);
        final SmallDecimal s2 = new SmallDecimal(3.45, (byte) 2);
        final SmallDecimal s3 = s1.plus(s2);
        assertThat(s3, is(new SmallDecimal(4.7, ONE_DP)));
    }

}
