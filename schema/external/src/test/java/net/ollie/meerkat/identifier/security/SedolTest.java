package net.ollie.meerkat.identifier.security;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import net.ollie.meerkat.identifier.country.CountryIso;

/**
 *
 * @author ollie
 */
public class SedolTest {

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
