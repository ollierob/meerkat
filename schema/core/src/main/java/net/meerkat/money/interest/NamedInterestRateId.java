package net.meerkat.money.interest;

import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.Named;
import net.meerkat.identifier.currency.CurrencyId;

/**
 *
 * @author ollie
 */
public class NamedInterestRateId
        extends Named
        implements InterestRateId {

    private static final long serialVersionUID = 1L;

    @XmlElementRef(name = "currency")
    private CurrencyId currencyId;

    @Deprecated
    NamedInterestRateId() {
    }

    public NamedInterestRateId(final String value, final CurrencyId currencyId) {
        super(value);
        this.currencyId = currencyId;
    }

    @Override
    public CurrencyId currencyId() {
        return currencyId;
    }

}
