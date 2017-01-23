package net.meerkat.reporting.security;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import net.meerkat.Provider;
import net.meerkat.identifier.SecurityInMarketId;
import net.meerkat.instrument.InstrumentDefinition;
import net.meerkat.identifier.instrument.InstrumentId;

/**
 *
 * @author Ollie
 */
public interface SecurityDefinitionProvider extends Provider<SecurityInMarketId, InstrumentDefinition> {

    @Nonnull
    Set<SecurityInMarketId> getSecurityInMarketIds(@Nonnull InstrumentId SecurityId);

    @Nonnull
    default Set<InstrumentDefinition> getAll(@Nonnull final InstrumentId securityId) {
        return this.getSecurityInMarketIds(securityId)
                .stream()
                .map(this::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

}
