package net.ollie.meerkat.security.bond;

import net.ollie.meerkat.security.Security;
import net.ollie.meerkat.security.SecurityDefinition;
import net.ollie.meerkat.security.bond.option.BondFutureOption;
import net.ollie.meerkat.security.bond.option.BondOption;
import net.ollie.meerkat.security.bond.swap.BondAssetSwap;
import net.ollie.meerkat.security.derivative.Derivative;

/**
 *
 * @author Ollie
 */
public interface BondDerivative<B extends Security> extends SecurityDefinition, Derivative<B> {

    @Override
    default <R> R handleWith(final SecurityDefinition.Handler<R> handler) {
        return handler instanceof BondDerivative.Handler
                ? this.handleWith((BondDerivative.Handler<R>) handler)
                : handler.handle(this);
    }

    <R> R handleWith(BondDerivative.Handler<R> handler);

    interface Handler<R> extends SecurityDefinition.Handler<R> {

        R handle(BondOption option);

        R handle(BondAssetSwap swap);

        R handle(BondFutureOption futureOption);

    }

}
