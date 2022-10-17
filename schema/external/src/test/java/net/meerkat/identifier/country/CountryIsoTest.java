package net.meerkat.identifier.country;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class CountryIsoTest {

    public CountryIsoTest() {
    }

    @Test
    public void testEquals() {
        final var US = CountryIso.US;
        final var UK = CountryIso.GB;
        assertThat(US, is(CountryIso.valueOf("US")));
        assertThat(US, not(UK));
    }

}