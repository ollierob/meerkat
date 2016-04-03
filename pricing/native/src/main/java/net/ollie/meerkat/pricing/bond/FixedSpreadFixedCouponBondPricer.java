//package net.ollie.meerkat.pricing.bond;
//
//import java.time.LocalDate;
//import java.util.function.BiFunction;
//import java.util.function.Function;
//
//import net.ollie.meerkat.calculate.fx.ExchangeRateCalculator;
//import net.ollie.meerkat.calculate.price.bond.BondPrice;
//import net.ollie.meerkat.calculate.price.bond.BondTypePricer;
//import net.ollie.meerkat.identifier.currency.CurrencyId;
//import net.ollie.meerkat.numeric.Percentage;
//import net.ollie.meerkat.numeric.interest.FixedInterestRate;
//import net.ollie.meerkat.numeric.interest.InterestRate;
//import net.ollie.meerkat.numeric.money.Money;
//import net.ollie.meerkat.security.bond.FixedCouponBond;
//
///**
// * Computes a credit spread over an underlying accrual curve so as to match the market value.
// *
// * @author Ollie
// */
//public class FixedSpreadFixedCouponBondPricer implements BondTypePricer<LocalDate, FixedCouponBond> {
//
//    private final BiFunction<? super LocalDate, ? super FixedCouponBond, ? extends Money<?>> getCleanMarketPrice;
//    private final BiFunction<? super LocalDate, ? super CurrencyId, ? extends FixedInterestRate> getInterestRate;
//    private final Function<? super LocalDate, ? extends ExchangeRateCalculator> getExchangeRates;
//
//    public FixedSpreadFixedCouponBondPricer(
//            BiFunction<? super LocalDate, ? super FixedCouponBond, ? extends Money<?>> getCleanMarketPrice,
//            BiFunction<? super LocalDate, ? super CurrencyId, ? extends FixedInterestRate> getInterestRate,
//            Function<? super LocalDate, ? extends ExchangeRateCalculator> getExchangeRates) {
//        this.getCleanMarketPrice = getCleanMarketPrice;
//        this.getInterestRate = getInterestRate;
//        this.getExchangeRates = getExchangeRates;
//    }
//
//    @Override
//    public <C extends CurrencyId> BondPrice<C> price(
//            final LocalDate date,
//            final FixedCouponBond bond,
//            final C currency) {
//
//        final ExchangeRateCalculator exchangeRates = getExchangeRates.apply(date);
//        final Money<C> marketCleanPrice = this.shiftFx(getCleanMarketPrice.apply(date, bond), shifts, currency, exchangeRates);
//        final InterestRate interestRate = this.shift(getInterestRate.apply(date, currency), shifts);
//        final Percentage creditSpread = this.calculateCreditSpread(marketCleanPrice, getInterestRate.apply(date, currency), bond);
//
//        throw new UnsupportedOperationException(); //TODO
//
//    }
//
//    private <C extends CurrencyId> Percentage calculateCreditSpread(final Money<C> cleanPrice, final InterestRate baseRates, final FixedCouponBond bond) {
//        throw new UnsupportedOperationException(); //TODO
//    }
//
//}
