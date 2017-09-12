package net.meerkat.issue;

import net.meerkat.issuer.HasIssuerId;
import net.meerkat.issuer.IssuerId;

/**
 *
 * @author ollie
 */
public interface Issue extends HasIssuerId {

    static Issue of(final IssuerId id) {
        return new MinimalIssue(id);
    }

}
