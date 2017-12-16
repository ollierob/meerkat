package net.meerkat.instrument.equity.dividend;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.HasCurrencyId;
import net.meerkat.time.calendar.TemporalSequence;

import javax.annotation.Nonnull;
import java.util.function.Predicate;

public interface Dividends<C extends CurrencyId> extends HasCurrencyId, TemporalSequence<Dividend<C>> {

    @Nonnull
    @Override
    Dividends<C> filter(Predicate<? super Dividend<C>> predicate);
    
}
