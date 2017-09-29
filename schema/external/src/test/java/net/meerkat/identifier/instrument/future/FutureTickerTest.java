package net.meerkat.identifier.instrument.future;

import java.time.Month;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class FutureTickerTest {

    @Test
    public void testTickerString() {
        assertThat(new FutureTicker(FutureUnderlyingId.CRUDE_OIL, FutureDeliveryYearMonth.of(Month.MARCH, 2018)).toTickerString(), is("CLH8"));
    }

}
