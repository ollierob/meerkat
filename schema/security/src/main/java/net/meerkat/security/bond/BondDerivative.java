package net.meerkat.security.bond;

import net.meerkat.security.Security;
import net.meerkat.security.SecurityDefinition;
import net.meerkat.security.bond.option.BondFutureOption;
import net.meerkat.security.bond.option.BondOption;
import net.meerkat.security.bond.swap.BondAssetSwap;
import net.meerkat.security.derivative.Derivative;

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
