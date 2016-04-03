package net.ollie.meerkat.reporting.security;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import net.ollie.meerkat.identifier.SecurityInMarketId;
import net.ollie.meerkat.identifier.security.SecurityId;
import net.ollie.meerkat.reporting.Provider;
import net.ollie.meerkat.security.SecurityDefinition;

/**
 *
 * @author Ollie
 */
public interface SecurityDefinitionProvider extends Provider<SecurityInMarketId, SecurityDefinition> {

    @Nonnull
    Set<SecurityInMarketId> getSecurityInMarketIds(@Nonnull SecurityId SecurityId);

    @Nonnull
    default Set<SecurityDefinition> getAll(@Nonnull final SecurityId securityId) {
        return this.getSecurityInMarketIds(securityId)
                .stream()
                .map(this::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

}
