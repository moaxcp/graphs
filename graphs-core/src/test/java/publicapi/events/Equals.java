package publicapi.events;

import com.github.moaxcp.graphs.events.*;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class Equals {

    @Test
    void directedGraphCreated() {
        EqualsVerifier.forClass(DirectedGraphCreated.class).verify();
    }

    @Test
    void edgeCreated() {
        EqualsVerifier.forClass(EdgeCreated.class).withRedefinedSuperclass().verify();
    }

    @Test
    void edgePropertyAdded() {
        EqualsVerifier.forClass(EdgePropertyAdded.class).withRedefinedSuperclass().verify();
    }

    @Test
    void edgePropertyRemoved() {
        EqualsVerifier.forClass(EdgePropertyRemoved.class).withRedefinedSuperclass().verify();
    }

    @Test
    void edgePropertyUpdated() {
        EqualsVerifier.forClass(EdgePropertyUpdated.class).withRedefinedSuperclass().verify();
    }

    @Test
    void edgeRemoved() {
        EqualsVerifier.forClass(EdgeRemoved.class).withRedefinedSuperclass().verify();
    }

    @Test
    void edgeIdAdded() {
        EqualsVerifier.forClass(EdgeIdAdded.class).withRedefinedSuperclass().verify();
    }

    @Test
    void edgeIdUpdated() {
        EqualsVerifier.forClass(EdgeIdUpdated.class).withRedefinedSuperclass().verify();
    }

    @Test
    void edgeIdRemoved() {
        EqualsVerifier.forClass(EdgeIdRemoved.class).withRedefinedSuperclass().verify();
    }

    @Test
    void edgeToUpdated() {
        EqualsVerifier.forClass(EdgeToUpdated.class).withRedefinedSuperclass().verify();
    }

    @Test
    void edgeFromUpdated() {
        EqualsVerifier.forClass(EdgeFromUpdated.class).withRedefinedSuperclass().verify();
    }

    @Test
    void undirectedGraphCreated() {
        EqualsVerifier.forClass(UndirectedGraphCreated.class).verify();
    }
}
