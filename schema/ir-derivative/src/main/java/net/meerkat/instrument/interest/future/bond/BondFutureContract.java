package net.meerkat.instrument.interest.future.bond;

import java.time.Period;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.instrument.bond.Bond;
import net.meerkat.instrument.bond.BondProvider;
import net.meerkat.instrument.interest.future.InterestRateFutureContract;
import net.ollie.goat.numeric.percentage.Percentage;

/**
 *
 * @author ollie
 */
public interface BondFutureContract<C extends CurrencyId>
        extends InterestRateFutureContract<C> {

    /**
     *
     * @return deliverable bond IDs. This may be just one ID if the deliverable is the theoretical notional bond.
     */
    @Nonnull
    Set<InstrumentId> deliverableBonds();

    default Set<Bond> deliverableBonds(final BondProvider bondProvider) {
        final Map<InstrumentId, Bond> bonds = bondProvider.getAll(this.deliverableBonds());
        return bonds.isEmpty() ? Collections.emptySet() : new HashSet<>(bonds.values());
    }

    @Nonnull
    Period minimumMaturity();

    @Nonnull
    Period maximumMaturity();

    @Nonnull
    Percentage nominalCoupon();

}
