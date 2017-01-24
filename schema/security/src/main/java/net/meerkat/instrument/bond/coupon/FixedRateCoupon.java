package net.meerkat.instrument.bond.coupon;

import java.time.LocalDate;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.instrument.cash.CashPayment;
import net.meerkat.money.Money;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.money.interest.fixed.FixedInterestRate;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.temporal.date.count.YearCount;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class FixedRateCoupon<C extends CurrencyId>
        extends AbstractBondCoupon
        implements CashPayment<C> {

    @XmlElementRef(name = "amount")
    private Money<C> amount;

    @XmlElementRef(name = "rate")
    private FixedInterestRate rate;

    @Deprecated
    FixedRateCoupon() {
    }

    public FixedRateCoupon(final LocalDate paymentDate, final Money<C> amount, final FixedInterestRate rate) {
        super(paymentDate);
        this.amount = amount;
        this.rate = rate;
    }

    @Override
    public boolean hasReferenceRate() {
        return false;
    }

    @Override
    public Money<C> amount() {
        return amount;
    }

    @Nonnull
    public FixedInterestRate interestRate() {
        return rate;
    }

    @Override
    public Percentage spread() {
        return rate.annualRate();
    }

    @Override
    public LocalDate date() {
        return this.paymentDate();
    }

    public Money<?> accrue(final LocalDate to) {
        return rate.accrue(amount, this.paymentDate(), to);
    }

    public YearCount accrual() {
        return rate.dateArithmetic();
    }

    @Override
    public <T extends CurrencyId> CashPayment<T> convert(final ExchangeRate<C, T> exchangeRate) {
        return new FixedRateCoupon<>(this.paymentDate(), amount.convert(exchangeRate), rate);
    }

}
