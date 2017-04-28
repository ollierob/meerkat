package net.meerkat.instrument.interest.future.bond;

import java.time.Period;
import java.util.Set;

import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.NamedInstrument;
import net.meerkat.money.Money;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.temporal.date.months.Months;

/**
 *
 * @author ollie
 */
public class GenericBondFutureBasket<C extends CurrencyId>
        extends NamedInstrument
        implements BondFutureContract<C> {

    @XmlElementRef(name = "deliverable")
    private Set<InstrumentId> deliverable;

    @XmlElementRef(name = "minimumMaturity")
    private Period minimumMaturity;

    @XmlElementRef(name = "maximumMaturity")
    private Period maximumMaturity;

    @XmlElementRef(name = "nominalCoupon")
    private Percentage nominalCoupon;

    @XmlElementRef(name = "notional")
    private Money<C> notional;

    @XmlElementRef(name = "deliveryMonths")
    private Months deliveryMonths;

    public GenericBondFutureBasket(
            final String name,
            final InstrumentIds ids,
            final Set<InstrumentId> deliverable,
            final Period minimumMaturity,
            final Period maximumMaturity,
            final Percentage nominalCoupon,
            final Money<C> notional,
            final Months deliveryMonths) {
        super(name, ids);
        this.deliverable = deliverable;
        this.minimumMaturity = minimumMaturity;
        this.maximumMaturity = maximumMaturity;
        this.nominalCoupon = nominalCoupon;
        this.notional = notional;
        this.deliveryMonths = deliveryMonths;
    }

    @Override
    public Set<InstrumentId> deliverableBonds() {
        return deliverable;
    }

    @Override
    public Period minimumMaturity() {
        return minimumMaturity;
    }

    @Override
    public Period maximumMaturity() {
        return maximumMaturity;
    }

    @Override
    public Percentage nominalCoupon() {
        return nominalCoupon;
    }

    @Override
    public Money<C> notional() {
        return notional;
    }

    @Override
    public Months deliveryMonths() {
        return deliveryMonths;
    }

}
