package net.ollie.meerkat.security.equity;

import net.ollie.meerkat.security.Security;
import net.ollie.meerkat.security.SecurityDefinition;
import net.ollie.meerkat.security.derivative.Derivative;
import net.ollie.meerkat.security.equity.future.StockIndexFuture;
import net.ollie.meerkat.security.equity.option.StockIndexOption;
import net.ollie.meerkat.security.equity.option.Warrant;

/**
 *
 * @author Ollie
 */
public interface EquityDerivative<E extends Security> extends Derivative<E>, SecurityDefinition {

    @Override
    default <R> R handleWith(final SecurityDefinition.Handler<R> handler) {
        return handler instanceof EquityDerivative.Handler
                ? this.handleWith((EquityDerivative.Handler<R>) handler)
                : handler.handle(this);
    }

    <R> R handleWith(EquityDerivative.Handler<R> handler);

    interface Handler<R> extends SecurityDefinition.Handler<R> {

        R handle(Warrant warrant);

        R handle(StockIndexOption option);

        R handle(StockIndexFuture future);

    }

}
