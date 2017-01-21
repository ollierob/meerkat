package net.ollie.meerkat.identifier.currency;

import net.ollie.meerkat.identifier.country.CountryIso;
import net.ollie.meerkat.identifier.country.HasCountryId;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class CurrencyIsoTest {

    @Test
    public void testToCountryIso() {
        final CurrencyIso currency = CurrencyIso.EUR;
        assertFalse(currency.isReserved());
        assertThat(currency, instanceOf(HasCountryId.class));
        assertThat(((HasCountryId) currency).countryId(), is(CountryIso.valueOf("EU")));
    }

}
