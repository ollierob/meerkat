package net.ollie.meerkat.security.bond.coupon;

import java.time.LocalDate;

import javax.annotation.Nonnull;

import net.ollie.goat.money.currency.Currency;
import net.ollie.goat.money.Money;
import net.ollie.goat.money.interest.daycount.YearCount;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.money.interest.fixed.FixedInterestRate;
import net.ollie.meerkat.security.fx.CashPayment;

/**
 *
 * @author Ollie
 */
public class FixedRateCoupon<C extends Currency>
        extends AbstractBondCoupon
        implements CashPayment<C> {

    private final Money<C> amount;
    private final FixedInterestRate rate;

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
        return rate.accrual();
    }

}
