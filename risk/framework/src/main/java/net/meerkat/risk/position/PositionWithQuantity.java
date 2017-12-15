package net.meerkat.risk.position;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 *
 * @author ollie
 */
public interface PositionWithQuantity extends Position {

    @Nonnull
    Number quantity();

    default double doubleQuantity() {
        return this.quantity().doubleValue();
    }

    @Override
    default Map<String, Object> explain() {
        return this.explanationBuilder()
                .put("position ID", this.positionId())
                .put("quantity", this.quantity());
    }

}
