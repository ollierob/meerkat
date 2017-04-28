package net.meerkat.reporting.security;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import net.ollie.goat.data.Provider;
import net.meerkat.instrument.InstrumentDefinition;
import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.identifier.InstrumentInMarketId;

/**
 *
 * @author Ollie
 */
public interface SecurityDefinitionProvider extends Provider<InstrumentInMarketId, InstrumentDefinition> {

    @Nonnull
    Set<InstrumentInMarketId> getSecurityInMarketIds(@Nonnull InstrumentId SecurityId);

    @Nonnull
    default Set<InstrumentDefinition> getAll(@Nonnull final InstrumentId securityId) {
        return this.getSecurityInMarketIds(securityId)
                .stream()
                .map(this::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

}
