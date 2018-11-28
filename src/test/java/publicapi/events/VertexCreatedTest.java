package publicapi.events;

import com.github.moaxcp.graphs.events.VertexCreated;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class VertexCreatedTest {
    @Test
    void equalsContract() {
        EqualsVerifier.forClass(VertexCreated.class).verify();
    }
}
