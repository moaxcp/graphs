package publicapi.events;

import com.github.moaxcp.graphs.events.EdgeCreated;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class EdgeCreatedTest {
    @Test
    void equalsContract() {
        EqualsVerifier.forClass(EdgeCreated.class).verify();
    }
}
