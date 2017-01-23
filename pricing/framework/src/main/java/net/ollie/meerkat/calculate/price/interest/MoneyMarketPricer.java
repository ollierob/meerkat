package net.ollie.meerkat.calculate.price.interest;

import java.time.LocalDate;

import net.ollie.meerkat.calculate.price.ShiftableSecurityPrice;
import net.meerkat.instrument.interest.CertificateOfDeposit;
import net.meerkat.instrument.interest.MoneyMarketSecurity;
import net.meerkat.money.currency.CurrencyId;

/**
 *
 * @author ollie
 */
public interface MoneyMarketPricer {

    default <C extends CurrencyId> ShiftableSecurityPrice<C> price(
            LocalDate date,
            CertificateOfDeposit certificateOfDeposit,
            C currency) {
        return this.priceContext(date, currency).handle(certificateOfDeposit);
    }

    <C extends CurrencyId> MoneyMarketPriceContext<C> priceContext(LocalDate date, C currency);

    interface MoneyMarketPriceContext<C extends CurrencyId> extends MoneyMarketSecurity.Handler<ShiftableSecurityPrice<C>> {

    }

}
