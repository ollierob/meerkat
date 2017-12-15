package net.meerkat.identifier.instrument;

import net.meerkat.StringWrapper;
import net.meerkat.identifier.Iso;

import javax.annotation.Nonnull;

/**
 * @see <a href="http://www.anna-web.org/standards/cfi-iso-10962/">Classification of Financial Instruments</a>
 */
public class Cfi extends StringWrapper implements Iso {

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
    public static final char REFERENCE = 'R';
    public static final char SWAP = 'S';

    public static Cfi bond(final char interestType, final char guarantee, final char reimbursement, final char form) {
        return new Cfi(DEBT, 'B', interestType, guarantee, reimbursement, form);
    }

    protected Cfi(final char type, final char group, final char a1, final char a2, final char a3, final char a4) {
        super(new String(new char[]{type, group, a1, a2, a3, a4}));
    }

    protected Cfi(final String value) {
        super(value);
    }

    public char category() {
        return this.chartAt(0);
    }

    public char group() {
        return this.chartAt(1);
    }

    public String attributes() {
        return this.value().substring(2);
    }

    @Override
    public char first() {
        return super.first();
    }

    @Nonnull
    @Override
    public String value() {
        return super.value();
    }

    @Override
    public String standard() {
        return "10962";
    }

}
