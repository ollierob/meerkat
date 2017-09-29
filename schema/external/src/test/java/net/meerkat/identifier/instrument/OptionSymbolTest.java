package net.meerkat.identifier.instrument;

import net.meerkat.identifier.instrument.option.OptionSymbol;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ollie
 */
public class OptionSymbolTest {

    @Test
    public void testExample() {
        final OptionSymbol symbol = new OptionSymbol("SPX", LocalDate.of(2014, 11, 22), true, new BigDecimal("19.5"));
        assertThat(symbol.toOccString(), is("SPX   141122P00019500"));
    }

}
