package net.meerkat.instrument.interest;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.money.Money;
import net.meerkat.money.interest.fixed.FixedInterestRate;
import net.ollie.goat.temporal.date.Dates;
import net.ollie.goat.temporal.date.interim.CompleteInterval;
import net.meerkat.Explainable;
import net.meerkat.money.interest.earning.InterestEarning;
import net.meerkat.instrument.Issued;
import net.meerkat.instrument.NamedInstrument;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.HasCurrencyId;

/**
 *
 * @author ollie
 */
@SuppressWarnings("deprecation")
@XmlRootElement
public class CertificateOfDeposit
        extends NamedInstrument
        implements MoneyMarketSecurity, HasCurrencyId, Issued, InterestEarning.Fixed, Explainable {

    private static final long serialVersionUID = 1L;

    @XmlElementRef(name = "rate")
    private FixedInterestRate rate;

    @XmlElementRef(name = "notional")
    private Money<?> notional;

    @XmlAttribute(name = "issued")
    private LocalDate issued;

    @XmlElement(name = "term")
    private CompleteInterval term;

    CertificateOfDeposit() {
    }

    @Override
    public Money<?> notional() {
        return notional;
    }

    @Override
    public LocalDate issueDate() {
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
    public CurrencyId currencyId() {
        return notional.currencyId();
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

    @Override
    public ExplanationBuilder explain() {
        return super.explain()
                .put("interest rate", rate)
                .put("notional", notional)
                .put("issued", issued)
                .put("start", term.first())
                .put("maturity", term.last());
    }

}
