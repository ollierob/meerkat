package net.ollie.meerkat.calculate.price.interest;

import java.time.LocalDate;
import java.util.function.Function;

import net.ollie.goat.money.Money;
import net.ollie.goat.money.currency.Currency;
import net.ollie.goat.money.fx.ExchangeRate;
import net.ollie.goat.money.interest.InterestRate;
import net.ollie.goat.temporal.date.interim.CompleteInterval;
import net.ollie.meerkat.calculate.fx.ExchangeRateCalculator;
import net.ollie.meerkat.calculate.price.ShiftableSecurityPrice;
import net.ollie.meerkat.calculate.price.shifts.ExchangeRateShifts;
import net.ollie.meerkat.calculate.price.shifts.InterestRateShifts;
import net.ollie.meerkat.calculate.price.shifts.SecurityShifts;
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

        NativeMoneyMarketPriceContext(C currency, LocalDate date) {
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

        private <F extends Currency> SimpleSecurityPrice<F, C> price(
                final MoneyMarketSecurity security,
                final Money<F> notional,
                final InterestRate interestRate,
                final LocalDate maturity) {
            final ExchangeRate<F, C> fxRate = this.fxRates().rate(notional.currency(), currency);
            final CompleteInterval period = new CompleteInterval(date, maturity);
            return new SimpleSecurityPrice<>(security, notional, interestRate, fxRate, period, null);
        }

    }

    private static final class SimpleSecurityPrice<F extends Currency, C extends Currency>
            implements ShiftableSecurityPrice<C> {

        private final MoneyMarketSecurity security;
        private final Money<F> notional;
        private final InterestRate interestRate;
        private final ExchangeRate<F, C> fxRate;
        private final CompleteInterval period;
        private final SecurityShifts shifts;

        SimpleSecurityPrice(
                final MoneyMarketSecurity security,
                final Money<F> notional,
                final InterestRate interestRate,
                final ExchangeRate<F, C> fxRate,
                final CompleteInterval period,
                final SecurityShifts shifts) {
            this.security = security;
            this.notional = notional;
            this.interestRate = interestRate;
            this.fxRate = fxRate;
            this.period = period;
            this.shifts = shifts;
        }

        @Override
        public ShiftableSecurityPrice<C> shift(final SecurityShifts shifts) throws InvalidShiftTypeException {
            return new SimpleSecurityPrice<>(security, notional, interestRate, fxRate, period, shifts);
        }

        InterestRate interestRate() {
            return shifts instanceof InterestRateShifts
                    ? ((InterestRateShifts) shifts).shift(interestRate)
                    : interestRate;
        }

        ExchangeRate<F, C> fxRate() {
            return shifts instanceof ExchangeRateShifts
                    ? ((ExchangeRateShifts) shifts).shift(fxRate)
                    : fxRate;
        }

        @Override
        public Money<C> clean() {
            return this.fxRate().convert(notional);
        }

        @Override
        public Money<C> dirty() {
            final Money<F> accrued = this.interestRate().accrue(notional, period);
            return this.fxRate().convert(accrued);
        }

        @Override
        public ExplanationBuilder explain() {
            return ShiftableSecurityPrice.super.explain()
                    .put("security", security)
                    .put("shifts", shifts)
                    .put("base interest rate", interestRate)
                    .put("base fx rate", fxRate)
                    .putIf(shifts != null, "shifted interest rate", this.interestRate())
                    .putIf(shifts != null, "shifted fx rate", this.fxRate());
        }

    }

}
