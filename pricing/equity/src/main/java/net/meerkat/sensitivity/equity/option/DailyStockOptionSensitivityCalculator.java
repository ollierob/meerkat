package net.meerkat.sensitivity.equity.option;

import java.time.LocalDate;

import net.meerkat.calculate.sensitivity.greeks.Delta;
import net.meerkat.calculate.sensitivity.greeks.Gamma;
import net.meerkat.calculate.sensitivity.greeks.Rho;
import net.meerkat.calculate.sensitivity.greeks.Theta;
import net.meerkat.calculate.sensitivity.greeks.Vega;
import net.meerkat.identifier.currency.CurrencyIso;
import net.meerkat.identifier.currency.USD;
import net.meerkat.instrument.equity.option.StockOption;
import net.meerkat.pricing.equity.option.OptionPricer;
import net.meerkat.pricing.option.OptionPrice;
import net.meerkat.pricing.option.OptionPriceShifts;
import net.meerkat.sensitivity.equity.EquityDerivativeSensitivityCalculator;

/**
 *
 * @author ollie
 */
public class DailyStockOptionSensitivityCalculator implements EquityDerivativeSensitivityCalculator<LocalDate, StockOption> {

    private static final OptionPriceShifts DELTA_SHIFT = null; //TODO

    private final OptionPricer<LocalDate, StockOption> optionPricer;

    public DailyStockOptionSensitivityCalculator(final OptionPricer<LocalDate, StockOption> pricer) {
        this.optionPricer = pricer;
    }

    @Override
    public EquityOptionInstrumentSensitivities sensitivities(final LocalDate date, final StockOption option) {
        final OptionPrice.Shiftable<USD> price = optionPricer.price(date, option, CurrencyIso.USD);
        return new Sensitivities(date, option, price);
    }

    private final class Sensitivities implements EquityOptionInstrumentSensitivities {

        private final LocalDate date;
        private final StockOption option;
        private final OptionPrice.Shiftable<USD> price;

        Sensitivities(final LocalDate date, final StockOption option, final OptionPrice.Shiftable<USD> price) {
            this.date = date;
            this.option = option;
            this.price = price;
        }

        @Override
        public Delta delta() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Gamma gamma() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Theta theta() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Vega vega() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Rho rho() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

}
