package net.ollie.meerkat.calculate.price.bond;

import net.ollie.meerkat.numeric.interest.curve.YieldCurve;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.calculate.price.SecurityShifts;

/**
 *
 * @author ollie
 */
public interface BondShifts extends SecurityShifts {

    Money shiftPrice(Money price);

    YieldCurve shiftYieldCurve(YieldCurve curve);

    BondShifts NONE = new BondShifts() {

        @Override
        public Money shiftPrice(final Money price) {
            return price;
        }

        @Override
        public YieldCurve shiftYieldCurve(final YieldCurve curve) {
            return curve;
        }

    };

}
