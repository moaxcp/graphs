package publicapi.events;

import com.github.moaxcp.graphs.events.*;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class Equals {
    @Test
    void allEdgesPropertyAdded() {
        EqualsVerifier.forClass(AllEdgesPropertyAdded.class).verify();
    }

    @Test
    void allEdgesPropertyRemoved() {
        EqualsVerifier.forClass(AllEdgesPropertyRemoved.class).verify();
    }

    @Test
    void allEdgesPropertyUpdated() {
        EqualsVerifier.forClass(AllEdgesPropertyUpdated.class).verify();
    }

    @Test
    void allVerticesPropertyAdded() {
        EqualsVerifier.forClass(AllVerticesPropertyAdded.class).verify();
    }

    @Test
    void allVerticesPropertyRemoved() {
        EqualsVerifier.forClass(AllVerticesPropertyRemoved.class).verify();
    }

    @Test
    void allVerticesPropertyUpdated() {
        EqualsVerifier.forClass(AllVerticesPropertyUpdated.class).verify();
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
        EqualsVerifier.forClass(EdgePropertyAdded.class).verify();
    }

    @Test
    void edgePropertyRemoved() {
        EqualsVerifier.forClass(EdgePropertyRemoved.class).verify();
    }

    @Test
    void edgePropertyUpdated() {
        EqualsVerifier.forClass(EdgePropertyUpdated.class).verify();
    }

    @Test
    void edgeRemoved() {
        EqualsVerifier.forClass(EdgeRemoved.class).verify();
    }

    @Test
    void graphPropertyAdded() {
        EqualsVerifier.forClass(GraphPropertyAdded.class).verify();
    }

    @Test
    void graphPropertyRemoved() {
        EqualsVerifier.forClass(GraphPropertyRemoved.class).verify();
    }

    @Test
    void graphPropertyUpdated() {
        EqualsVerifier.forClass(GraphPropertyUpdated.class).verify();
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
        EqualsVerifier.forClass(VertexPropertyAdded.class).verify();
    }

    @Test
    void vertexPropertyRemoved() {
        EqualsVerifier.forClass(VertexPropertyRemoved.class).verify();
    }

    @Test
    void vertexPropertyUpdated() {
        EqualsVerifier.forClass(VertexPropertyUpdated.class).verify();
    }

    @Test
    void vertexRemoved() {
        EqualsVerifier.forClass(VertexRemoved.class).verify();
    }
}
