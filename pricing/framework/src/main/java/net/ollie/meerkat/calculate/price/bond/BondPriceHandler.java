package net.ollie.meerkat.calculate.price.bond;

import java.time.temporal.Temporal;

import javax.annotation.Nonnull;

import net.ollie.meerkat.identifier.currency.CurrencyIso;
import net.ollie.meerkat.security.bond.Bond;

/**
 *
 * @author Ollie
 */
public interface BondPriceHandler<C extends CurrencyIso, T extends Temporal> extends Bond.Handler<BondPrice<C>> {

    @Nonnull
    T temporal();

}
