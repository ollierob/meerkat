package net.meerkat.pricing.fx.swap;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.fx.swap.MaturingFxSwap;
import net.meerkat.pricing.InstrumentPriceException;
import net.meerkat.pricing.fx.FxDerivativePricer;
import net.meerkat.pricing.fx.FxPrice;
import net.meerkat.pricing.shifts.InstrumentPriceShifts;

import java.time.LocalDate;

public class DailyMaturingFxSwapPricer implements FxDerivativePricer<LocalDate, MaturingFxSwap<?, ?>> {

    @Override
    public <C extends CurrencyId> FxPrice.Shiftable<C> price(LocalDate temporal, MaturingFxSwap<?, ?> instrument, C currency, InstrumentPriceShifts shifts) throws InstrumentPriceException {
        throw new UnsupportedOperationException(); //TODO
    }
}
