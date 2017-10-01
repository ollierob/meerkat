package net.meerkat.instrument.bond.coupon;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.cash.CashPayment;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.money.interest.InterestRates;
import net.meerkat.money.interest.fixed.FixedInterestRate;
import net.ollie.goat.temporal.date.count.YearCount;

/**
 *
 * @author Ollie
 */
public class FixedCoupon<C extends CurrencyId>
        extends AbstractBondCoupon
        implements CashPayment<C> {

    private final Money<C> amount;
    private final FixedInterestRate rate;

    public FixedCoupon(final LocalDate paymentDate, final Money<C> amount, final FixedInterestRate rate) {
        super(paymentDate);
        this.amount = amount;
        this.rate = rate;
    }

    @Override
    public boolean hasReferenceRate() {
        return false;
    }

    @Override
    public Money<C> paymentAmount() {
        return amount;
    }

    @Override
    public FixedInterestRate rate() {
        return rate;
    }

    @Override
    @Deprecated
    public FixedInterestRate rate(final InterestRates provider) {
        return rate;
    }

    @Override
    @Deprecated
    public Optional<Money<?>> couponValue() {
        return Optional.of(amount);
    }

    public Money<?> accrue(final LocalDate to) {
        return rate.accrue(amount, this.paymentDate(), to);
    }

    public YearCount accrual() {
        return rate.dateArithmetic();
    }

    @Override
    public <T extends CurrencyId> CashPayment<T> convert(final ExchangeRate<C, T> exchangeRate) {
        return new FixedCoupon<>(this.paymentDate(), amount.convert(exchangeRate), rate);
    }

    @Override
    public Map<String, Object> explain() {
        return this.explanationBuilder(super.explain())
                .put("rate", rate)
                .put("amount", amount);
    }

}
