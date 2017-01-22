package net.meerkat.identifier.security;

import net.meerkat.identifier.security.OptionSymbol;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

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
