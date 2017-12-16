package net.meerkat.instrument.equity.dividend;

import net.meerkat.temporal.date.years.Years;
import net.meerkat.time.calendar.TemporalSequence;

import javax.annotation.Nonnull;
import java.util.function.Predicate;

public interface Dividends extends TemporalSequence<Dividend<?>> {

    @Nonnull
    Years frequency();

    @Nonnull
    @Override
    Dividends filter(Predicate<? super Dividend<?>> predicate);

}
