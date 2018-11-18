package publicapi;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.github.moaxcp.graphs.SimpleGraph;
import com.github.moaxcp.graphs.greenrobot.*;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class VertexTest {
    static Stream<SimpleGraph> graphs() {
        return Stream.of(
                new UndirectedEventGraph(),
                new UndirectedEventGraph(),
                new DirectedEventGraph());
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void testSetId(SimpleGraph graph) {
        var a = graph.vertex("id");
        var from = graph.edge("id", "b");
        var to = graph.edge("c", "id");
        a.setId("a");

        assertThat(a.getId()).isEqualTo("a");
        assertThat(from.getFrom()).isEqualTo("a");
        assertThat(to.getTo()).isEqualTo("a");
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void testToString(SimpleGraph graph) {
        var vertex = graph.vertex("id");
        vertex.setProperty("key", "value");
        assertThat(vertex.toString()).isEqualTo("Vertex 'id' {id=id, key=value}");
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void testAdjacentEdges(SimpleGraph graph) {
        graph.edge("A", "B");
        graph.edge("A", "C");
        graph.edge("Z", "Y");

        var vertex = graph.vertex("A");

        var edges = vertex.adjacentEdges();
        assertThat(edges).hasSize(2);
        for(var edge : edges) {
            assertThat(edge.getLocal().values()).contains("A");
        }
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void testConnectsTo(SimpleGraph graph) {
        var vertex = graph.vertex("A")
                .connectsTo("B");
        assertThat(vertex.getId()).isEqualTo("A");
        assertThat(graph.getVertices().keySet()).containsExactly("A", "B");
        assertThat(graph.getEdges()).hasSize(1);
        assertThat(graph.getEdges().iterator().next().getLocal()).containsExactly("from", "A", "to", "B");
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void testConnectsFrom(SimpleGraph graph) {
        var vertex = graph.vertex("A")
                .connectsFrom("B");
        assertThat(vertex.getId()).isEqualTo("A");
        assertThat(graph.getVertices().keySet()).containsExactly("A", "B");
        assertThat(graph.getEdges()).hasSize(1);
        assertThat(graph.getEdges().iterator().next().getLocal()).containsExactly("from", "B", "to", "A");
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void testEdgeTo(SimpleGraph graph) {
        var edge = graph.vertex("A")
                .edgeTo("B");
        assertThat(edge.getLocal()).containsExactly("from", "A", "to", "B");
        assertThat(graph.getEdges().iterator().next()).isEqualTo(edge);
        assertThat(graph.getVertices().keySet()).containsExactly("A", "B");
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void testEdgeFrom(SimpleGraph graph) {
        var edge = graph.vertex("A")
                .edgeFrom("B");
        assertThat(edge.getLocal()).containsExactly("from", "B", "to", "A");
        assertThat(graph.getEdges().iterator().next()).isEqualTo(edge);
        assertThat(graph.getVertices().keySet()).containsExactly("A", "B");
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void testEqualsNull(SimpleGraph graph) {
        var vertex = graph.vertex("A");
        assertNotEquals(vertex, null);
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void testEqualsReflexive(SimpleGraph graph) {
        var vertex = graph.vertex("A");
        assertEquals(vertex, vertex);
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void testEqualsSymmetric(SimpleGraph graph) {
        var vertex1 = graph.vertex("A");
        var vertex2 = new UndirectedEventGraph().vertex("A");
        assertEquals(vertex1, vertex2);
        assertEquals(vertex2, vertex1);
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void testEqualsTransitive(SimpleGraph graph) {
        var vertex1 = graph.vertex("A");
        var vertex2 = new UndirectedEventGraph().vertex("A");
        var vertex3 = new UndirectedEventGraph().vertex("A");
        assertEquals(vertex1, vertex2);
        assertEquals(vertex2, vertex3);
        assertEquals(vertex1, vertex3);
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void testEqualsConsistent(SimpleGraph graph) {
        var vertex1 = graph.vertex("A");
        var vertex2 = new UndirectedEventGraph().vertex("A");
        assertEquals(vertex1, vertex2);
        assertEquals(vertex1, vertex2);
        assertEquals(vertex1, vertex2);
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void testHashCodeConsistent(SimpleGraph graph) {
        var vertex = graph.vertex("A");
        int hashCode = vertex.hashCode();
        assertThat(vertex.hashCode()).isEqualTo(hashCode);
        assertThat(vertex.hashCode()).isEqualTo(hashCode);
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void testHashCodeForEqualVertices(SimpleGraph graph) {
        var vertex1 = graph.vertex("A");
        var vertex2 = new UndirectedEventGraph().vertex("A");
        assertEquals(vertex1, vertex2);
        assertThat(vertex1.hashCode()).isEqualTo(vertex2.hashCode());
    }
}
