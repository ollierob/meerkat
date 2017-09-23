package net.meerkat.identifier.currency;

import net.meerkat.identifier.country.CountryIso;
import net.meerkat.identifier.country.HasCountryId;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

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
