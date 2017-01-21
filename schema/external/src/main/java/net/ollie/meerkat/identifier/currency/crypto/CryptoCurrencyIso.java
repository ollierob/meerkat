package net.ollie.meerkat.identifier.currency.crypto;

import net.ollie.goat.money.MoneyFormat;
import net.ollie.meerkat.identifier.currency.CurrencyIso;

/**
 *
 * @author ollie
 */
public interface CryptoCurrencyIso extends CurrencyIso {

    @Override
    default MoneyFormat format() {
        return MoneyFormat.SYMBOL_AMOUNT;
    }

    @Override
    default String uniqueSymbol() {
        return this.value();
    }

    XBT Bitcoin = new XBT();

}
