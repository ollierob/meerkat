package net.meerkat.instrument.equity.pair;

public interface HasRatioRelationship extends LegRelationship {

    double getRatio(EquityPair.Leg leg);

}
