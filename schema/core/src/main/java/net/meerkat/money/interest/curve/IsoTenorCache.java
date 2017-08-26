package net.meerkat.money.interest.curve;

import java.time.Period;

import net.coljate.list.Array;
import net.coljate.map.ConcurrentMap;
import net.coljate.map.Map;
import net.coljate.map.MutableMap;

/**
 *
 * @author ollie
 */
public class IsoTenorCache implements TenorProvider<NamedTenor> {

    private final MutableMap<String, NamedTenor> tenorsByName = ConcurrentMap.createHashMap();
    private final MutableMap<Period, NamedTenor> tenorsByPeriod = ConcurrentMap.createHashMap();

    public IsoTenorCache(final NamedTenor... tenors) {
        this(Array.viewOf(tenors));
    }

    protected IsoTenorCache(final Iterable<NamedTenor> tenors) {
        tenors.forEach(tenor -> {
            tenorsByName.put(tenor.name(), tenor);
            tenorsByPeriod.put(tenor.period(), tenor);
        });
    }

//    public static IsoTenorCache byName(final Map<String, Period> namedPeriods) {
//        return new IsoTenorCache(namedPeriods.transform(NamedTenor::new));
//    }

    public IsoTenorCache(final Map<String, Period> namedPeriods) {
        namedPeriods.forEach((name, period) -> {
            final NamedTenor tenor = new NamedTenor(name, period);
            tenorsByName.put(name, tenor);
            tenorsByPeriod.put(period, tenor);
        });
    }

    @Override
    public NamedTenor get(final String key) {
        return tenorsByName.computeIfAbsent(key, this::toTenor);
    }

    @Override
    public NamedTenor get(final Period period) {
        return tenorsByPeriod.computeIfAbsent(period.normalized(), this::toTenor);
    }

    private NamedTenor toTenor(final String name) {
        final Period period = Period.parse(name.startsWith("P") || name.startsWith("-P") ? name : "P" + name);
        return this.toTenor(period);
    }

    private NamedTenor toTenor(final Period normalizedPeriod) {
        final String name = normalizedPeriod.toString().replace("P", "");
        return new NamedTenor(name, normalizedPeriod);
    }

}
