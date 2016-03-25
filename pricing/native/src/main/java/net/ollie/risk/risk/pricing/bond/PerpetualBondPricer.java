package net.ollie.risk.risk.pricing.bond;

import java.time.LocalDate;

import net.ollie.meerkat.calculate.price.bond.BondPrice;
import net.ollie.meerkat.calculate.price.bond.BondPricer;
import net.ollie.meerkat.security.bond.PerpetualBond;
import net.ollie.meerkat.calculate.price.bond.BondShifts;

/**
 *
 * @author Ollie
 */
public class PerpetualBondPricer implements BondPricer<LocalDate, PerpetualBond> {

    @Override
    public BondPrice price(final LocalDate date, final PerpetualBond bond, final BondShifts shifts) {
        throw new UnsupportedOperationException(); //TODO
    }

}
