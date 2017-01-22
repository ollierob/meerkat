package net.ollie.meerkat.calculate.price.interest;

import java.time.LocalDate;

import net.ollie.meerkat.money.currency.Currency;
import net.ollie.meerkat.calculate.price.ShiftableSecurityPrice;
import net.meerkat.security.interest.CertificateOfDeposit;
import net.meerkat.security.interest.MoneyMarketSecurity;

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
