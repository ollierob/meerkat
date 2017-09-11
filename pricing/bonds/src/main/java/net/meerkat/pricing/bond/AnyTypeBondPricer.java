package net.meerkat.pricing.bond;

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
public class AnyTypeBondPricer implements GenericBondPricer {

    private final BondPricer<PerpetualBond> perpetualPricer;
    private final BondPricer<FixedCouponBond> fixedCouponPricer;
    private final BondPricer<FloatingRateNote> floatingNotePricer;
    private final BondPricer<ConvertibleBond> convertiblePricer;
    private final BondPricer<VariableRateBond> variablePricer;

    public AnyTypeBondPricer(
            final BondPricer<PerpetualBond> perpetualPricer,
            final BondPricer<FixedCouponBond> fixedCouponPricer,
            final BondPricer<FloatingRateNote> floatingNotePricer,
            final BondPricer<ConvertibleBond> convertiblePricer,
            final BondPricer<VariableRateBond> variablePricer) {
        this.perpetualPricer = perpetualPricer;
        this.fixedCouponPricer = fixedCouponPricer;
        this.floatingNotePricer = floatingNotePricer;
        this.convertiblePricer = convertiblePricer;
        this.variablePricer = variablePricer;
    }

    @Override
    public <C extends CurrencyId> BondPriceContext<C> priceContext(final C currency) {

        return new BondPriceContext<C>() {

            @Override
            public BondPrice.Shiftable<C> handle(final FixedCouponBond bond) {
                return fixedCouponPricer.price(bond, currency);
            }

            @Override
            public BondPrice.Shiftable<C> handle(final FloatingRateNote bond) {
                return floatingNotePricer.price(bond, currency);
            }

            @Override
            public BondPrice.Shiftable<C> handle(final PerpetualBond bond) {
                return perpetualPricer.price(bond, currency);
            }

            @Override
            public BondPrice.Shiftable<C> handle(final ConvertibleBond bond) {
                return convertiblePricer.price(bond, currency);
            }

            @Override
            public BondPrice.Shiftable<C> handle(final VariableRateBond bond) {
                return variablePricer.price(bond, currency);
            }

        };

    }

}
