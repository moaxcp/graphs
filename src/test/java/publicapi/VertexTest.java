package publicapi;

import static com.github.moaxcp.graphs.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.github.moaxcp.graphs.SimpleGraph;
import testframework.*;

public class VertexTest {

    @SimpleGraphs
    void inheritedDefault(SimpleGraph<String> graph) {
        graph.vertex("A");
        assertThat(graph).hasVertex("A").withInherited().isEmpty();
    }

    @SimpleGraphs
    void inheritedWithProperty(SimpleGraph<String> graph) {
        graph.vertex("A");
        graph.vertexProperty("color", "blue");
        assertThat(graph).hasVertex("A").withInherited().containsExactly("color", "blue");
    }

    @SimpleGraphs
    void localWithProperty(SimpleGraph<String> graph) {
        graph.vertex("A").property("color", "blue");
        assertThat(graph).hasVertex("A").withLocal().containsExactly("color", "blue");
    }

    @SimpleGraphs
    void getPropertyInherited(SimpleGraph<String> graph) {
        graph.vertexProperty("color", "blue");
        graph.vertex("A");
        assertThat(graph).hasVertex("A").withProperty("color").hasValue("blue");
    }

    @SimpleGraphs
    void getPropertyLocal(SimpleGraph<String> graph) {
        graph.vertex("A").property("color", "blue");
        assertThat(graph).hasVertex("A").withProperty("color").hasValue("blue");
    }

    @SimpleGraphs
    void getPropertyDefault(SimpleGraph<String> graph) {
        graph.vertex("A");
        assertThat(graph).hasVertex("A").withProperty("color").isEmpty();
    }

