package net.ollie.meerkat.calculate.price.interest;

import java.time.LocalDate;
import java.util.function.Function;

import net.ollie.goat.money.Money;
import net.ollie.goat.money.currency.Currency;
import net.ollie.goat.money.fx.ExchangeRate;
import net.ollie.meerkat.calculate.fx.ExchangeRateCalculator;
import net.ollie.meerkat.calculate.price.ShiftableSecurityPrice;
import net.ollie.meerkat.calculate.price.shifts.SecurityShifts;
import net.ollie.meerkat.security.moneymarket.CertificateOfDeposit;

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

        NativeMoneyMarketPriceContext(C currency, LocalDate date) {
            this.currency = currency;
            this.date = date;
        }

        ExchangeRateCalculator fxRates() {
            return getExchangeRates.apply(date);
        }

        @Override
        public ShiftableSecurityPrice<C> handle(final CertificateOfDeposit cd) {
            final Money<?> price = cd.accrueFrom(date);
            return this.wrap(price);
        }

        private <F extends Currency> SimpleSecurityPrice<F, C> wrap(final Money<F> price) {
            final ExchangeRate<F, C> fxRate = this.fxRates().rate(price.currency(), currency);
            return new SimpleSecurityPrice<>(price, fxRate);
        }

    }

    private static final class SimpleSecurityPrice<F extends Currency, C extends Currency>
            implements ShiftableSecurityPrice<C> {

        private final Money<F> price;
        private final ExchangeRate<F, C> fxRate;

        SimpleSecurityPrice(final Money<F> price, final ExchangeRate<F, C> fxRate) {
            this.price = price;
            this.fxRate = fxRate;
        }

        @Override
        public ShiftableSecurityPrice<C> shift(final SecurityShifts shifts) throws InvalidShiftTypeException {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Money<C> clean() {
            return fxRate.convert(price);
        }

        @Override
        public Money<C> dirty() {
            return fxRate.convert(price);
        }

        @Override
        public ExplanationBuilder explain() {
            return ShiftableSecurityPrice.super.explain()
                    .put("fx rate", fxRate);
        }

    }

}
