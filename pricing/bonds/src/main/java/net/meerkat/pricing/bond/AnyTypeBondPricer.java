package net.meerkat.pricing.bond;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.bond.*;
import net.meerkat.pricing.bond.shifts.BondPriceShifts;

import java.time.temporal.Temporal;

/**
 *
 * @author ollie
 */
public class AnyTypeBondPricer<T extends Temporal> implements GenericBondPricer<T> {

    private final BondPricer<T, ? super PerpetualBond<?>> perpetualPricer;
    private final BondPricer<T, ? super FixedCouponBond> fixedCouponPricer;
    private final BondPricer<T, ? super FloatingRateNote> floatingNotePricer;
    private final BondPricer<T, ? super ConvertibleBond> convertiblePricer;
    private final BondPricer<T, ? super VariableRateBond> variablePricer;

    public AnyTypeBondPricer(
            final BondPricer<T, ? super PerpetualBond<?>> perpetualPricer,
            final BondPricer<T, ? super FixedCouponBond> fixedCouponPricer,
            final BondPricer<T, ? super FloatingRateNote> floatingNotePricer,
            final BondPricer<T, ? super ConvertibleBond> convertiblePricer,
            final BondPricer<T, ? super VariableRateBond> variablePricer) {
        this.perpetualPricer = perpetualPricer;
        this.fixedCouponPricer = fixedCouponPricer;
        this.floatingNotePricer = floatingNotePricer;
        this.convertiblePricer = convertiblePricer;
        this.variablePricer = variablePricer;
    }

    @Override
    public <C extends CurrencyId> BondPriceContext<C> priceContext(
            final T temporal,
            final C currency,
            final BondPriceShifts shifts) {

        return new BondPriceContext<C>() {

            @Override
            public BondPrice.Shiftable<C> handle(final FixedCouponBond bond) {
                return fixedCouponPricer.price(temporal, bond, currency, shifts);
            }

            @Override
            public BondPrice.Shiftable<C> handle(final FloatingRateNote bond) {
                return floatingNotePricer.price(temporal, bond, currency, shifts);
            }

            @Override
            public BondPrice.Shiftable<C> handle(final PerpetualBond<?> bond) {
                return perpetualPricer.price(temporal, bond, currency, shifts);
            }

            @Override
            public BondPrice.Shiftable<C> handle(final ConvertibleBond bond) {
                return convertiblePricer.price(temporal, bond, currency, shifts);
            }

            @Override
            public BondPrice.Shiftable<C> handle(final VariableRateBond bond) {
                return variablePricer.price(temporal, bond, currency, shifts);
            }

        };

    }

}
