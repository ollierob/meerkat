package net.meerkat.instrument.repo;

import javax.annotation.Nonnull;

import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.instrument.FixedInterestSecurity;
import net.meerkat.instrument.cash.CashPayment;

/**
 *
 * @author ollie
 */
public interface Repo extends FixedInterestSecurity {

    @Nonnull
    CashPayment<?> repurchasePrice();

    @Nonnull
    InstrumentId collateralId();

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
