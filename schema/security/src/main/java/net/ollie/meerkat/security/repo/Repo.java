package net.ollie.meerkat.security.repo;

import javax.annotation.Nonnull;

import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.derivative.Derivative;
import net.ollie.meerkat.security.Security;
import net.ollie.meerkat.security.SecurityDefinition;
import net.ollie.meerkat.security.fx.CashForCollateral;
import net.ollie.meerkat.security.repo.dates.RepoDates;
import net.ollie.meerkat.security.repo.rate.RepoRate;

/**
 *
 * @author ollie
 */
public interface Repo<C extends Security>
        extends CashForCollateral<C>, SecurityDefinition, Derivative<C> {

    @Nonnull
    RepoRate rate();

    @Override
    Money principal();

    @Override
    C collateral();

    @Nonnull
    RepoDates dates();

    @Override
    default C underlying() {
        return this.collateral();
    }

    @Override
    @Deprecated
    default <R> R handleWith(final SecurityDefinition.Handler<R> handler) {
        return handler instanceof Repo.Handler
                ? this.handleWith((Repo.Handler<R>) handler)
                : handler.handle(this);
    }

    <R> R handleWith(Repo.Handler<R> handler);

    interface Handler<R> extends SecurityDefinition.Handler<R> {

        R handle(BondRepo repo);

        R handle(EquityRepo repo);

    }

}
