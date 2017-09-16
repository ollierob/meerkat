package net.meerkat.identifier.currency;

import javax.annotation.Nonnull;

import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.money.MoneyFormat;
import net.meerkat.utils.Classes.Castable;

/**
 * A monetary unit.
 *
 * @author Ollie
 */
public interface CurrencyId extends HasCurrencyId, InstrumentId, Castable {

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
        return MoneyFormat.SYMBOL_AMOUNT;
    }

}
