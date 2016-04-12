package net.ollie.meerkat.calculate.price.bond;

import java.time.temporal.Temporal;

import net.ollie.meerkat.calculate.price.bond.BondPrice;
import net.ollie.meerkat.calculate.price.bond.BondPricer;
import net.ollie.meerkat.calculate.price.bond.BondTypePricer;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.security.bond.ConvertibleBond;
import net.ollie.meerkat.security.bond.FixedCouponBond;
import net.ollie.meerkat.security.bond.FloatingRateNote;
import net.ollie.meerkat.security.bond.PerpetualBond;

/**
 *
 * @author ollie
 */
public class NativeBondPricer<T extends Temporal> implements BondPricer<T> {

    private final BondTypePricer<T, PerpetualBond> perpetualPricer;
    private final BondTypePricer<T, FixedCouponBond> fixedCouponPricer;
    private final BondTypePricer<T, FloatingRateNote> floatingNotePricer;
    private final BondTypePricer<T, ConvertibleBond> convertiblePricer;

    public NativeBondPricer(
            final BondTypePricer<T, PerpetualBond> perpetualPricer,
            final BondTypePricer<T, FixedCouponBond> fixedCouponPricer,
            final BondTypePricer<T, FloatingRateNote> floatingNotePricer,
            final BondTypePricer<T, ConvertibleBond> convertiblePricer) {
        this.perpetualPricer = perpetualPricer;
        this.fixedCouponPricer = fixedCouponPricer;
        this.floatingNotePricer = floatingNotePricer;
        this.convertiblePricer = convertiblePricer;
    }

    @Override
    public <C extends CurrencyId> BondPriceContext<C> priceContext(
            final T temporal,
            final C currency) {

        return new BondPriceContext<C>() {

            @Override
            public BondPrice.Shiftable<C> handle(final FixedCouponBond bond) {
                return fixedCouponPricer.price(temporal, bond, currency);
            }

            @Override
            public BondPrice.Shiftable<C> handle(final FloatingRateNote bond) {
                return floatingNotePricer.price(temporal, bond, currency);
            }

            @Override
            public BondPrice.Shiftable<C> handle(final PerpetualBond bond) {
                return perpetualPricer.price(temporal, bond, currency);
            }

            @Override
            public BondPrice.Shiftable<C> handle(final ConvertibleBond bond) {
                return convertiblePricer.price(temporal, bond, currency);
            }

        };

    }

}