    @SimpleGraphs
    void setPropertyNullName(SimpleGraph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.vertex("A").setProperty(null, null));
        assertThat(thrown).hasMessage("name must not be null.");
    }

    @SimpleGraphs
    void setPropertyNullValue(SimpleGraph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.vertex("A").setProperty("", null));
        assertThat(thrown).hasMessage("value must not be null.");
    }

    @SimpleGraphs
    void setPropertyEmptyName(SimpleGraph<String> graph) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.vertex("A").setProperty("", ""));
        assertThat(thrown).hasMessage("name must not be empty.");
    }

    @SimpleGraphs
    void setProperty(SimpleGraph<String> graph) {
        graph.vertex("A").setProperty("name", "value");
        assertThat(graph).hasVertex("A").withProperty("name").hasValue("value");
    }

    @SimpleGraphs
    void property(SimpleGraph<String> graph) {
        graph.vertex("A").property("name", "value");
        assertThat(graph).hasVertex("A").withProperty("name").hasValue("value");
    }

    @SimpleGraphs
    void removePropertyNullName(SimpleGraph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.vertex("A").removeProperty(null));
        assertThat(thrown).hasMessage("name must not be null.");
    }

    @SimpleGraphs
    void removePropertyNameMissing(SimpleGraph<String> graph) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.vertex("A").removeProperty("name"));
        assertThat(thrown).hasMessage("element does not contain property named 'name'.");
    }

    @SimpleGraphs
    void removeProperty(SimpleGraph<String> graph) {
        graph.vertex("A").property("name", "value");
        var result = graph.vertex("A").removeProperty("name");
        assertThat(graph).hasVertex("A").withProperty("name").isEmpty();
        assertThat(result).isSameAs(graph.vertex("A"));
    }

    @SimpleGraphs
    void testSetId(SimpleGraph<String> graph) {
        var a = graph.vertex("id");
        var from = graph.edge("id", "b");
        var to = graph.edge("c", "id");
        a.setId("a");

        assertThat(a.getId()).isEqualTo("a");
        assertThat(from.getFrom()).isEqualTo("a");
        assertThat(to.getTo()).isEqualTo("a");
    }

    @SimpleGraphs
    void aVoid(SimpleGraph<String> graph) {
        var vertex = graph.vertex("A").id("B");
        assertThat(vertex).hasId("B");
        assertThat(vertex).isSameAs(graph.vertex("B"));
    }

    @SimpleGraphs
    void testToString(SimpleGraph<String> graph) {
        var vertex = graph.vertex("id");
        vertex.setProperty("key", "value");
        assertThat(vertex.toString()).isEqualTo("Vertex 'id' {key=value}");
    }

    @SimpleGraphs
    void testAdjacentEdges(SimpleGraph<String> graph) {
        graph.edge("A", "B");
        graph.edge("A", "C");
        graph.edge("Z", "Y");

        var vertex = graph.vertex("A");

        var edges = vertex.adjacentEdges();
        assertThat(edges).hasSize(2);
        for(var edge : edges) {
            assertThat(edge.endpoints()).contains("A");
        }
    }

    @SimpleGraphs
    void testConnectsTo(SimpleGraph<String> graph) {
        var vertex = graph.vertex("A")
                .connectsTo("B");
        assertThat(graph).hasVertex("B");
        assertThat(graph).hasEdge("A", "B");
    }

    @SimpleGraphs
    void testConnectsFrom(SimpleGraph<String> graph) {
        var vertex = graph.vertex("A")
                .connectsFrom("B");
        assertThat(graph).hasVertex("B");
        assertThat(graph).hasEdge("B", "A");
        var optional = graph.findEdge("B", "A");
        assertThat(optional).isPresent();
        var edge = optional.orElse(null);
        assertThat(edge).hasFromThat().isEqualTo("B");
        assertThat(edge).hasToThat().isEqualTo("A");
        assertThat(vertex.getId()).isEqualTo("A");
    }

    @SimpleGraphs
    void testEdgeTo(SimpleGraph<String> graph) {
        var edge = graph.vertex("A")
                .edgeTo("B");
        assertThat(graph).hasVertex("B");
        assertThat(graph).hasEdge("A", "B");
        assertThat(edge).hasFromThat().isEqualTo("A");
        assertThat(edge).hasToThat().isEqualTo("B");
    }

    @SimpleGraphs
    void testEdgeFrom(SimpleGraph<String> graph) {
        var edge = graph.vertex("A")
                .edgeFrom("B");
        assertThat(graph).hasVertex("B");
        assertThat(graph).hasEdge("B", "A");
        assertThat(edge).hasFromThat().isEqualTo("B");
        assertThat(edge).hasToThat().isEqualTo("A");
    }

    @SimpleGraphs
    void toVertex(SimpleGraph<String> graph) {
        var vertex = graph.vertex("A").toVertex("B");
        assertThat(vertex).hasId("B");
        assertThat(graph).hasEdge("A", "B");
    }

    @SimpleGraphs
    void fromVertex(SimpleGraph<String> graph) {
        var vertex = graph.vertex("B").fromVertex("A");
        assertThat(vertex).hasId("A");
        assertThat(graph).hasEdge("A", "B");
    }

    @SimpleGraphs
    void inEdges(SimpleGraph<String> graph) {
        var edge1 = graph.edge("B", "A");
        var edge2 = graph.edge("C", "A");
        var edge3 = graph.edge("D", "A");
        var result = graph.vertex("A").inEdges();

        assertThat(result).containsExactly(edge1, edge2, edge3);
    }

    @SimpleGraphs
    void outEdges(SimpleGraph<String> graph) {
        var edge1 = graph.edge("A", "B");
        var edge2 = graph.edge("A", "C");
        var edge3 = graph.edge("A", "D");
        var result = graph.vertex("A").outEdges();

        assertThat(result).containsExactly(edge1, edge2, edge3);
    }

    @UndirectedSimpleGraphs
    void undirectedTraverseEdges(SimpleGraph<String> graph) {
        graph.edge("A", "B");
        graph.edge("A", "C");
        graph.edge("Z", "Y");

        var vertex = graph.vertex("A");

        var edges = vertex.traverseEdges();
        assertThat(edges).hasSize(2);
        for(var edge : edges) {
            assertThat(edge.endpoints()).contains("A");
        }
    }

    @DirectedSimpleGraphs
    void directedTraverseEdges(SimpleGraph<String> graph) {
        graph.edge("A", "B");
        graph.edge("A", "C");
        graph.edge("Z", "Y");

        var vertex = graph.vertex("A");

        var edges = vertex.traverseEdges();
        assertThat(edges).hasSize(2);
        for(var edge : edges) {
            assertThat(edge).hasFromThat().isEqualTo("A");
        }
    }

    @SimpleGraphs
    void equalsSameVertex(SimpleGraph<String> graph) {
        var vertex = graph.vertex("A");
        assertThat(vertex.equals(vertex)).isTrue();
    }

    @SimpleGraphs
    void equalsNotSameObject(SimpleGraph<String> graph) {
        var vertex = graph.vertex("A");
        assertThat(vertex.equals(1)).isFalse();
    }

    @SimpleGraphs
    void notEqualsVertex(SimpleGraph<String> graph) {
        var vertex1 = graph.vertex("A");
        var vertex2 = graph.vertex("B");
        assertThat(vertex1.equals(vertex2)).isFalse();
    }
}
