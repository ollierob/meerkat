package net.ollie.meerkat.pricing.bond;

import java.time.LocalDate;

import net.ollie.meerkat.calculate.price.bond.BondPrice;
import net.ollie.meerkat.calculate.price.bond.BondShifts;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.security.bond.ConvertibleBond;
import net.ollie.meerkat.security.bond.FixedCouponBond;
import net.ollie.meerkat.security.bond.FloatingRateNote;
import net.ollie.meerkat.security.bond.PerpetualBond;
import net.ollie.meerkat.calculate.price.bond.BondTypePricer;
import net.ollie.meerkat.calculate.price.bond.BondPricer;

/**
 *
 * @author ollie
 */
public class NativeBondPricer implements BondPricer {

    private final BondTypePricer<PerpetualBond> perpetualPricer;
    private final BondTypePricer<FixedCouponBond> fixedCouponPricer;
    private final BondTypePricer<FloatingRateNote> floatingNotePricer;
    private final BondTypePricer<ConvertibleBond> convertiblePricer;

    public NativeBondPricer(
            final BondTypePricer<PerpetualBond> perpetualPricer,
            final BondTypePricer<FixedCouponBond> fixedCouponPricer,
            final BondTypePricer<FloatingRateNote> floatingNotePricer,
            final BondTypePricer<ConvertibleBond> convertiblePricer) {
        this.perpetualPricer = perpetualPricer;
        this.fixedCouponPricer = fixedCouponPricer;
        this.floatingNotePricer = floatingNotePricer;
        this.convertiblePricer = convertiblePricer;
    }

    @Override
    public <C extends CurrencyId> BondPriceContext<C> priceContext(final LocalDate date, final C currency, final BondShifts shifts) {
        return new BondPriceContext<C>() {

            @Override
            public BondPrice<C> handle(final FixedCouponBond bond) {
                return fixedCouponPricer.price(date, bond, shifts, currency);
            }

            @Override
            public BondPrice<C> handle(final FloatingRateNote bond) {
                return floatingNotePricer.price(date, bond, shifts, currency);
            }

            @Override
            public BondPrice<C> handle(final PerpetualBond bond) {
                return perpetualPricer.price(date, bond, shifts, currency);
            }

            @Override
            public BondPrice<C> handle(final ConvertibleBond bond) {
                return convertiblePricer.price(date, bond, shifts, currency);
            }

        };
    }

}
