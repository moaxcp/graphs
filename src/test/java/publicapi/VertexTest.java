package publicapi;

import static com.github.moaxcp.graphs.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.github.moaxcp.graphs.SimpleGraph;
import testframework.SimpleGraphs;

public class VertexTest {

    @SimpleGraphs
    void inheritedDefault(SimpleGraph graph) {
        graph.vertex("A");
        assertThat(graph).hasVertex("A").withInherited().isEmpty();
    }

    @SimpleGraphs
    void inheritedWithProperty(SimpleGraph graph) {
        graph.vertex("A");
        graph.vertexProperty("color", "blue");
        assertThat(graph).hasVertex("A").withInherited().containsExactly("color", "blue");
    }

    @SimpleGraphs
    void localDefault(SimpleGraph graph) {
        graph.vertex("A");
        assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A");
    }

    @SimpleGraphs
    void localWithProperty(SimpleGraph graph) {
        graph.vertex("A").property("color", "blue");
        assertThat(graph).hasVertex("A").withLocal().containsExactly("id", "A", "color", "blue");
    }

    @SimpleGraphs
    void getPropertyInherited(SimpleGraph graph) {
        graph.vertexProperty("color", "blue");
        graph.vertex("A");
        assertThat(graph).hasVertex("A").withProperty("color").hasValue("blue");
    }

    @SimpleGraphs
    void getPropertyLocal(SimpleGraph graph) {
        graph.vertex("A").property("color", "blue");
        assertThat(graph).hasVertex("A").withProperty("color").hasValue("blue");
    }

    @SimpleGraphs
    void getPropertyDefault(SimpleGraph graph) {
        graph.vertex("A");
        assertThat(graph).hasVertex("A").withProperty("color").isEmpty();
    }

    @SimpleGraphs
    void setPropertyNullName(SimpleGraph graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.vertex("A").setProperty(null, null));
        assertThat(thrown).hasMessage("name must not be null.");
    }

    @SimpleGraphs
    void setPropertyNullValue(SimpleGraph graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.vertex("A").setProperty("", null));
        assertThat(thrown).hasMessage("value must not be null.");
    }

    @SimpleGraphs
    void setPropertyEmptyName(SimpleGraph graph) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.vertex("A").setProperty("", ""));
        assertThat(thrown).hasMessage("name must not be empty.");
    }

    @SimpleGraphs
    void setProperty(SimpleGraph graph) {
        graph.vertex("A").setProperty("name", "value");
        assertThat(graph).hasVertex("A").withProperty("name").hasValue("value");
    }

    @SimpleGraphs
    void propertyId(SimpleGraph graph) {
        var vertex = graph.vertex("A");
        var result = graph.vertex("A").property("id", "B");
        assertThat(graph).hasVertex("B");
        assertThat(result).isSameAs(vertex);
    }

    @SimpleGraphs
    void property(SimpleGraph graph) {
        graph.vertex("A").property("name", "value");
        assertThat(graph).hasVertex("A").withProperty("name").hasValue("value");
    }

    @SimpleGraphs
    void removePropertyNullName(SimpleGraph graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.vertex("A").removeProperty(null));
        assertThat(thrown).hasMessage("name must not be null.");
    }

    @SimpleGraphs
    void removePropertyNameMissing(SimpleGraph graph) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.vertex("A").removeProperty("name"));
        assertThat(thrown).hasMessage("element does not contain property named 'name'.");
    }

    @SimpleGraphs
    void removeProperty(SimpleGraph graph) {
        graph.vertex("A").property("name", "value");
        var result = graph.vertex("A").removeProperty("name");
        assertThat(graph).hasVertex("A").withProperty("name").isEmpty();
        assertThat(result).isSameAs(graph.vertex("A"));
    }

    @SimpleGraphs
    void removePropertyId(SimpleGraph graph) {
        graph.vertex("A");
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.vertex("A").removeProperty("id"));
        assertThat(thrown).hasMessage("id cannot be removed.");
    }

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
    void aVoid(SimpleGraph graph) {
        var vertex = graph.vertex("A").id("B");
        assertThat(vertex).hasId("B");
        assertThat(vertex).isSameAs(graph.vertex("B"));
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
            assertThat(edge.local().values()).contains("A");
        }
    }

    @SimpleGraphs
    void testConnectsTo(SimpleGraph graph) {
        var vertex = graph.vertex("A")
                .connectsTo("B");
        assertThat(vertex.getId()).isEqualTo("A");
        assertThat(graph.getVertices().keySet()).containsExactly("A", "B");
        assertThat(graph.getEdges()).hasSize(1);
        assertThat(graph.getEdges().iterator().next().local()).containsExactly("from", "A", "to", "B");
    }

    @SimpleGraphs
    void testConnectsFrom(SimpleGraph graph) {
        var vertex = graph.vertex("A")
                .connectsFrom("B");
        assertThat(vertex.getId()).isEqualTo("A");
        assertThat(graph.getVertices().keySet()).containsExactly("A", "B");
        assertThat(graph.getEdges()).hasSize(1);
        assertThat(graph.getEdges().iterator().next().local()).containsExactly("from", "B", "to", "A");
    }

    @SimpleGraphs
    void testEdgeTo(SimpleGraph graph) {
        var edge = graph.vertex("A")
                .edgeTo("B");
        assertThat(edge.local()).containsExactly("from", "A", "to", "B");
        assertThat(graph.getEdges().iterator().next()).isEqualTo(edge);
        assertThat(graph.getVertices().keySet()).containsExactly("A", "B");
    }

    @SimpleGraphs
    void testEdgeFrom(SimpleGraph graph) {
        var edge = graph.vertex("A")
                .edgeFrom("B");
        assertThat(edge.local()).containsExactly("from", "B", "to", "A");
        assertThat(graph.getEdges().iterator().next()).isEqualTo(edge);
        assertThat(graph.getVertices().keySet()).containsExactly("A", "B");
    }

    @SimpleGraphs
    void toVertex(SimpleGraph graph) {
        var vertex = graph.vertex("A").toVertex("B");
        assertThat(vertex).hasId("B");
        assertThat(graph).hasEdge("A", "B");
    }

    @SimpleGraphs
    void fromVertex(SimpleGraph graph) {
        var vertex = graph.vertex("B").fromVertex("A");
        assertThat(vertex).hasId("A");
        assertThat(graph).hasEdge("A", "B");
    }

    @SimpleGraphs
    void inEdges(SimpleGraph graph) {
        var edge1 = graph.edge("B", "A");
        var edge2 = graph.edge("C", "A");
        var edge3 = graph.edge("D", "A");
        var result = graph.vertex("A").inEdges();

        assertThat(result).containsExactly(edge1, edge2, edge3);
    }

    @SimpleGraphs
    void outEdges(SimpleGraph graph) {
        var edge1 = graph.edge("A", "B");
        var edge2 = graph.edge("A", "C");
        var edge3 = graph.edge("A", "D");
        var result = graph.vertex("A").outEdges();

        assertThat(result).containsExactly(edge1, edge2, edge3);
    }
}
