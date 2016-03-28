package net.ollie.meerkat.calculate.price.bond;

import java.time.LocalDate;

import javax.annotation.Nonnull;

import net.ollie.meerkat.calculate.price.SecurityPricer;
import net.ollie.meerkat.calculate.price.shifts.ExchangeRateShifts.ExchangeRateShifter;
import net.ollie.meerkat.calculate.price.shifts.InterestRateShifts.InterestRateShifter;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.security.bond.Bond;

/**
 *
 * @author ollie
 */
public interface BondTypePricer<B extends Bond>
        extends SecurityPricer<LocalDate, B>, ExchangeRateShifter, InterestRateShifter {

    @Override
    default <C extends CurrencyId> BondPrice<C> price(final LocalDate date, final B bond, final C currency) {
        return this.price(date, bond, BondShifts.none(), currency);
    }

    @Nonnull
    <C extends CurrencyId> BondPrice<C> price(LocalDate date, B bond, BondShifts shifts, C currency);

}
