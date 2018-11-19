package publicapi;

import static com.google.common.truth.Truth.assertThat;
import com.github.moaxcp.graphs.SimpleGraph;
import testframework.SimpleGraphs;

public class VertexTest {

    @SimpleGraphs
    void testSetId(SimpleGraph graph) {
        var a = graph.vertex("id");
        var from = graph.edge("id", "b");
        var to = graph.edge("c", "id");
        a.setId("a");

        assertThat(a.getId()).isEqualTo("a");
        assertThat(from.getFrom()).isEqualTo("a");
        assertThat(to.getTo()).isEqualTo("a");
    }

    @SimpleGraphs
    void testToString(SimpleGraph graph) {
        var vertex = graph.vertex("id");
        vertex.setProperty("key", "value");
        assertThat(vertex.toString()).isEqualTo("Vertex 'id' {id=id, key=value}");
    }

    @SimpleGraphs
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

    @SimpleGraphs
    void testConnectsTo(SimpleGraph graph) {
        var vertex = graph.vertex("A")
                .connectsTo("B");
        assertThat(vertex.getId()).isEqualTo("A");
        assertThat(graph.getVertices().keySet()).containsExactly("A", "B");
        assertThat(graph.getEdges()).hasSize(1);
        assertThat(graph.getEdges().iterator().next().getLocal()).containsExactly("from", "A", "to", "B");
    }

    @SimpleGraphs
    void testConnectsFrom(SimpleGraph graph) {
        var vertex = graph.vertex("A")
                .connectsFrom("B");
        assertThat(vertex.getId()).isEqualTo("A");
        assertThat(graph.getVertices().keySet()).containsExactly("A", "B");
        assertThat(graph.getEdges()).hasSize(1);
        assertThat(graph.getEdges().iterator().next().getLocal()).containsExactly("from", "B", "to", "A");
    }

    @SimpleGraphs
    void testEdgeTo(SimpleGraph graph) {
        var edge = graph.vertex("A")
                .edgeTo("B");
        assertThat(edge.getLocal()).containsExactly("from", "A", "to", "B");
        assertThat(graph.getEdges().iterator().next()).isEqualTo(edge);
        assertThat(graph.getVertices().keySet()).containsExactly("A", "B");
    }

    @SimpleGraphs
    void testEdgeFrom(SimpleGraph graph) {
        var edge = graph.vertex("A")
                .edgeFrom("B");
        assertThat(edge.getLocal()).containsExactly("from", "B", "to", "A");
        assertThat(graph.getEdges().iterator().next()).isEqualTo(edge);
        assertThat(graph.getVertices().keySet()).containsExactly("A", "B");
    }
}
