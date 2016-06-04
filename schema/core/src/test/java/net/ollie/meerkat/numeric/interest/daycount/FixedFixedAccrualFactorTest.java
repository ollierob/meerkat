package net.ollie.meerkat.numeric.interest.daycount;

import net.ollie.goat.money.interest.daycount.FixedFixedAccrualFactor;
import net.ollie.goat.money.interest.daycount.DayCount;

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
        final DayCount dayCount = FixedFixedAccrualFactor.THIRTY_THREESIXTY;
        assertThat(dayCount.daysBetween(LocalDate.of(2006, Month.MAY, 1), LocalDate.of(2006, Month.AUGUST, 1)), is(90));
    }

}
