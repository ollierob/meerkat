package net.meerkat.instrument.equity.dividend;

import net.meerkat.time.calendar.TemporalSequence;

import javax.annotation.Nonnull;
import java.time.Period;
import java.util.Optional;
import java.util.function.Predicate;

public interface Dividends extends TemporalSequence<Dividend<?>> {

    @Nonnull
    Optional<Period> frequency();

    @Nonnull
    @Override
    Dividends filter(Predicate<? super Dividend<?>> predicate);

}
