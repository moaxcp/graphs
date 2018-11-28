package publicapi.events;

import com.github.moaxcp.graphs.events.DirectedGraphCreated;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class DirectedGraphCreatedTest {
    @Test
    void equalsContract() {
        EqualsVerifier.forClass(DirectedGraphCreated.class).verify();
    }
}
