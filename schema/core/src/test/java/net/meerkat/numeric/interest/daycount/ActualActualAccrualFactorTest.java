package net.meerkat.numeric.interest.daycount;

import net.meerkat.temporal.date.count.ActualActualDateArithmetic;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 *
 * @author ollie
 */
public class ActualActualAccrualFactorTest {

    @Test
    public void testActAct() {
        final ActualActualDateArithmetic testFactor = ActualActualDateArithmetic.ACT_ACT;
        final LocalDate date = LocalDate.now();
        assertThat(testFactor.daysBetween(date.minusDays(1), date), is(1));
        assertThat(testFactor.daysBetween(date, date), is(0));
        assertThat(testFactor.daysBetween(date, date.plusDays(1)), is(1));

    }

}
