package net.meerkat.identifier.currency.crypto;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.utils.HasName;

/**
 *
 * @author ollie
 */
public interface CryptoCurrencyId extends CurrencyId, HasName {

    @Override
    default String uniqueSymbol() {
        return this.getClass().getSimpleName() + this.symbol();
    }

    XBT BITCOIN = new XBT();
    ETH ETHEREUM = new ETH();

}
