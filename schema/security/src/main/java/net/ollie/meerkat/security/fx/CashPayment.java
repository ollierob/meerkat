package net.ollie.meerkat.security.fx;

import java.time.LocalDate;

import javax.annotation.Nonnull;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.money.Money;

/**
 *
 * @author ollie
 */
public interface CashPayment {

    @Nonnull
    LocalDate date();

    @Nonnull
    Money<?> amount();

    @Nonnull
    default CurrencyId currency() {
        return this.amount().currency();
    }

}
