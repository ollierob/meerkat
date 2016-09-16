package net.ollie.meerkat.security.bond;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.goat.collection.Iterables;
import net.ollie.goat.money.currency.Currency;
import net.ollie.meerkat.security.bond.coupon.BondCoupon;
import net.ollie.meerkat.security.bond.coupon.FixedRateCoupon;
import net.ollie.meerkat.security.bond.coupon.FloatingCoupon;

/**
 * A bond with fixed and floating elements.
 *
 * @author Ollie
 */
@XmlRootElement
public class VariableRateBond extends StraightBond {

    private static final long serialVersionUID = 1L;

    @XmlElements({
        @XmlElement(name = "fixed", type = FixedRateCoupon.class),
        @XmlElement(name = "floating", type = FloatingCoupon.class)
    })
    private List<BondCoupon> coupons;

    @Deprecated
    protected VariableRateBond() {
    }

    @Override
    public StraightBondCoupons<?> coupons() {
        final Currency commonCurrency = Iterables.requireCommonElement(coupons, BondCoupon::currency);
        return new VariableRateBondCoupons<>(commonCurrency);
    }

    @Override
    public <R> R handleWith(final Bond.Handler<R> handler) {
        return handler.handle(this);
    }

    @Override
    public ExplanationBuilder explain() {
        return super.explain();
    }

    public class VariableRateBondCoupons<C extends Currency>
            extends StraightBondCoupons<BondCoupon> {

        private final C commonCurrency;

        VariableRateBondCoupons(final C commonCurrency) {
            this.commonCurrency = commonCurrency;
        }

        @Override
        public BondCoupon get(final int index) {
            return coupons.get(index);
        }

        @Override
        public int size() {
            return coupons == null ? 0 : coupons.size();
        }

        @Override
        public boolean isEmpty() {
            return coupons == null || coupons.isEmpty();
        }

        @Override
        public C currency() {
            return commonCurrency;
        }

    }

}
