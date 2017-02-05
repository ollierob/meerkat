package net.meerkat.issuer;

import java.util.function.Function;

import net.meerkat.hierarchy.Hierarchy;

/**
 *
 * @author ollie
 */
public interface IssuerIdHierarchy extends Hierarchy<IssuerId> {

    IssuerHierarchy resolve(Function<? super IssuerId, ? extends Issuer> issuerLookup);

}
