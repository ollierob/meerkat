package net.meerkat.identifier.country;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

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