package publicapi;

import com.github.moaxcp.graphs.Graph;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VertexTest {
    Graph graph = new Graph("graph");
    @Test
    void testSetId() {
        var a = graph.vertex("id");
        var from = graph.edge("id", "b");
        var to = graph.edge("c", "id");
        a.setId("a");

        assertThat(a.getId()).isEqualTo("a");
        assertThat(from.getFrom()).isEqualTo("a");
        assertThat(to.getTo()).isEqualTo("a");
    }

    @Test
    void testToString() {
        var vertex = graph.vertex("id");
        vertex.setProperty("key", "value");
        assertThat(vertex.toString()).isEqualTo("Vertex 'id' {id=id, key=value}");
    }

    @Test
    void testAdjacentEdges() {
        graph.edge("A", "B");
        graph.edge("A", "C");
        graph.edge("Z", "Y");

        var vertex = graph.vertex("A");

        var edges = vertex.adjacentEdges();
        assertThat(edges).hasSize(2);
        for(Graph.Edge edge : edges) {
            assertThat(edge.getLocal().values()).contains("A");
        }
    }

    @Test
    void testConnectsTo() {
        var vertex = graph.vertex("A")
                .connectsTo("B");
        assertThat(vertex.getId()).isEqualTo("A");
        assertThat(graph.getVertices().keySet()).containsExactly("A", "B");
        assertThat(graph.getEdges()).hasSize(1);
        assertThat(graph.getEdges().iterator().next().getLocal()).containsExactly("from", "A", "to", "B");
    }

    @Test
    void testConnectsFrom() {
        var vertex = graph.vertex("A")
                .connectsFrom("B");
        assertThat(vertex.getId()).isEqualTo("A");
        assertThat(graph.getVertices().keySet()).containsExactly("A", "B");
        assertThat(graph.getEdges()).hasSize(1);
        assertThat(graph.getEdges().iterator().next().getLocal()).containsExactly("from", "B", "to", "A");
    }

    @Test
    void testEdgeTo() {
        var edge = graph.vertex("A")
                .edgeTo("B");
        assertThat(edge.getLocal()).containsExactly("from", "A", "to", "B");
        assertThat(graph.getEdges().iterator().next()).isEqualTo(edge);
        assertThat(graph.getVertices().keySet()).containsExactly("A", "B");
    }

    @Test
    void testEdgeFrom() {
        var edge = graph.vertex("A")
                .edgeFrom("B");
        assertThat(edge.getLocal()).containsExactly("from", "B", "to", "A");
        assertThat(graph.getEdges().iterator().next()).isEqualTo(edge);
        assertThat(graph.getVertices().keySet()).containsExactly("A", "B");
    }

    @Test
    void testEqualsNull() {
        var vertex = graph.vertex("A");
        assertNotEquals(vertex, null);
    }

    @Test
    void testEqualsReflexive() {
        var vertex = graph.vertex("A");
        assertEquals(vertex, vertex);
    }

    @Test
    void testEqualsSymmetric() {
        var vertex1 = graph.vertex("A");
        var vertex2 = new Graph().vertex("A");
        assertEquals(vertex1, vertex2);
        assertEquals(vertex2, vertex1);
    }

    @Test
    void testEqualsTransitive() {
        var vertex1 = graph.vertex("A");
        var vertex2 = new Graph().vertex("A");
        var vertex3 = new Graph().vertex("A");
        assertEquals(vertex1, vertex2);
        assertEquals(vertex2, vertex3);
        assertEquals(vertex1, vertex3);
    }

    @Test
    void testEqualsConsistent() {
        var vertex1 = graph.vertex("A");
        var vertex2 = new Graph().vertex("A");
        assertEquals(vertex1, vertex2);
        assertEquals(vertex1, vertex2);
        assertEquals(vertex1, vertex2);
    }

    @Test
    void testHashCodeConsistent() {
        var vertex = graph.vertex("A");
        int hashCode = vertex.hashCode();
        assertThat(vertex.hashCode()).isEqualTo(hashCode);
        assertThat(vertex.hashCode()).isEqualTo(hashCode);
    }

    @Test
    void testHashCodeForEqualVertices() {
        var vertex1 = graph.vertex("A");
        var vertex2 = new Graph().vertex("A");
        assertEquals(vertex1, vertex2);
        assertThat(vertex1.hashCode()).isEqualTo(vertex2.hashCode());
    }
}
