package net.meerkat.identifier.instrument.type;

import net.meerkat.identifier.Iso;

/**
 * @see <a href="http://www.anna-web.org/standards/cfi-iso-10962/">Classification of Financial Instruments</a>
 */
public record Cfi(String value) implements Iso {

    //http://www.iotafinance.com/en/Classification-of-Financial-Instrument-codes-CFI-ISO-10962.html

    public static final char COLLECTIVE_INVESTMENT_VEHICLE = 'C';
    public static final char DEBT = 'D';
    public static final char EQUITY = 'E';
    public static final char FUTURE = 'F';
    public static final char SPOT = 'I';
    public static final char FORWARD = 'J';
    public static final char STRATEGY = 'K';
    public static final char FINANCING = 'L';
    public static final char MISC = 'M';
    public static final char LISTED_OPTION = 'O';
    public static final char RIGHT = 'R';
    public static final char SWAP = 'S';

    public static Cfi bond(final char interestType, final char guarantee, final char reimbursement, final char form) {
        return new Cfi(DEBT, 'B', interestType, guarantee, reimbursement, form);
    }

    protected Cfi(final char type, final char group, final char a1, final char a2, final char a3, final char a4) {
        this(new String(new char[]{type, group, a1, a2, a3, a4}));
    }

    public char category() {
        return value.charAt(0);
    }

    public char group() {
        return value.charAt(1);
    }

    public String attributes() {
        return this.value().substring(2);
    }

    @Override
    public String standard() {
        return "10962";
    }

}
