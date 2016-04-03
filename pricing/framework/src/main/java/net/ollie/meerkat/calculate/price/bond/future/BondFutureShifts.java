package net.ollie.meerkat.calculate.price.bond.future;

import javax.annotation.Nonnull;

import net.ollie.meerkat.calculate.price.bond.BondShifts;

/**
 *
 * @author ollie
 */
public interface BondFutureShifts {

    @Nonnull
    BondShifts bondShifts();

    static BondFutureShifts none() {
        return NoBondFutureShifts.INSTANCE;
    }

    class NoBondFutureShifts implements BondFutureShifts {

        static final NoBondFutureShifts INSTANCE = new NoBondFutureShifts();

        @Override
        public BondShifts bondShifts() {
            return BondShifts.none();
        }

    }

}
