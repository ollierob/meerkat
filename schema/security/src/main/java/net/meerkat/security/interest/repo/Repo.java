package net.meerkat.security.interest.repo;

import javax.annotation.Nonnull;

import net.meerkat.security.Security;
import net.meerkat.security.SecurityDefinition;
import net.meerkat.security.derivative.Derivative;
import net.meerkat.security.fx.CashForCollateral;
import net.meerkat.security.interest.MoneyMarketSecurity;
import net.meerkat.security.interest.repo.dates.RepoDates;
import net.meerkat.security.interest.repo.rate.RepoRate;

/**
 *
 * @author ollie
 */
public interface Repo<C extends Security>
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
    default <R> R handleWith(final SecurityDefinition.Handler<R> handler) {
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

    interface Handler<R> extends SecurityDefinition.Handler<R> {

        R handle(BondRepo repo);

    }

}
