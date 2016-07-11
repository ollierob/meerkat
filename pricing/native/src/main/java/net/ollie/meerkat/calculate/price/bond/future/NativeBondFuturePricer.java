package net.ollie.meerkat.calculate.price.bond.future;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

import javax.annotation.Nonnull;

import net.ollie.goat.money.Money;
import net.ollie.goat.money.currency.Currency;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.suppliers.lazy.Lazy;
import net.ollie.meerkat.calculate.price.bond.BondPrice;
import net.ollie.meerkat.calculate.price.bond.BondPricer;
import net.ollie.meerkat.calculate.price.bond.BondPricer.BondPriceException;
import net.ollie.meerkat.security.bond.Bond;
import net.ollie.meerkat.security.bond.future.BondFuture;
import net.ollie.meerkat.security.moneymarket.repo.rate.RepoRate;

/**
 *
 * @author Ollie
 */
public abstract class NativeBondFuturePricer
        implements BondFuturePricer<LocalDate> {

    private final CheapestToDeliverProvider<LocalDate> getCheapestToDeliver;
    private final BondPricer<LocalDate> bondPricer;

    public NativeBondFuturePricer(
            final CheapestToDeliverProvider<LocalDate> getCheapestToDeliver,
            final BondPricer<LocalDate> bondPricer) {
        this.getCheapestToDeliver = getCheapestToDeliver;
        this.bondPricer = bondPricer;
    }

    @Override
    public <C extends Currency> BondFuturePrice.Shiftable<C> price(
            final LocalDate date,
            final BondFuture bondFuture,
            final C currency) {
        try {
            final CheapestToDeliver.Shiftable<C> cheapestToDeliver = this.cheapestToDeliver(date, bondFuture, currency);
            final RepoRate repoRate = this.repoRate(date, bondFuture, cheapestToDeliver);
            return new NativeBondFuturePrice<>(date, cheapestToDeliver, repoRate, BondFutureShifts.none());
        } catch (final BondPriceException bex) {
            throw new BondFuturePriceException("Failed to determine cheapest to deliver!", bex);
        }
    }

    @Nonnull
    private <C extends Currency> CheapestToDeliver.Shiftable<C> cheapestToDeliver(
            final LocalDate date,
            final BondFuture bondFuture,
            final C currency)
            throws BondFuturePriceException {

        final CheapestToDeliver<?> cheapestToDeliver = getCheapestToDeliver.get(date, bondFuture.securityIds())
                .orElseThrow(() -> new BondFuturePriceException("Could not determine cheapest to deliver!"));

        return new CheapestToDeliver.Shiftable<C>() {

            @Override
            public BondPrice.Shiftable<C> price() {
                return bondPricer.price(bondFuture.dates().earliest(), cheapestToDeliver.bond(), currency);
            }

            @Override
            public BigDecimal conversionFactor() {
                return cheapestToDeliver.conversionFactor();
            }

            @Override
            public Bond bond() {
                return cheapestToDeliver.bond();
            }

        };

    }

    protected abstract <C extends Currency> RepoRate repoRate(
            LocalDate date, BondFuture bondFuture, CheapestToDeliver<C> cheapestToDeliver);

    private static final class NativeBondFuturePrice<C extends Currency>
            implements BondFuturePrice.Shiftable<C> {

        private final LocalDate date;
        private final CheapestToDeliver.Shiftable<C> cheapestToDeliver;
        private final RepoRate repoRate;
        private final BondFutureShifts shifts;

        NativeBondFuturePrice(
                final LocalDate date,
                final CheapestToDeliver.Shiftable<C> cheapestToDeliver,
                final RepoRate repoRate,
                final BondFutureShifts shifts) {
            this.date = date;
            this.cheapestToDeliver = cheapestToDeliver;
            this.repoRate = repoRate;
            this.shifts = shifts;
        }

        @Nonnull
        BondPrice.Shiftable<C> shiftedCtdPrice() {
            return shiftedCtdPrice.get();
        }

        private final Lazy<BondPrice.Shiftable<C>> shiftedCtdPrice = Lazy.loadOnceNonnull(() -> this.computeShiftedCtdPrice());

        @Nonnull
        private BondPrice.Shiftable<C> computeShiftedCtdPrice() {
            return cheapestToDeliver.price().shift(shifts.bondShifts());
        }

        @Nonnull
        BigDecimal shiftedConversionFactor() {
            return shifts.shiftConversionFactor(cheapestToDeliver.conversionFactor());
        }

        @Override
        public RepoRate repoRate() {
            return shifts.shift(repoRate);
        }

        @Override
        public Money<C> price() {
            return this.shiftedCtdPrice().clean()
                    .over(this.shiftedConversionFactor())
                    .times(this.bondPriceMultiplier());
        }

        private Percentage costOfCarry() {
            return this.repoRate().annualRate().minus(this.shiftedCtdPrice().yieldToMaturity());
        }

        private BigDecimal bondPriceMultiplier() {
            throw new UnsupportedOperationException(); //TODO
        }

        @Nonnull
        Period timeToExpiry() {
            throw new UnsupportedOperationException();
        }

        @Override
        public CheapestToDeliver.Shiftable<C> cheapestToDeliver() {
            return new CheapestToDeliver.Shiftable<C>() {

                @Override
                public Bond bond() {
                    return cheapestToDeliver.bond();
                }

                @Override
                public BigDecimal conversionFactor() {
                    return shiftedConversionFactor();
                }

                @Override
                public BondPrice.Shiftable<C> price() {
                    return shiftedCtdPrice();
                }

            };
        }

        @Override
        public BondFuturePrice.Shiftable<C> shift(final BondFutureShifts shifts) {
            return new NativeBondFuturePrice<>(date, cheapestToDeliver, repoRate, shifts);
        }

        @Override
        public ExplanationBuilder explain() {
            return BondFuturePrice.Shiftable.super.explain()
                    .put("repo rate", repoRate)
                    .put("cost of carry", this.costOfCarry())
                    .put("shifts", shifts)
                    .put("shifted conversion factor", this.shiftedConversionFactor())
                    .put("shifted cheapest to deliver price", this.shiftedCtdPrice())
                    .put("shifted repo rate", this.repoRate());
        }

    }

}
