package net.meerkat.instrument;

import net.meerkat.instrument.cash.CashPayment;
import net.meerkat.instrument.moneymarket.CertificateOfDeposit;
import net.meerkat.instrument.moneymarket.CommercialPaper;
import net.meerkat.instrument.moneymarket.TreasuryBill;
import net.meerkat.instrument.repo.Repo;
import net.meerkat.money.interest.fixed.FixedInterestRate;

import javax.annotation.Nonnull;

/**
 * @author ollie
 */
public interface FixedInterestSecurity extends Security, InstrumentDefinition {

    @Nonnull
    CashPayment<?> purchase();

    @Nonnull
    FixedInterestRate impliedRate();

    @Override
    default <R> R handleWith(final InstrumentDefinition.Handler<R> handler) {
        return handler instanceof FixedInterestSecurity.Handler
                ? this.handleWith((FixedInterestSecurity.Handler<R>) handler)
                : handler.handleUnknown(this);
    }

    <R> R handleWith(Handler<R> handler);

    interface Handler<R> extends InstrumentDefinition.Handler<R> {

        R handle(Repo<?> repo);

        R handle(CertificateOfDeposit<?> certificateOfDeposit);

        R handle(CommercialPaper<?> commercialPaper);

        R handle(TreasuryBill treasuryBill);

    }

}
