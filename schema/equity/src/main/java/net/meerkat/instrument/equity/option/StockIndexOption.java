package net.meerkat.instrument.equity.option;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.derivative.option.AbstractOption;
import net.meerkat.instrument.derivative.option.exercise.OptionExercise;
import net.meerkat.instrument.equity.EquityDerivative;
import net.meerkat.instrument.equity.index.StockIndex;
import net.meerkat.issuer.IssuerId;
import net.meerkat.money.Money;

/**
 *
 * @author Ollie
 */
public class StockIndexOption
        extends AbstractOption<StockIndex>
        implements EquityDerivative<StockIndex> {

    private final StockIndex underlying;

    public StockIndexOption(
            final String name,
            final InstrumentIds identifiers,
            final IssuerId issuerId,
            final OptionExercise exercise,
            final Money<?> premium,
            final Money<?> strike,
            final Number contractMultiplier,
            final StockIndex underlying) {
        super(name, identifiers, issuerId, exercise, premium, strike, contractMultiplier);
        this.underlying = underlying;
    }

    @Override
    public StockIndex underlying() {
        return underlying;
    }

    @Override
    public <R> R handleWith(final EquityDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
