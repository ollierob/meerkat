package net.ollie.meerkat.rating;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Set;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class LetterOfCredit implements CreditRating, Externalizable {

    @XmlElementRef(name = "underlying")
    private CreditRating underlying;

    @Override
    public boolean isInvestmentGrade() {
        return underlying.isInvestmentGrade();
    }

    @Override
    public String description() {
        return underlying.description();
    }

    @Override
    public Set<CreditRatingBand> bands() {
        return underlying.bands();
    }

    @Override
    public String agency() {
        return underlying.agency();
    }

    @Override
    public void writeExternal(final ObjectOutput out) throws IOException {
        out.writeObject(underlying);
    }

    @Override
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        underlying = (CreditRating) in.readObject();
    }

}
