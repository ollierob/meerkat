package net.meerkat.instrument.bond;

import net.meerkat.instrument.bond.option.BondFutureOption;
import net.meerkat.instrument.bond.option.BondOption;
import net.meerkat.instrument.bond.swap.BondAssetSwap;
import net.meerkat.instrument.derivative.Derivative;
import net.meerkat.instrument.Instrument;
import net.meerkat.instrument.InstrumentDefinition;

/**
 *
 * @author Ollie
 */
public interface BondDerivative<B extends Instrument> extends InstrumentDefinition, Derivative<B> {

    @Override
    default <R> R handleWith(final InstrumentDefinition.Handler<R> handler) {
        return handler instanceof BondDerivative.Handler
                ? this.handleWith((BondDerivative.Handler<R>) handler)
                : handler.handle(this);
    }

    <R> R handleWith(BondDerivative.Handler<R> handler);

    interface Handler<R> extends InstrumentDefinition.Handler<R> {

        R handle(BondOption option);

        R handle(BondAssetSwap swap);

        R handle(BondFutureOption futureOption);

    }

}
