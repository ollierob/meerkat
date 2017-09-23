package net.meerkat.identifier.currency;

import net.meerkat.identifier.country.HasCountryId;
import net.meerkat.instrument.InstrumentDefinition;

/**
 *
 * @author Ollie
 */
public interface Currency extends HasCurrencyIds, InstrumentDefinition, HasCountryId {

    @Override
    default CurrencyIds currencyIds() {
        return CurrencyIds.of(this.instrumentIds());
    }

}
