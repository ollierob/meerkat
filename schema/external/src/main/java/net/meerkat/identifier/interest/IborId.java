package net.meerkat.identifier.interest;

import java.time.LocalDate;
import java.util.Objects;

import javax.annotation.CheckReturnValue;

import net.meerkat.identifier.currency.CurrencyIso;
import net.meerkat.identifier.currency.HasCurrencyId;
import net.meerkat.money.interest.InterestRate;
import net.meerkat.money.interest.InterestRateId;
import net.meerkat.money.interest.InterestRatePlusSpreadId;
import net.meerkat.money.interest.InterestRates;
import net.meerkat.money.interest.NamedInterestRateId;
import net.meerkat.money.interest.UnknownInterestRateException;
import net.meerkat.money.interest.curve.DateYieldCurve;
import net.meerkat.money.interest.floating.ContinousFloatingInterestRate;
import net.meerkat.money.interest.floating.FloatingInterestRate;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.temporal.date.count.DateArithmetic;

/**
 *
 * @author ollie
 */
public class IborId extends NamedInterestRateId implements InterestRatePlusSpreadId, HasCurrencyId {

    public static final IborId GBP_LIBOR = new IborId("GBP_LIBOR", CurrencyIso.GBP, DateArithmetic.ACT_365);
    public static final IborId USD_LIBOR = new IborId("USD_LIBOR", CurrencyIso.USD, DateArithmetic.ACT_360);
    public static final IborId EUR_LIBOR = new IborId("EUR_LIBOR", CurrencyIso.EUR, DateArithmetic.ACT_360);
    public static final IborId JPY_LIBOR = new IborId("JPY_LIBOR", CurrencyIso.JPY, DateArithmetic.ACT_360);

    public static final IborId EURIBOR = new IborId("EURIBOR", CurrencyIso.EUR, DateArithmetic.ACT_360); //EONIA = overnight
    public static final IborId SONIA = new IborId("SONIA", CurrencyIso.GBP, DateArithmetic.ACT_365);
    public static final IborId TIBOR = new IborId("TIBOR", CurrencyIso.JPY, DateArithmetic.ACT_365);
    public static final IborId BBSW = new IborId("BBSW", CurrencyIso.AUD, DateArithmetic.ACT_365);
    public static final IborId HIBOR = new IborId("HIBOR", CurrencyIso.HKD, DateArithmetic.ACT_365);
    public static final IborId SIBOR = new IborId("SIBOR", CurrencyIso.SGD, DateArithmetic.ACT_365);

    public static final IborId FEDERAL_FUNDS = new IborId("FED", CurrencyIso.USD, DateArithmetic.ACT_360);

    private final CurrencyIso currencyId;
    private final DateArithmetic dateArithmetic;
    private final Percentage spread;
    private final IborId base;

    public IborId(final String name, final CurrencyIso currencyId, final DateArithmetic dateArithmetic) {
        this(name, currencyId, dateArithmetic, Percentage.zero(), null);
    }

    protected IborId(final String name, final CurrencyIso currencyId, final DateArithmetic dateArithmetic, final Percentage spread, final IborId base) {
        super(name);
        this.currencyId = Objects.requireNonNull(currencyId);
        this.dateArithmetic = Objects.requireNonNull(dateArithmetic);
        this.spread = Objects.requireNonNull(spread);
        this.base = base == null || spread.isZero() ? this : base;
    }

    @Override
    public CurrencyIso currencyId() {
        return currencyId;
    }

    @Override
    public Percentage spread() {
        return spread;
    }

    @Override
    public InterestRateId base() {
        return base;
    }

    @Override
    public InterestRate resolve(final InterestRates provider) throws UnknownInterestRateException {
        return provider.require(base).plus(spread);
    }

    @CheckReturnValue
    public IborId plus(final Percentage spread) {
        if (spread.isZero()) {
            return this;
        }
        final Percentage totalSpread = this.spread.plus(spread);
        return new IborId(this.name() + "+" + totalSpread, currencyId, dateArithmetic, totalSpread, base);
    }

    public FloatingInterestRate toRate(final LocalDate referenceDate, final DateYieldCurve yieldCurve) {
        return new ContinousFloatingInterestRate(referenceDate, yieldCurve, dateArithmetic);
    }

}
