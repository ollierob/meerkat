package net.meerkat.issue;

import net.meerkat.issuer.HasIssuerId;
import net.meerkat.issuer.IssuerId;

/**
 *
 * @author ollie
 */
public interface HasIssue extends HasIssuerId {

    Issue issue();

    @Override
    default IssuerId issuerId() {
        return this.issue().issuerId();
    }

}
