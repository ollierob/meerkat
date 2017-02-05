package net.meerkat.calculate.price.interest;

import java.time.LocalDate;

import net.meerkat.calculate.fx.ExchangeRatesProvider;
import net.meerkat.calculate.price.InterestAccruedPrice;
import net.meerkat.calculate.price.ShiftableInstrumentPrice;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.interest.CertificateOfDeposit;
import net.meerkat.instrument.interest.MoneyMarketSecurity;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.money.fx.ExchangeRates;
import net.meerkat.money.interest.InterestRate;
import net.meerkat.money.interest.interpolation.InterestRateInterpolator;
import net.ollie.goat.temporal.date.interim.CompleteInterval;

/**
 *
 * @author ollie
 */
public class NativeMoneyMarketPricer
        implements MoneyMarketPricer {

    private final ExchangeRatesProvider<LocalDate> exchangeRatesProvider;
    private final InterestRateInterpolator interestRateInterpolator;

    public NativeMoneyMarketPricer(
            final ExchangeRatesProvider<LocalDate> exchangeRatesProvider,
            final InterestRateInterpolator interestRateInterpolator) {
        this.exchangeRatesProvider = exchangeRatesProvider;
        this.interestRateInterpolator = interestRateInterpolator;
    }

    @Override
    public <C extends CurrencyId> MoneyMarketPriceContext<C> priceContext(final LocalDate date, final C currency) {
        return new NativeMoneyMarketPriceContext<>(currency, date);
    }

    private final class NativeMoneyMarketPriceContext<C extends CurrencyId> implements MoneyMarketPriceContext<C> {

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
        public ShiftableInstrumentPrice<C> handle(final CertificateOfDeposit cd) {
            return this.price(cd, cd.notional(), cd.rate(), cd.maturity());
        }

        private <F extends CurrencyId> InterestAccruedPrice<F, C> price(
                final MoneyMarketSecurity security,
                final Money<F> notional,
                final InterestRate interestRate,
                final LocalDate maturity) {
            final ExchangeRate<F, C> fxRate = this.fxRates().rate(notional.currencyId(), currency);
            final CompleteInterval period = new CompleteInterval(date, maturity);
            return new InterestAccruedPrice<>(security, notional, interestRate, fxRate, period, interestRateInterpolator, null);
        }

    }

}
