package net.ollie.meerkat.calculate.price.interest;

import java.time.LocalDate;
import java.util.function.Function;

import net.ollie.goat.money.Money;
import net.ollie.goat.money.currency.Currency;
import net.ollie.goat.money.fx.ExchangeRate;
import net.ollie.goat.money.interest.InterestRate;
import net.ollie.goat.temporal.date.interim.CompleteInterval;
import net.ollie.meerkat.calculate.fx.ExchangeRateCalculator;
import net.ollie.meerkat.calculate.price.InterestAccruedPrice;
import net.ollie.meerkat.calculate.price.ShiftableSecurityPrice;
import net.ollie.meerkat.security.interest.CertificateOfDeposit;
import net.ollie.meerkat.security.interest.MoneyMarketSecurity;

/**
 *
 * @author ollie
 */
public class NativeMoneyMarketPricer
        implements MoneyMarketPricer {

    private final Function<? super LocalDate, ? extends ExchangeRateCalculator> getExchangeRates;

    public NativeMoneyMarketPricer(Function<? super LocalDate, ? extends ExchangeRateCalculator> getExchangeRates) {
        this.getExchangeRates = getExchangeRates;
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

        ExchangeRateCalculator fxRates() {
            return getExchangeRates.apply(date);
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
