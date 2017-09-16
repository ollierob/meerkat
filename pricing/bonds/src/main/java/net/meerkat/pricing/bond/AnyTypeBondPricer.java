package net.meerkat.pricing.bond;

import java.time.temporal.Temporal;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.bond.ConvertibleBond;
import net.meerkat.instrument.bond.FixedCouponBond;
import net.meerkat.instrument.bond.FloatingRateNote;
import net.meerkat.instrument.bond.PerpetualBond;
import net.meerkat.instrument.bond.VariableRateBond;

/**
 *
 * @author ollie
 */
public class AnyTypeBondPricer<T extends Temporal> implements GenericBondPricer<T> {

    private final BondPricer<T, PerpetualBond> perpetualPricer;
    private final BondPricer<T, FixedCouponBond> fixedCouponPricer;
    private final BondPricer<T, FloatingRateNote> floatingNotePricer;
    private final BondPricer<T, ConvertibleBond> convertiblePricer;
    private final BondPricer<T, VariableRateBond> variablePricer;

    public AnyTypeBondPricer(
            final BondPricer<T, PerpetualBond> perpetualPricer,
            final BondPricer<T, FixedCouponBond> fixedCouponPricer,
            final BondPricer<T, FloatingRateNote> floatingNotePricer,
            final BondPricer<T, ConvertibleBond> convertiblePricer,
            final BondPricer<T, VariableRateBond> variablePricer) {
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
            final BondShifts shifts) {

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
