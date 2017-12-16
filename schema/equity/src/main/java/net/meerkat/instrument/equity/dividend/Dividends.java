package net.meerkat.instrument.equity.dividend;

import net.meerkat.time.calendar.TemporalSequence;

import javax.annotation.Nonnull;
import java.util.function.Predicate;

public interface Dividends extends TemporalSequence<Dividend<?>> {

    @Nonnull
    @Override
    Dividends filter(Predicate<? super Dividend<?>> predicate);

}
