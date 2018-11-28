package publicapi.events;

import com.github.moaxcp.graphs.events.UndirectedGraphCreated;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class UndirectedGraphCreatedTest {
    @Test
    void equalsContract() {
        EqualsVerifier.forClass(UndirectedGraphCreated.class).verify();
    }
}
