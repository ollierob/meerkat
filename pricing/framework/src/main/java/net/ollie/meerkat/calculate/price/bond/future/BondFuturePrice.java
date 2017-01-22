package net.ollie.meerkat.calculate.price.bond.future;

import java.util.Optional;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.meerkat.money.Money;
import net.meerkat.money.currency.Currency;
import net.ollie.meerkat.calculate.price.SecurityPrice;
import net.ollie.meerkat.calculate.price.ShiftableSecurityPrice;
import net.ollie.meerkat.calculate.price.bond.BondPrice;
import net.ollie.meerkat.calculate.price.shifts.SecurityShifts;
import net.meerkat.security.interest.repo.rate.RepoRate;

/**
 *
 * @author ollie
 */
public interface BondFuturePrice<C extends Currency>
        extends SecurityPrice<C> {

    @Nonnull
    Money<C> price();

    @Override
    default Money<C> clean() {
        return this.price();
    }

    @Override
    @Deprecated
    default Money<C> dirty() {
        return this.price();
    }

    @Nonnull
    RepoRate repoRate();

    /**
     *
     * @return shifted cheapest to deliver.
     */
    @Nonnull
    CheapestToDeliver<C> cheapestToDeliver();
    
    @Override
    default EvaluatedBondFuturePrice<C> evaluate() {
        return new EvaluatedBondFuturePrice<>(this.price(), this.repoRate(), this.cheapestToDeliver());
    }

    @Override
    default ExplanationBuilder explain() {
        return SecurityPrice.super.explain()
                .put("cheapest to deliver", this.cheapestToDeliver())
                .put("repo rate", this.repoRate());
    }

    interface Shiftable<C extends Currency>
            extends BondFuturePrice<C>, ShiftableSecurityPrice<C> {

        @Override
        CheapestToDeliver<C> cheapestToDeliver();
        
        BondPrice.Shiftable<C> cheapestToDeliverPrice();

        @Override
        @Deprecated
        default Optional<BondFuturePrice.Shiftable<C>> shift(final SecurityShifts shifts) {
            return shifts.cast(BondFutureShifts.class).map(this::shift);
        }

        @CheckReturnValue
        BondFuturePrice.Shiftable<C> shift(BondFutureShifts shifts);

    }

}
