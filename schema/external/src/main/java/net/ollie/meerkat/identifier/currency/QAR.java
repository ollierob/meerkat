package net.ollie.meerkat.identifier.currency;

/**
 *
 * @author ollie
 */
public class QAR extends NationalCurrencyIso {

    private static final long serialVersionUID = 1L;

    QAR() {
    }

    @Override
    public String symbol() {
        return "QR";
    }

    @Override
    public String name() {
        return "Qatari riyal";
    }

    @Override
    public String uniqueSymbol() {
        return "QR";
    }

}
