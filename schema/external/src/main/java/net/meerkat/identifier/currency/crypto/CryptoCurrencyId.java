package net.meerkat.identifier.currency.crypto;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.objects.HasName;

/**
 *
 * @author ollie
 */
public interface CryptoCurrencyId extends CurrencyId, HasName {

    @Override
    default String uniqueSymbol() {
        return this.getClass().getSimpleName() + this.symbol();
    }

    BTC BTC = new BTC();
    ETH ETH = new ETH();
    ETC ETC = new ETC();
    DASH DASH = new DASH();
    LTC LTC = new LTC();
    ZEC ZEC = new ZEC();
    XRP XRP = new XRP();
    XMR XMR = new XMR();

}
