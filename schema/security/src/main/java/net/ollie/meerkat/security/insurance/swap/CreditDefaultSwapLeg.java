package net.ollie.meerkat.security.insurance.swap;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.money.Money;
import net.ollie.meerkat.security.derivative.swap.SwapLeg;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class CreditDefaultSwapLeg implements SwapLeg {

    @XmlAttribute(name = "payment")
    private LocalDate payment;

    @XmlElementRef(name = "premium")
    private Money<?> premium;

    CreditDefaultSwapLeg(final LocalDate payment, final Money<?> premium) {
        this.payment = payment;
        this.premium = premium;
    }

    public LocalDate paymentDate() {
        return payment;
    }

    public Money<?> premium() {
        return premium;
    }

}
