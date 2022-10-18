package net.meerkat.instrument.equity.pair;

import net.meerkat.money.Money;

public interface HasCashRelationship extends LegRelationship {

    Money<?> getCash(EquityPair.Leg leg);

}
