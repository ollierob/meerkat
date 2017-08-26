package net.meerkat.money;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import net.meerkat.identifier.currency.CurrencyId;

/**
 *
 * @author ollie
 */
public class DoubleMoney<C extends CurrencyId>
        implements Money<C> {
    
    private final double amount;
    private final C currency;
    
    public DoubleMoney(double amount, C currency) {
        this.amount = amount;
        this.currency = currency;
    }
    
    @Override
    public C currencyId() {
        return currency;
    }
    
    @Override
    public Double amount() {
        return amount;
    }
    
    @Override
    public double doubleValue() {
        return amount;
    }
    
    @Override
    public DoubleMoney<C> times(final Number n) {
        return new DoubleMoney<>(amount * n.doubleValue(), currency);
    }
    
    @Override
    public DoubleMoney<C> plus(final Money<C> that) {
        return new DoubleMoney<>(amount + that.doubleValue(), currency);
    }
    
    @Override
    public Money<C> times(final Number that, final RoundingMode rounding) {
        return new DoubleMoney<>(amount * that.doubleValue(), currency);
    }
    
    @Override
    public BigDecimal decimalValue(final MathContext context) {
        return BigDecimal.valueOf(amount).round(context);
    }
    
    @Override
    public DoubleMoney<C> reciprocal() {
        return new DoubleMoney<>(1 / amount, currency);
    }
    
}
