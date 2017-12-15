package net.meerkat.instrument.equity.option;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.derivative.option.exercise.OptionExercise;
import net.meerkat.instrument.equity.EquityDerivative;
import net.meerkat.issuer.IssuerId;
import net.meerkat.money.Money;

/**
 *
 * @author ollie
 * @see
 * <a href="http://deutsche-boerse.com/dbg-en/about-us/services/know-how/glossary/glossary-article/Warrant/2561996">Warrants</a>
 */
public class Warrant extends StockOption {

    public Warrant(
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
