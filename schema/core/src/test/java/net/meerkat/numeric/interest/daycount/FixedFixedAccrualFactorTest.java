package net.meerkat.numeric.interest.daycount;

import net.ollie.goat.temporal.date.count.FixedFixedDateArithmetic;
import net.ollie.goat.temporal.date.count.DayCount;

import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class FixedFixedAccrualFactorTest {

    @Test
    public void test30360() {
        final DayCount dayCount = FixedFixedDateArithmetic.THIRTY_360;
        assertThat(dayCount.daysBetween(LocalDate.of(2006, Month.MAY, 1), LocalDate.of(2006, Month.AUGUST, 1)), is(90));
    }

}
