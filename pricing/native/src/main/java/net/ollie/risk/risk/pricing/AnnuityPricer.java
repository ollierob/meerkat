package net.ollie.risk.risk.pricing;

import java.time.LocalDate;

import javax.annotation.Nonnull;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.interest.InterestRate;
import net.ollie.meerkat.numeric.money.Money;

/**
 *
 * @author ollie
 */
public abstract class AnnuityPricer {

    protected <C extends CurrencyId> Money<C> presentValue(
            final Money<C> money,
            final LocalDate date,
            final LocalDate maturity) {
        return this.discountRate(date, money.currencyId()).discount(money, date, maturity);
    }

    @Nonnull
    protected abstract InterestRate discountRate(LocalDate date, CurrencyId currency);

}
