package publicapi.events;

import com.github.moaxcp.graphs.events.*;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class Equals {
    @Test
    void allEdgesPropertyAdded() {
        EqualsVerifier.forClass(AllEdgesPropertyAdded.class).withRedefinedSuperclass().verify();
    }

    @Test
    void allEdgesPropertyRemoved() {
        EqualsVerifier.forClass(AllEdgesPropertyRemoved.class).withRedefinedSuperclass().verify();
    }

    @Test
    void allEdgesPropertyUpdated() {
        EqualsVerifier.forClass(AllEdgesPropertyUpdated.class).withRedefinedSuperclass().verify();
    }

    @Test
    void allVerticesPropertyAdded() {
        EqualsVerifier.forClass(AllVerticesPropertyAdded.class).withRedefinedSuperclass().verify();
    }

    @Test
    void allVerticesPropertyRemoved() {
        EqualsVerifier.forClass(AllVerticesPropertyRemoved.class).withRedefinedSuperclass().verify();
    }

    @Test
    void allVerticesPropertyUpdated() {
        EqualsVerifier.forClass(AllVerticesPropertyUpdated.class).withRedefinedSuperclass().verify();
    }

    @Test
    void directedGraphCreated() {
        EqualsVerifier.forClass(DirectedGraphCreated.class).verify();
    }

    @Test
    void edgeCreated() {
        EqualsVerifier.forClass(EdgeCreated.class).verify();
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
        EqualsVerifier.forClass(EdgeRemoved.class).verify();
    }

    @Test
    void graphPropertyAdded() {
        EqualsVerifier.forClass(GraphPropertyAdded.class).withRedefinedSuperclass().verify();
    }

    @Test
    void graphPropertyRemoved() {
        EqualsVerifier.forClass(GraphPropertyRemoved.class).withRedefinedSuperclass().verify();
    }

    @Test
    void graphPropertyUpdated() {
        EqualsVerifier.forClass(GraphPropertyUpdated.class).withRedefinedSuperclass().verify();
    }

    @Test
    void undirectedGraphCreated() {
        EqualsVerifier.forClass(UndirectedGraphCreated.class).verify();
    }

    @Test
    void vertexCreated() {
        EqualsVerifier.forClass(VertexCreated.class).verify();
    }

    @Test
    void vertexPropertyAdded() {
        EqualsVerifier.forClass(VertexPropertyAdded.class).withRedefinedSuperclass().verify();
    }

    @Test
    void vertexPropertyRemoved() {
        EqualsVerifier.forClass(VertexPropertyRemoved.class).withRedefinedSuperclass().verify();
    }

    @Test
    void vertexPropertyUpdated() {
        EqualsVerifier.forClass(VertexPropertyUpdated.class).withRedefinedSuperclass().verify();
    }

    @Test
    void vertexRemoved() {
        EqualsVerifier.forClass(VertexRemoved.class).verify();
    }
}
