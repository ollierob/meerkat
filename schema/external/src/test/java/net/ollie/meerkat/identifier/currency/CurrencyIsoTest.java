package net.ollie.meerkat.identifier.currency;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import net.ollie.meerkat.identifier.country.CountryIso;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class CurrencyIsoTest {

    @Test
    public void testToCountryIso() {
        final CurrencyIso currency = CurrencyIso.EUR;
        assertThat(currency.countryId(), is(CountryIso.valueOf("EU")));
    }

}
