package net.meerkat.instrument.interest;

import net.meerkat.instrument.InstrumentDefinition;

/**
 *
 * @author ollie
 */
public interface MoneyMarketSecurity extends InstrumentDefinition {

    @Override
    default <R> R handleWith(final InstrumentDefinition.Handler<R> handler) {
        return handler instanceof MoneyMarketSecurity.Handler
                ? this.handleWith((MoneyMarketSecurity.Handler<R>) handler)
                : handler.handle(this);
    }

    <R> R handleWith(MoneyMarketSecurity.Handler<R> handler);

    interface Handler<R> extends InstrumentDefinition.Handler<R> {

        R handle(CertificateOfDeposit cd);

    }

}
