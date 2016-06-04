package net.ollie.meerkat.identifier.currency;

import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.goat.numeric.percentage.DecimalPercentage;
import net.ollie.goat.numeric.percentage.Percentage;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class JPY extends AbstractCurrencyIso {

    private static final long serialVersionUID = 1L;

    @Override
    public String symbol() {
        return "¥";
    }

    @Override
    public Percentage pip() {
        return DecimalPercentage.ONE_PERCENT;
    }

    @Override
    public String name() {
        return "Japanese Yen";
    }

}
