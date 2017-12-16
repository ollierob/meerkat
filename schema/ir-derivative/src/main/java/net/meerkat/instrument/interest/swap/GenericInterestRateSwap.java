package net.meerkat.instrument.interest.swap;

import net.coljate.list.List;
import net.coljate.list.ListIterator;
import net.meerkat.identifier.currency.CurrencyIds;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.IssuedSecurity;
import net.meerkat.instrument.derivative.swap.SwapLegs;
import net.meerkat.instrument.interest.swap.leg.InterestRateSwapLeg;
import net.meerkat.issuer.IssuerId;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.util.OptionalInt;
import java.util.function.Predicate;

/**
 *
 * @author ollie
 */
public class GenericInterestRateSwap
        extends IssuedSecurity
        implements InterestRateSwap {

    private final List<? extends InterestRateSwapLeg<?, ?>> legs;

    public GenericInterestRateSwap(
            final String name,
            final InstrumentIds ids,
            final IssuerId issue,
            final List<? extends InterestRateSwapLeg<?, ?>> legs) {
        super(name, ids, issue);
        this.legs = legs;
    }

    @Override
    public SwapLegs.Finite<? extends InterestRateSwapLeg<?, ?>> legs() {
        return new GenericSwapLegs();
    }

    @Override
    public ExplanationBuilder explain() {
        return super.explain()
                .put("legs", legs);
    }

    private class GenericSwapLegs implements SwapLegs.Finite<InterestRateSwapLeg<?, ?>> {

        @Override
        public OptionalInt numLegs() {
            return OptionalInt.of(legs.count());
        }

        @Nonnull
        @Override
        public Finite<InterestRateSwapLeg<?, ?>> filter(Predicate<? super InterestRateSwapLeg<?, ?>> predicate) {
            throw new UnsupportedOperationException(); //TODO
        }

        @Nonnull
        @Override
        public List<InterestRateSwapLeg<?, ?>> between(LocalDate startInclusive, LocalDate endExclusive) {
            throw new UnsupportedOperationException(); //TODO
        }

        @CheckForNull
        @Override
        public InterestRateSwapLeg<?, ?> latestBefore(LocalDate endExclusive) {
            throw new UnsupportedOperationException(); //TODO
        }

        @CheckForNull
        @Override
        public InterestRateSwapLeg<?, ?> getIfPresent(@Nullable Object key) {
            throw new UnsupportedOperationException(); //TODO
        }

        @Override
        public CurrencyIds currencyIds() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public ListIterator<InterestRateSwapLeg<?, ?>> iterator() {
            return ListIterator.wrap(legs.iterator());
        }

        @Override
        public InterestRateSwapLeg<?, ?> last() {
            return legs.last();
        }

    }

}
