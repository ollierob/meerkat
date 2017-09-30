package net.meerkat.position;

import java.util.Map;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface PositionWithQuantity extends Position {

    @Nonnull
    Number quantity();

    @Override
    default Map<String, Object> explain() {
        return this.explanationBuilder()
                .put("position ID", this.positionId())
                .put("quantity", this.quantity());
    }

}
