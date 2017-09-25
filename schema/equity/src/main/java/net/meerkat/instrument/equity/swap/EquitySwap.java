package net.meerkat.instrument.equity.swap;

import javax.annotation.Nonnull;

import net.coljate.list.List;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.Instrument;
import net.meerkat.instrument.derivative.swap.Swap;
import net.meerkat.instrument.derivative.swap.SwapLeg;
import net.meerkat.instrument.equity.EquityDerivative;
import net.meerkat.money.Money;

/**
 *
 * @author ollie
 * @see <a href="https://ikenna.net/finance/introduction-to-equity-swaps/">Equity swaps</a>
 */
public interface EquitySwap<E extends Instrument>
        extends EquityDerivative<E>, Swap {

    @Override
    E underlying();

    @Override
    List<? extends EquitySwapLeg<?>> legs();

    interface EquitySwapLeg<C extends CurrencyId> extends SwapLeg<C, C> {

        int underlyingQuantity();

        @Nonnull
        Money<C> underlyingPrice();

        @Nonnull
        default Money<C> notional() {
            return this.underlyingPrice().times(this.underlyingQuantity());
        }

        /**
         * @return true if performance of the equity is included.
         */
        boolean isTotalReturn();

    }

}
