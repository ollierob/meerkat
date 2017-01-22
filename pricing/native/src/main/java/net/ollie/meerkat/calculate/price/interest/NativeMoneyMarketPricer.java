package net.ollie.meerkat.calculate.price.interest;

import java.time.LocalDate;

import net.meerkat.money.Money;
import net.meerkat.money.currency.Currency;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.money.interest.InterestRate;
import net.ollie.goat.temporal.date.interim.CompleteInterval;
import net.ollie.meerkat.calculate.price.InterestAccruedPrice;
import net.ollie.meerkat.calculate.price.ShiftableSecurityPrice;
import net.meerkat.security.interest.CertificateOfDeposit;
import net.meerkat.security.interest.MoneyMarketSecurity;
import net.meerkat.money.fx.ExchangeRates;
import net.ollie.meerkat.calculate.fx.ExchangeRatesProvider;

/**
 *
 * @author ollie
 */
public class NativeMoneyMarketPricer
        implements MoneyMarketPricer {

    private final ExchangeRatesProvider<LocalDate> exchangeRatesProvider;

    public NativeMoneyMarketPricer(final ExchangeRatesProvider<LocalDate> exchangeRatesProvider) {
        this.exchangeRatesProvider = exchangeRatesProvider;
    }

    @Override
    public <C extends Currency> MoneyMarketPriceContext<C> priceContext(final LocalDate date, final C currency) {
        return new NativeMoneyMarketPriceContext<>(currency, date);
    }

    private final class NativeMoneyMarketPriceContext<C extends Currency> implements MoneyMarketPriceContext<C> {

        private final C currency;
        private final LocalDate date;

        NativeMoneyMarketPriceContext(final C currency, final LocalDate date) {
            this.currency = currency;
            this.date = date;
        }

        ExchangeRates fxRates() {
            return exchangeRatesProvider.get(date);
        }

        @Override
        public ShiftableSecurityPrice<C> handle(final CertificateOfDeposit cd) {
            return this.price(cd, cd.notional(), cd.rate(), cd.maturity());
        }

        private <F extends Currency> InterestAccruedPrice<F, C> price(
                final MoneyMarketSecurity security,
                final Money<F> notional,
                final InterestRate interestRate,
                final LocalDate maturity) {
            final ExchangeRate<F, C> fxRate = this.fxRates().rate(notional.currency(), currency);
            final CompleteInterval period = new CompleteInterval(date, maturity);
            return new InterestAccruedPrice<>(security, notional, interestRate, fxRate, period, null);
        }

    }

}
