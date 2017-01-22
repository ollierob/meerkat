package net.meerkat.identifier.security;

import net.meerkat.identifier.security.Isin;
import net.meerkat.identifier.security.Sedol;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import net.meerkat.identifier.country.CountryIso;

/**
 *
 * @author ollie
 */
public class SedolTest {

    @Test
    public void testCheckDigit_GB() {
        final Sedol sedol = new Sedol("BY2ZN89");
        assertThat(sedol.checkDigit(), is('9'));
        assertTrue(sedol.isValid());
    }

    @Test
    public void testToIsin_GB() {
        final Sedol sedol = new Sedol("BY2ZN89");
        assertThat(sedol.isinPart(), is("00BY2ZN89"));
        final Isin isin = sedol.toIsin(CountryIso.valueOf("GB"));
        assertThat(isin.toString(), is("GB00BY2ZN893"));
    }

    @Test
    public void testToIsin_IE() {
        final Sedol sedol = new Sedol("BDSTXT9");
        final Isin isin = sedol.toIsin(CountryIso.valueOf("IE"));
        assertThat(isin.toString(), is("IE00BDSTXT90"));
    }

}
