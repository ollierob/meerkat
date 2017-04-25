package net.meerkat.issue;

import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.issuer.HasIssuerId;
import net.meerkat.issuer.IssuerId;

/**
 *
 * @author ollie
 */
@XmlRootElement
public interface Issue extends HasIssuerId {

    static Issue of(final IssuerId id) {
        return new MinimalIssue(id);
    }

}
