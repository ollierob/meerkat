package net.meerkat.identifier.organization;

import net.meerkat.Explainable;
import net.meerkat.organization.OrganizationId;

/**
 * @author Ollie
 * @see <a href="http://iatistandard.org/202/organisation-identifiers/">IATI</a>
 */
public record Iati(String namespace, String base) implements OrganizationId, Explainable {

    @Override
    public ExplanationBuilder explain() {
        return this.explanationBuilder()
                .put("namespace", namespace)
                .put("base", base);
    }

}
