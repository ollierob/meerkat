package net.meerkat.security.fx;

import net.meerkat.security.SecurityDefinition;
import net.meerkat.security.fx.option.FxOption;
import net.meerkat.security.fx.swap.FxSwap;

/**
 *
 * @author Ollie
 */
public interface FxDerivative extends SecurityDefinition {

    @Override
    default <R> R handleWith(final SecurityDefinition.Handler<R> handler) {
        return handler instanceof FxDerivative.Handler
                ? this.handleWith((FxDerivative.Handler<R>) handler)
                : handler.handle(this);
    }

    <R> R handleWith(FxDerivative.Handler<R> handler);

    interface Handler<R> extends SecurityDefinition.Handler<R> {

        R handle(FxSwap swap);

        R handle(FxOption option);

    }

}