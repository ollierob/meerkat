package net.ollie.meerkat.numeric.interest.daycount;

import net.ollie.goat.temporal.date.count.ActualActualAccrualFactor;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author ollie
 */
public class ActualActualAccrualFactorTest {

    @Test
    public void testActAct() {
        final ActualActualAccrualFactor testFactor = ActualActualAccrualFactor.ACT_ACT;
        final LocalDate date = LocalDate.now();
        assertThat(testFactor.daysBetween(date.minusDays(1), date), is(1));
        assertThat(testFactor.daysBetween(date, date), is(0));
        assertThat(testFactor.daysBetween(date, date.plusDays(1)), is(1));

    }

}
