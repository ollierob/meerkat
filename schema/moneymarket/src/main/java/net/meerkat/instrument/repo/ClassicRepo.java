package net.meerkat.instrument.repo;

import java.time.LocalDate;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.cash.CashPayment;
import net.meerkat.instrument.cash.DefaultCashPayment;
import net.meerkat.money.Money;
import net.meerkat.money.interest.fixed.FixedInterestRate;

/**
 *
 * @author ollie
 */
public interface ClassicRepo<C extends CurrencyId> extends Repo<C> {

    @Nonnull
    FixedInterestRate impliedRate();

    @Nonnull
    LocalDate repurchaseDate();

    @Nonnull
    default Money<C> repurchaseAmount() {
        final CashPayment<C> purchasePrice = this.purchase();
        return this.impliedRate().accrue(purchasePrice.paymentAmount(), purchasePrice.paymentDate(), this.repurchaseDate());
    }

    @Override
    default CashPayment<C> repurchase() {
        return new DefaultCashPayment<>(this.repurchaseDate(), this.repurchaseAmount());
    }

}
