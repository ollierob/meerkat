package net.meerkat.instrument.moneymarket;

import javax.annotation.Nonnull;

import net.meerkat.instrument.FixedInterestSecurity;
import net.meerkat.money.interest.fixed.FixedInterestRate;

/**
 *
 * @author ollie
 */
public interface CertificateOfDeposit extends FixedInterestSecurity {

    @Nonnull
    FixedInterestRate impliedRate();

}
