package net.meerkat.identifier.currency;

import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.money.MoneyFormat;

import javax.annotation.Nonnull;

/**
 * A monetary unit.
 *
 * @author Ollie
 */
public interface CurrencyId extends HasCurrencyId, InstrumentId {

    /**
     * @return the character(s) that are used, for example {@code $} for US dollars.
     */
    @Nonnull
    String symbol();

    /**
     * @return a string uniquely representing this currency.
     */
    @Nonnull
    String uniqueSymbol();

    @Override
    @Deprecated
    default CurrencyId currencyId() {
        return this;
    }

    @Override
    @Deprecated
    default CurrencyId instrumentId() {
        return this;
    }

    @Nonnull
    default MoneyFormat format() {
        return MoneyFormat.UNIQUE_SYMBOL_AMOUNT;
    }

}
