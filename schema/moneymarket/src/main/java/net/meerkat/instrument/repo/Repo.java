package net.meerkat.instrument.repo;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.instrument.FixedInterestSecurity;
import net.meerkat.instrument.cash.CashPayment;
import net.meerkat.money.Money;

/**
 *
 * @author ollie
 */
public interface Repo<C extends CurrencyId> extends FixedInterestSecurity {

    @Override
    CashPayment<C> purchase();

    @Nonnull
    CashPayment<C> repurchase();

    @Nonnull
    InstrumentId collateralId();

    @Nonnull
    default Money<C> interestPaid() {
        return this.repurchase().paymentAmount().minus(this.purchase().paymentAmount());
    }

    @Override
    default <R> R handleWith(final FixedInterestSecurity.Handler<R> handler) {
        return handler instanceof Handler
                ? this.handleWith((Handler<R>) handler)
                : handler.handle(this);
    }

    <R> R handleWith(Handler<R> handler);

    interface Handler<R> extends FixedInterestSecurity.Handler<R> {

        R handle(BondRepo bondRepo);

        R handle(EquityRepo equityRepo);

    }

}
