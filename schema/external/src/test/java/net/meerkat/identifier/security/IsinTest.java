package net.meerkat.identifier.security;

import net.meerkat.identifier.country.CountryIso;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ollie
 */
public class IsinTest {

    @Test
    public void testUsIsin() {
        final Isin isin = Isin.valueOf("US0378331005");
        assertThat(isin.country(), is(CountryIso.valueOf("US")));
        assertThat("Actual check digit", isin.checkDigit(), is('5'));
        assertThat("Computed check digit", isin.computeCheckDigit(), is('5'));
        assertTrue(isin.isValid());
    }

}
