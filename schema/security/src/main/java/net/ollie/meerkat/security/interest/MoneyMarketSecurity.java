package net.ollie.meerkat.security.interest;

import net.ollie.meerkat.security.SecurityDefinition;

/**
 *
 * @author ollie
 */
public interface MoneyMarketSecurity extends SecurityDefinition {

    @Override
    default <R> R handleWith(final SecurityDefinition.Handler<R> handler) {
        return handler instanceof MoneyMarketSecurity.Handler
                ? this.handleWith((MoneyMarketSecurity.Handler<R>) handler)
                : handler.handle(this);
    }

    <R> R handleWith(MoneyMarketSecurity.Handler<R> handler);

    interface Handler<R> extends SecurityDefinition.Handler<R> {

        R handle(CertificateOfDeposit cd);

    }

}
