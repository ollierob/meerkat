package net.meerkat.instrument.equity.option;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.derivative.option.exercise.OptionExercise;
import net.meerkat.instrument.equity.EquityDerivative;
import net.meerkat.instrument.equity.Stock;
import net.meerkat.issuer.IssuerId;
import net.meerkat.money.Money;

/**
 * @author ollie
 */
public class StockOption extends AbstractEquityOption<Stock> {

    public StockOption(
            final String name,
            final InstrumentIds identifiers,
            final IssuerId issuerId,
            final OptionExercise exercise,
            final Money<?> premium,
            final Money<?> strike,
            final InstrumentIds underlyingIds) {
        super(name, identifiers, issuerId, exercise, premium, strike, underlyingIds);
    }

    @Override
    public <R> R handleWith(final EquityDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
