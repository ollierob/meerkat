package net.ollie.meerkat.security.moneymarket;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.goat.money.Money;
import net.ollie.goat.money.currency.Currency;
import net.ollie.goat.money.currency.HasCurrency;
import net.ollie.goat.money.interest.fixed.FixedInterestRate;
import net.ollie.goat.temporal.date.Dates;
import net.ollie.goat.temporal.date.interim.CompleteInterval;
import net.ollie.meerkat.numeric.interest.earning.InterestEarning;
import net.ollie.meerkat.security.Issued;
import net.ollie.meerkat.security.NamedSecurity;

/**
 *
 * @author ollie
 */
@SuppressWarnings("deprecation")
@XmlRootElement
public class CertificateOfDeposit
        extends NamedSecurity
        implements MoneyMarketSecurity, HasCurrency, Issued, InterestEarning.Fixed {

    private static final long serialVersionUID = 1L;

    @XmlElementRef(name = "rate")
    private FixedInterestRate rate;

    @XmlElementRef(name = "principal")
    private Money<?> principal;

    @XmlAttribute(name = "issued")
    private LocalDate issued;

    @XmlElement(name = "term")
    private CompleteInterval term;

    CertificateOfDeposit() {
    }

    @Override
    public Money<?> notional() {
        return principal;
    }

    @Override
    public LocalDate issued() {
        return issued;
    }

    @Override
    public FixedInterestRate rate() {
        return rate;
    }

    public LocalDate start() {
        return term.startInclusive();
    }

    public LocalDate maturity() {
        return term.endInclusive();
    }

    @Override
    public Currency currency() {
        return principal.currency();
    }

    public Money<?> accrueFrom(final LocalDate date) {
        return this.accrue(Dates.max(this.start(), date), this.maturity());
    }

    @Override
    public Money<?> accrue(final LocalDate since, final LocalDate until) {
        return Fixed.super.accrue(
                Dates.max(this.start(), since),
                Dates.min(this.maturity(), until));
    }

    @Override
    public <R> R handleWith(final MoneyMarketSecurity.Handler<R> handler) {
        return handler.handle(this);
    }

}
