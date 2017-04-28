package net.meerkat.instrument.interest.swap;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.Explainable;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.CurrencyIds;
import net.meerkat.identifier.currency.HasCurrencyIds;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.NamedInstrument;
import net.meerkat.instrument.dates.Issued;
import net.meerkat.instrument.dates.Traded;
import net.meerkat.instrument.derivative.swap.Swap;
import net.meerkat.instrument.interest.InterestRateDerivative;
import net.meerkat.issuer.IssuerId;
import net.meerkat.utils.collections.sequence.FiniteSequence;
import net.meerkat.utils.collections.sequence.Sequence;

/**
 *
 * @author ollie
 */
public class InterestRateSwap
        extends NamedInstrument
        implements InterestRateDerivative, Swap, Issued, Traded, HasCurrencyIds, Explainable {
    
    private static final long serialVersionUID = 1L;
    
    @XmlElementRef(name = "leg")
    private List<InterestRateSwapLeg<?, ?>> legs;
    
    @XmlAttribute(name = "tradeDate")
    private LocalDate tradeDate;
    
    @XmlElementRef(name = "issuer")
    private IssuerId issuerId;
    
    public InterestRateSwap(
            final String name,
            final InstrumentIds ids,
            final List<InterestRateSwapLeg<?, ?>> legs,
            final LocalDate tradeDate,
            final IssuerId issuerId) {
        super(name, ids);
        this.legs = legs;
        this.tradeDate = tradeDate;
        this.issuerId = issuerId;
    }
    
    @Override
    public FiniteSequence<InterestRateSwapLeg<?, ?>> legs() {
        return Sequence.of(legs);
    }
    
    @Override
    public IssuerId issuerId() {
        return issuerId;
    }
    
    @Override
    @Deprecated
    public LocalDate issueDate() {
        return this.tradeDate();
    }
    
    @Override
    public LocalDate tradeDate() {
        return tradeDate;
    }
    
    @Override
    public CurrencyIds currencyIds() {
        final Set<CurrencyId> currencies = new HashSet<>(2);
        for (final InterestRateSwapLeg<?, ?> leg : legs) {
            currencies.add(leg.payCurrency());
            currencies.add(leg.receiveCurrency());
        }
        return CurrencyIds.of(currencies);
    }
    
    @Override
    public ExplanationBuilder explain() {
        return super.explain()
                .put("traded", tradeDate)
                .put("issuer", issuerId)
                .put("legs", legs);
    }
    
}
