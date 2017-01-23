package net.meerkat.instrument.interest.repo;

import javax.annotation.Nonnull;

import net.meerkat.instrument.derivative.Derivative;
import net.meerkat.instrument.cash.CashForCollateral;
import net.meerkat.instrument.interest.MoneyMarketSecurity;
import net.meerkat.instrument.interest.repo.dates.RepoDates;
import net.meerkat.instrument.interest.repo.rate.RepoRate;
import net.meerkat.instrument.Instrument;
import net.meerkat.instrument.InstrumentDefinition;

/**
 *
 * @author ollie
 */
public interface Repo<C extends Instrument>
        extends MoneyMarketSecurity, CashForCollateral<C>, Derivative<C> {

    @Nonnull
    RepoRate rate();

    @Override
    C collateral();

    @Nonnull
    RepoDates dates();

    @Override
    default C underlying() {
        return this.collateral();
    }

    @Override
    default <R> R handleWith(final InstrumentDefinition.Handler<R> handler) {
        return handler instanceof Repo.Handler
                ? this.handleWith((Repo.Handler<R>) handler)
                : MoneyMarketSecurity.super.handleWith(handler);
    }

    @Override
    @Deprecated
    default <R> R handleWith(final MoneyMarketSecurity.Handler<R> handler) {
        return handler instanceof Repo.Handler
                ? this.handleWith((Repo.Handler<R>) handler)
                : handler.handle(this);
    }

    <R> R handleWith(Repo.Handler<R> handler);

    interface Handler<R> extends InstrumentDefinition.Handler<R> {

        R handle(BondRepo repo);

    }

}
