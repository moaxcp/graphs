package publicapi;

import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.testframework.SimpleGraphs;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VertexTest {

    @SimpleGraphs
    void inheritedDefault(Graph<String> graph) {
        graph.vertex("A");
        assertThat(graph).hasVertex("A").withInherited().isEmpty();
    }

    @SimpleGraphs
    void inheritedWithProperty(Graph<String> graph) {
        graph.vertex("A");
        graph.vertexProperty("color", "blue");
        assertThat(graph).hasVertex("A").withInherited().containsExactly("color", "blue");
    }

    @SimpleGraphs
    void localWithProperty(Graph<String> graph) {
        graph.vertex("A").property("color", "blue");
        assertThat(graph).hasVertex("A").withLocal().containsExactly("color", "blue");
    }

    @SimpleGraphs
    void getPropertyInherited(Graph<String> graph) {
        graph.vertexProperty("color", "blue");
        graph.vertex("A");
        assertThat(graph).hasVertex("A").withProperty("color").hasValue("blue");
    }

    @SimpleGraphs
    void getPropertyLocal(Graph<String> graph) {
        graph.vertex("A").property("color", "blue");
        assertThat(graph).hasVertex("A").withProperty("color").hasValue("blue");
    }

    @SimpleGraphs
    void getPropertyDefault(Graph<String> graph) {
        graph.vertex("A");
        assertThat(graph).hasVertex("A").withProperty("color").isEmpty();
    }

    @SimpleGraphs
    void setPropertyNullName(Graph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.vertex("A").setProperty(null, null));
        assertThat(thrown).hasMessage("name must not be null.");
    }

    @SimpleGraphs
    void setPropertyNullValue(Graph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.vertex("A").setProperty("", null));
        assertThat(thrown).hasMessage("value must not be null.");
    }

    @SimpleGraphs
    void setPropertyEmptyName(Graph<String> graph) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.vertex("A").setProperty("", ""));
        assertThat(thrown).hasMessage("name must not be empty.");
    }

    @SimpleGraphs
    void setProperty(Graph<String> graph) {
        graph.vertex("A").setProperty("name", "value");
        assertThat(graph).hasVertex("A").withProperty("name").hasValue("value");
    }

    @SimpleGraphs
    void property(Graph<String> graph) {
        graph.vertex("A").property("name", "value");
        assertThat(graph).hasVertex("A").withProperty("name").hasValue("value");
    }

    @SimpleGraphs
    void removePropertyNullName(Graph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.vertex("A").removeProperty(null));
        assertThat(thrown).hasMessage("name must not be null.");
    }

    @SimpleGraphs
    void removePropertyNameMissing(Graph<String> graph) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.vertex("A").removeProperty("name"));
        assertThat(thrown).hasMessage("element does not contain property named 'name'.");
    }

    @SimpleGraphs
    void removeProperty(Graph<String> graph) {
        graph.vertex("A").property("name", "value");
        var result = graph.vertex("A").removeProperty("name");
        assertThat(graph).hasVertex("A").withProperty("name").isEmpty();
        assertThat(result).isSameAs(graph.vertex("A"));
    }

    @SimpleGraphs
    void testSetId(Graph<String> graph) {
        var a = graph.vertex("id");
        var from = graph.edge("id", "b");
        var to = graph.edge("c", "id");
        a.setId("a");

        assertThat(a.getId()).isEqualTo("a");
        assertThat(from.getFrom()).isEqualTo("a");
        assertThat(to.getTo()).isEqualTo("a");
    }

    @SimpleGraphs
    void testSetIdAlreadyExists(Graph<String> graph) {
        graph.vertex("a").property("key", "value");
        var vertex = graph.vertex("id");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> vertex.id("a"));
        assertThat(ex).hasMessageThat().isEqualTo("vertex with id a already exists.");
    }

    @SimpleGraphs
    void edgeAfterSetId(Graph<String> graph) {
        graph.edge("id", "b");
        graph.edge("c", "id");
        graph.vertex("id").setId("a");

        assertThat(graph).hasEdge("a", "b");
        assertThat(graph).hasEdge("c", "a");
    }

    @SimpleGraphs
    void testId(Graph<String> graph) {
        var vertex = graph.vertex("A").id("B");
        assertThat(vertex).hasId("B");
        assertThat(vertex).isSameAs(graph.vertex("B"));
    }

    @SimpleGraphs
    void testAdjacentEdges(Graph<String> graph) {
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
    void adjacentEdgesEmpty(Graph<String> graph) {
        assertThat(graph.vertex("A").adjacentEdges()).isEmpty();
    }

    @SimpleGraphs
    void adjacentEdgesAfterSetId(Graph<String> graph) {
        graph.edge("id", "B");
        graph.edge("id", "C");
        graph.edge("D", "id");
        graph.edge("Z", "Y");

        var vertex = graph.vertex("id").id("A");

        var edges = vertex.adjacentEdges();
        assertThat(edges).hasSize(3);
        for(var edge : edges) {
            assertThat(edge.endpoints()).contains("A");
        }
    }

    @SimpleGraphs
    void testConnectsTo(Graph<String> graph) {
        var vertex = graph.vertex("A")
                .connectsTo("B");
        assertThat(graph).hasVertex("B");
        assertThat(graph).hasEdge("A", "B");
    }

    @SimpleGraphs
    void testConnectsFrom(Graph<String> graph) {
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
    void inEdges(Graph<String> graph) {
        var edge1 = graph.edge("B", "A");
        var edge2 = graph.edge("C", "A");
        var edge3 = graph.edge("D", "A");
        var result = graph.vertex("A").inEdges();

        assertThat(result).containsExactly(edge1, edge2, edge3);
    }

    @SimpleGraphs
    void inEdgesAfterSetId(Graph<String> graph) {
        graph.edge("B", "id");
        graph.edge("C", "id");
        graph.edge("D", "id");
        graph.edge("Z", "Y");

        var vertex = graph.vertex("id").id("A");

        var edges = vertex.adjacentEdges();
        assertThat(edges).hasSize(3);
        for(var edge : edges) {
            assertThat(edge.to()).isEqualTo("A");
        }
    }

    @SimpleGraphs
    void inEdgesEmpty(Graph<String> graph) {
        assertThat(graph.vertex("A").inEdges()).isEmpty();
    }

    @SimpleGraphs
    void outEdges(Graph<String> graph) {
        var edge1 = graph.edge("A", "B");
        var edge2 = graph.edge("A", "C");
        var edge3 = graph.edge("A", "D");
        var result = graph.vertex("A").outEdges();

        assertThat(result).containsExactly(edge1, edge2, edge3);
    }

    @SimpleGraphs
    void outEdgesAfterSetId(Graph<String> graph) {
        graph.edge("id", "B");
        graph.edge("id", "C");
        graph.edge("id", "D");
        graph.edge("Z", "Y");

        var vertex = graph.vertex("id").id("A");

        var edges = vertex.adjacentEdges();
        assertThat(edges).hasSize(3);
        for(var edge : edges) {
            assertThat(edge.from()).isEqualTo("A");
        }
    }

    @SimpleGraphs
    void outEdgesEmpty(Graph<String> graph) {
        assertThat(graph.vertex("A").outEdges()).isEmpty();
    }

    @SimpleGraphs
    void testEquals(Graph<String> graph) {
        var vertex = graph.vertex("A");
        EqualsVerifier.forClass(vertex.getClass()).suppress(Warning.NONFINAL_FIELDS, Warning.NULL_FIELDS).verify();
    }
}
