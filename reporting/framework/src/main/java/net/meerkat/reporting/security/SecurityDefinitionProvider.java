package net.meerkat.reporting.security;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import net.meerkat.Provider;
import net.meerkat.identifier.SecurityInMarketId;
import net.meerkat.identifier.security.SecurityId;
import net.meerkat.security.SecurityDefinition;

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
