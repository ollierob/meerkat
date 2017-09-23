package net.meerkat.time.calendar.settlement;

import java.time.LocalDate;

import net.coljate.cache.Cache;
import net.coljate.cache.impl.EmptyCache;
import net.coljate.map.ImmutableEntry;

/**
 *
 * @author ollie
 */
public interface SettlementDateCache extends Cache<LocalDate, SettlementDate> {

    SettlementDateCache NOT_CACHED = new Empty();

    class Empty extends EmptyCache<LocalDate, SettlementDate> implements SettlementDateCache {

        private static final long serialVersionUID = 1L;

        protected Empty() {
        }

        @Override
        public SettlementDate get(final LocalDate key) {
            return new SettlementDate(key);
        }

        @Override
        public ImmutableEntry<LocalDate, SettlementDate> getEntry(final Object key) {
            return key instanceof LocalDate
                    ? ImmutableEntry.of((LocalDate) key, this.get((LocalDate) key))
                    : null;
        }

    }

}
