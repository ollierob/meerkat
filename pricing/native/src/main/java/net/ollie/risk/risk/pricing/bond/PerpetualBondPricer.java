package net.ollie.risk.risk.pricing.bond;

import java.time.LocalDate;

import net.ollie.meerkat.calculate.price.bond.BondPrice;
import net.ollie.meerkat.calculate.price.bond.BondPricer;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.security.bond.PerpetualBond;
import net.ollie.meerkat.calculate.price.bond.BondShifts;

/**
 *
 * @author Ollie
 */
public class PerpetualBondPricer implements BondPricer<LocalDate, PerpetualBond> {

    @Override
    public <C extends CurrencyId> BondPrice<C> price(final LocalDate date, final PerpetualBond bond, final BondShifts shifts, final C currency) {
        throw new UnsupportedOperationException(); //TODO
    }

}
