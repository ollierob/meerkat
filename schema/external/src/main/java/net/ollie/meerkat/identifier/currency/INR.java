package net.ollie.meerkat.identifier.currency;

/**
 *
 * @author Ollie
 */
public class INR extends AbstractCurrencyIso {

    private static final long serialVersionUID = 1L;

    @Override
    public String symbol() {
        return "₹";
    }

    @Override
    public String name() {
        return "Indian Rupee";
    }

}
