package net.ollie.meerkat.security.moneymarket.repo;

import javax.annotation.Nonnull;

import net.ollie.meerkat.security.Security;
import net.ollie.meerkat.security.SecurityDefinition;
import net.ollie.meerkat.security.derivative.Derivative;
import net.ollie.meerkat.security.fx.CashForCollateral;
import net.ollie.meerkat.security.moneymarket.MoneyMarketSecurity;
import net.ollie.meerkat.security.moneymarket.repo.dates.RepoDates;
import net.ollie.meerkat.security.moneymarket.repo.rate.RepoRate;

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
