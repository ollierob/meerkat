package net.meerkat.identifier.country;

import net.meerkat.identifier.country.CountryIso;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class CountryIsoTest {

    public CountryIsoTest() {
    }

    @Test
    public void testEquals() {
        final CountryIso US = CountryIso.US;
        final CountryIso UK = CountryIso.GB;
        assertThat(US, is(CountryIso.valueOf("US")));
        assertThat(US, not(UK));
    }

}