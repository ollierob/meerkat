package net.ollie.goat.numeric;

import org.junit.jupiter.api.Test;

import java.math.RoundingMode;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class NumbersTest {

    @Test
    public void testRounding_HalfUp() {
        assertThat(Numbers.round(5.5d, RoundingMode.HALF_UP), is(6));
        assertThat(Numbers.round(2.5d, RoundingMode.HALF_UP), is(3));
        assertThat(Numbers.round(1.6d, RoundingMode.HALF_UP), is(2));
        assertThat(Numbers.round(1.5d, RoundingMode.HALF_UP), is(2));
        assertThat(Numbers.round(1.1d, RoundingMode.HALF_UP), is(1));
        assertThat(Numbers.round(1.0d, RoundingMode.HALF_UP), is(1));
        assertThat("0.1", Numbers.round(0.1d, RoundingMode.HALF_UP), is(0));
        assertThat("0", Numbers.round(0d, RoundingMode.HALF_UP), is(0));
        assertThat("-0.1", Numbers.round(-0.1d, RoundingMode.HALF_UP), is(0));
        assertThat("-1", Numbers.round(-1d, RoundingMode.HALF_UP), is(-1));
        assertThat(Numbers.round(-1.1d, RoundingMode.HALF_UP), is(-1));
        assertThat(Numbers.round(-1.6d, RoundingMode.HALF_UP), is(-2));
        assertThat(Numbers.round(-2.5d, RoundingMode.HALF_UP), is(-3));
        assertThat(Numbers.round(-5.5d, RoundingMode.HALF_UP), is(-6));
    }

    @Test
    public void testRounding_HalfEven() {
        assertThat(Numbers.round(5.5d, RoundingMode.HALF_EVEN), is(6));
        assertThat(Numbers.round(2.5d, RoundingMode.HALF_EVEN), is(2));
        assertThat(Numbers.round(1.6d, RoundingMode.HALF_EVEN), is(2));
        assertThat(Numbers.round(1.1d, RoundingMode.HALF_EVEN), is(1));
        assertThat(Numbers.round(1d, RoundingMode.HALF_EVEN), is(1));
        assertThat(Numbers.round(-1d, RoundingMode.HALF_EVEN), is(-1));
        assertThat(Numbers.round(-1.1d, RoundingMode.HALF_EVEN), is(-1));
        assertThat(Numbers.round(-1.6d, RoundingMode.HALF_EVEN), is(-2));
        assertThat(Numbers.round(-2.5d, RoundingMode.HALF_EVEN), is(-2));
        assertThat(Numbers.round(-5.5d, RoundingMode.HALF_EVEN), is(-6));
    }

}
