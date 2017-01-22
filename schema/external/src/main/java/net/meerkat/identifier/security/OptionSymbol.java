package net.meerkat.identifier.security;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author ollie
 * @see <a href="https://en.wikipedia.org/wiki/Option_symbol">Optional
 * symbol</a>
 */
@XmlRootElement
public class OptionSymbol implements SecurityId, Externalizable {

    private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "root")
    private String root;

    @XmlAttribute(name = "expiration")
    private LocalDate expiration;

    @XmlAttribute(name = "put")
    private boolean isPut;

    @XmlAttribute(name = "strike")
    private BigDecimal strike;

    @Deprecated
    OptionSymbol() {
    }

    public OptionSymbol(String root, LocalDate expiration, boolean isPut, BigDecimal strike) {
        this.root = root;
        this.expiration = expiration;
        this.isPut = isPut;
        this.strike = strike;
    }

    public String toString(final Function<OptionSymbol, String> toString) {
        return toString.apply(this);
    }

    public String toOccString() {
        return this.toString(OptionSymbol::toOccSymbol);
    }

    @Override
    public String toString() {
        return this.toOccString();
    }

    private static final DateTimeFormatter YYMMDD = DateTimeFormatter.ofPattern("yyMMdd");
    private static final DecimalFormat SIGFIGS = new DecimalFormat("00000000");

    public static String toOccSymbol(final OptionSymbol symbol) {
        return String.format("%1$-6s", symbol.root)
                + YYMMDD.format(symbol.expiration)
                + (symbol.isPut ? 'P' : 'C')
                + SIGFIGS.format(symbol.strike.scaleByPowerOfTen(3));
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(root);
        out.writeObject(expiration);
        out.writeBoolean(isPut);
        out.writeObject(strike);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        root = in.readUTF();
        expiration = (LocalDate) in.readObject();
        isPut = in.readBoolean();
        strike = (BigDecimal) in.readObject();
    }

}
