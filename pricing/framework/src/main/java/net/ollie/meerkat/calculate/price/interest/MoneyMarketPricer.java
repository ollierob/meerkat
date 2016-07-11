package net.ollie.meerkat.calculate.price.interest;

import java.time.LocalDate;

import net.ollie.goat.money.currency.Currency;
import net.ollie.meerkat.calculate.price.ShiftableSecurityPrice;
import net.ollie.meerkat.security.moneymarket.CertificateOfDeposit;
import net.ollie.meerkat.security.moneymarket.MoneyMarketSecurity;

/**
 *
 * @author ollie
 */
public interface MoneyMarketPricer {

    default <C extends Currency> ShiftableSecurityPrice<C> price(
            LocalDate date,
            CertificateOfDeposit certificateOfDeposit,
            C currency) {
        return this.priceContext(date, currency).handle(certificateOfDeposit);
    }

    <C extends Currency> MoneyMarketPriceContext<C> priceContext(LocalDate date, C currency);

    interface MoneyMarketPriceContext<C extends Currency> extends MoneyMarketSecurity.Handler<ShiftableSecurityPrice<C>> {

    }

}
