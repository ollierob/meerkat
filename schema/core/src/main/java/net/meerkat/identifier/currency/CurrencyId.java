package net.meerkat.identifier.currency;

import javax.annotation.Nonnull;

import net.meerkat.money.MoneyFormat;
import net.meerkat.utils.Classes.Castable;

/**
 * A monetary unit.
 *
 * @author Ollie
 */
public interface CurrencyId extends HasCurrencyId, Castable {

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

    @Nonnull
    default MoneyFormat format() {
        return MoneyFormat.SYMBOL_AMOUNT;
    }

}
