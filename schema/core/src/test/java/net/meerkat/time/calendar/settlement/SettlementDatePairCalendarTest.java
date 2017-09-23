package net.meerkat.time.calendar.settlement;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class SettlementDatePairCalendarTest {

    @Test
    public void testArabSettlement() {

        final SettlementDateCalendar usSettlements = mock(SettlementDateCalendar.class);
        when(usSettlements.isInRange(any(LocalDate.class))).thenReturn(true);

        final SettlementDateCalendar arabSettlements = mock(SettlementDateCalendar.class);
        when(arabSettlements.isInRange(any(LocalDate.class))).thenReturn(true);

        final SettlementDatePairCalendar pair = new SettlementDatePairCalendar(usSettlements, arabSettlements);

        final LocalDate thursday = LocalDate.of(2017, 9, 21);
        final LocalDate friday = thursday.plusDays(1);
        final LocalDate saturday = friday.plusDays(1);
        final LocalDate monday = saturday.plusDays(2);
        final LocalDate tuesday = monday.plusDays(1);

        when(usSettlements.next(thursday)).thenReturn(new SettlementDate(monday));
        when(usSettlements.next(friday)).thenReturn(new SettlementDate(tuesday));
        when(usSettlements.next(saturday)).thenReturn(new SettlementDate(tuesday));

        when(arabSettlements.contains(thursday)).thenReturn(true);
        when(arabSettlements.contains(monday)).thenReturn(true);
        when(arabSettlements.contains(tuesday)).thenReturn(true);

        //Then
        assertThat(pair.next(thursday).date(), is(monday));
        assertThat(pair.next(friday).date(), is(tuesday));
        assertThat(pair.next(saturday).date(), is(tuesday));

    }

}
