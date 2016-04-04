package net.ollie.meerkat.price.bond;

//package net.ollie.meerkat.pricing.bond;
//
//import java.time.LocalDate;
//import static java.util.Objects.requireNonNull;
//import java.util.function.BiFunction;
//import java.util.function.Function;
//
//import net.ollie.meerkat.calculate.fx.ExchangeRateCalculator;
//import net.ollie.meerkat.calculate.price.bond.BondPrice;
//import net.ollie.meerkat.calculate.price.bond.BondShifts;
//import net.ollie.meerkat.calculate.price.bond.BondTypePricer;
//import net.ollie.meerkat.identifier.currency.CurrencyId;
//import net.ollie.meerkat.numeric.interest.InterestRate;
//import net.ollie.meerkat.numeric.money.Money;
//import net.ollie.meerkat.security.bond.FixedCouponBond;
//import net.ollie.meerkat.security.fx.CashPayment;
//import net.ollie.meerkat.time.interim.Interval;
//import net.ollie.meerkat.utils.collections.Sequence;
//
///**
// * Prices fixed coupon bonds purely based on their coupon rate.
// *
// * @author ollie
// */
//public class NaiveFixedCouponBondPricer implements BondTypePricer<LocalDate, FixedCouponBond> {
//
//    private final Function<? super LocalDate, ? extends ExchangeRateCalculator> getFxRates;
//    private final BiFunction<? super LocalDate, ? super CurrencyId, ? extends InterestRate> getDiscountRate;
//
//    public NaiveFixedCouponBondPricer(
//            final Function<LocalDate, ExchangeRateCalculator> exchangeRates,
//            final BiFunction<LocalDate, CurrencyId, InterestRate> discountRates) {
//        this.getFxRates = exchangeRates;
//        this.getDiscountRate = discountRates;
//    }
//
//    @Override
//    public <C extends CurrencyId> BondPrice<C> price(
//            final LocalDate date,
//            final FixedCouponBond bond,
//            final C currency) {
//
//        final ExchangeRateCalculator fxRates = requireNonNull(getFxRates.apply(date));
//        final InterestRate discountRate = requireNonNull(getDiscountRate.apply(date, currency));
//
//        throw new UnsupportedOperationException();
//
////        final Money<C> par = fxRates.convert(bond.par(), currency);
////
////        //Prices
////        final LocalDate maturity = bond.dates().matures();
////        final FixedCouponBondCoupons coupons = bond.coupons();
////        final NavigableMap<LocalDate, Money<C>> clean = this.cleanValue(date, maturity, par, coupons, discountRate, fxRates, shifts);
////        final Money<C> accrued = this.accruedValue(date, coupons, currency, fxRates, shifts);
////
////        return new CleanFlowsAndAccruedBondPrice<>(par, clean, accrued);
//    }
//
////    private <C extends CurrencyId> NavigableMap<LocalDate, Money<C>> cleanValue(
////            final LocalDate date,
////            final LocalDate maturity,
////            final Money<C> par,
////            final FixedCouponBondCoupons coupons,
////            final InterestRate discountRate,
////            final ExchangeRateCalculator fxRates,
////            final BondShifts shifts) {
////
////        final C currency = par.currencyId();
////
////        final NavigableMap<LocalDate, Money<C>> cleanCashFlow = new TreeMap<>();
////
////        cleanCashFlow.put(maturity, discountRate.discount(par, date, maturity));
////
////        for (final FixedCoupon coupon : coupons.onOrAfter(date)) {
////            final LocalDate couponDate = coupon.date();
////            final Money<C> amount = this.shiftPrice(coupon.amount(), shifts, currency, fxRates);
////            final Money<C> discounted = discountRate.discount(amount, date, couponDate);
////            cleanCashFlow.compute(couponDate, (d, current) -> current == null ? discounted : current.plus(discounted));
////        }
////
////        return cleanCashFlow;
////
////    }
////
////    private <C extends Object & CurrencyId> Money<C> accruedValue(
////            final LocalDate date,
////            final FixedCouponBondCoupons coupons,
////            final C currency,
////            final ExchangeRateCalculator fxRates) {
////
////        final FixedCoupon prior = coupons.prior(date);
////        if (prior == null) {
////            return Money.zero(currency);
////        }
////
////        final Money<C> amount = this.shiftPrice(prior.amount(), shifts, currency, fxRates);
////        final FixedInterestRate rate = this.shift(prior.interestRate(), shifts);
////        return rate.accrue(amount, prior.date(), date);
////
////    }
////
////    private <C extends CurrencyId> Money<C> shiftPrice(
////            final Money<?> amount,
////            final BondShifts shifts,
////            final C currency,
////            final ExchangeRateCalculator fxRates) {
////        return shifts.shiftPrice(this.shiftFx(amount, shifts, currency, fxRates));
////    }
////
////    private static final class CleanFlowsAndAccruedBondPrice<C extends CurrencyId>
////            implements BondPrice<C> {
////
////        private final Money<C> par;
////        private final NavigableMap<LocalDate, Money<C>> cleanFlows;
////        private final Money<C> accrued;
////
////        CleanFlowsAndAccruedBondPrice(final Money<C> par, NavigableMap<LocalDate, Money<C>> cleanFlows, Money<C> accrued) {
////            this.par = par;
////            this.cleanFlows = cleanFlows;
////            this.accrued = accrued;
////        }
////
////        C currency() {
////            return par.currencyId();
////        }
////
////        Money<C> zero() {
////            return Money.zero(this.currency());
////        }
////
////        @Override
////        public Money<C> parValue() {
////            return par;
////        }
////
////        @Override
////        public Money<C> cleanValue() {
////            return cleanFlows.values().stream().reduce(zero(), Money::plus);
////        }
////
////        static <C extends CurrencyId> FiniteSequence<CashPayment<C>> flow(final NavigableMap<LocalDate, Money<C>> full) {
////            final List<CashPayment<C>> flow = full.entrySet()
////                    .stream()
////                    .map(e -> CashPayment.of(e.getKey(), e.getValue()))
////                    .collect(Collectors.toList());
////            return FiniteSequence.of(flow);
////        }
////
////        FiniteSequence<CashPayment<C>> cleanFlow() {
////            return flow(cleanFlows);
////        }
////
////        @Override
////        public FiniteSequence<CashPayment<C>> cleanFlow(final Interval interval) {
////            return flow(cleanFlows.tailMap(interval.startInclusive(), true).headMap(interval.endInclusive(), true));
////        }
////
////        @Override
////        public Money<C> dirtyValue() {
////            return this.cleanValue().plus(this.accrued());
////        }
////
////        @Override
////        public Money<C> accrued() {
////            return accrued;
////        }
////
////    }
//    private static final class CleanFlowsAndAccruedBondPrice<C extends CurrencyId>
//            implements BondPrice<C> {
//
//        @Override
//        public Money<C> parValue() {
//            throw new UnsupportedOperationException(); //TODO
//        }
//
//        @Override
//        public Money<C> cleanValue() {
//            throw new UnsupportedOperationException(); //TODO
//        }
//
//        @Override
//        public Sequence<CashPayment<C>> cleanFlow(Interval interval) {
//            throw new UnsupportedOperationException(); //TODO
//        }
//
//        @Override
//        public Money<C> dirtyValue() {
//            throw new UnsupportedOperationException(); //TODO
//        }
//
//        @Override
//        public BondPrice<C> shift(BondShifts shifts) {
//            throw new UnsupportedOperationException(); //TODO
//        }
//
//    }
//
//}
