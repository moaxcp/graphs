package publicapi;

import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.testframework.SimpleGraphs;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import java.util.Map;

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
        graph.getVertex("A").property("color", "blue");
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
        graph.getVertex("A").property("color", "blue");
        assertThat(graph).hasVertex("A").withProperty("color").hasValue("blue");
    }

    @SimpleGraphs
    void getPropertyDefault(Graph<String> graph) {
        graph.vertex("A");
        assertThat(graph).hasVertex("A").withProperty("color").isEmpty();
    }

    @SimpleGraphs
    void setPropertyNullName(Graph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.getVertex("A").setProperty(null, null));
        assertThat(thrown).hasMessage("name must not be null.");
    }

    @SimpleGraphs
    void setPropertyNullValue(Graph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.getVertex("A").setProperty("", null));
        assertThat(thrown).hasMessage("value must not be null.");
    }

    @SimpleGraphs
    void setPropertyEmptyName(Graph<String> graph) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.getVertex("A").setProperty("", ""));
        assertThat(thrown).hasMessage("name must not be empty.");
    }

    @SimpleGraphs
    void setProperty(Graph<String> graph) {
        graph.getVertex("A").setProperty("name", "value");
        assertThat(graph).hasVertex("A").withProperty("name").hasValue("value");
    }

    @SimpleGraphs
    void property(Graph<String> graph) {
        graph.getVertex("A").property("name", "value");
        assertThat(graph).hasVertex("A").withProperty("name").hasValue("value");
    }

    @SimpleGraphs
    void property2(Graph<String> graph) {
        graph.getVertex("A").property("name1", "value1", "name2", "value2");
        assertThat(graph).hasVertex("A").withLocal().containsExactly("name1", "value1", "name2", "value2");
    }

    @SimpleGraphs
    void property3(Graph<String> graph) {
        graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3");
        assertThat(graph).hasVertex("A").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3");
    }

    @SimpleGraphs
    void property4(Graph<String> graph) {
        graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
        assertThat(graph).hasVertex("A").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
    }

    @SimpleGraphs
    void property5(Graph<String> graph) {
        graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
        assertThat(graph).hasVertex("A").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
    }

    @SimpleGraphs
    void property6(Graph<String> graph) {
        graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
        assertThat(graph).hasVertex("A").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
    }

    @SimpleGraphs
    void property7(Graph<String> graph) {
        graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
        assertThat(graph).hasVertex("A").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
    }

    @SimpleGraphs
    void property8(Graph<String> graph) {
        graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
        assertThat(graph).hasVertex("A").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
    }

    @SimpleGraphs
    void property9(Graph<String> graph) {
        graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
        assertThat(graph).hasVertex("A").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
    }

    @SimpleGraphs
    void property10(Graph<String> graph) {
        graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
        assertThat(graph).hasVertex("A").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
    }

    @SimpleGraphs
    void propertyWithMap(Graph<String> graph) {
        graph.getVertex("A").property(Map.of("name", "value"));
        assertThat(graph).hasVertex("A").withProperty("name").hasValue("value");
    }

    @SimpleGraphs
    void removePropertyNullName(Graph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.getVertex("A").removeProperty(null));
        assertThat(thrown).hasMessageThat().isEqualTo("name must not be null.");
    }

    @SimpleGraphs
    void removePropertyNameMissing(Graph<String> graph) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.getVertex("A").removeProperty("name"));
        assertThat(thrown).hasMessageThat().isEqualTo("element does not contain property named 'name'.");
    }

    @SimpleGraphs
    void removeProperty(Graph<String> graph) {
        graph.getVertex("A").property("name", "value");
        var result = graph.getVertex("A").removeProperty("name");
        assertThat(graph).hasVertex("A").withProperty("name").isEmpty();
        assertThat(result).isSameAs(graph.getVertex("A"));
    }

    @SimpleGraphs
    void testSetId(Graph<String> graph) {
        var a = graph.getVertex("id");
        var from = graph.getEdge("id", "b");
        var to = graph.getEdge("c", "id");
        a.setId("a");

        assertThat(a.getId()).isEqualTo("a");
        assertThat(from.getFrom()).isEqualTo("a");
        assertThat(to.getTo()).isEqualTo("a");
    }

    @SimpleGraphs
    void testSetIdAlreadyExists(Graph<String> graph) {
        graph.getVertex("a").property("key", "value");
        var vertex = graph.getVertex("id");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> vertex.id("a"));
        assertThat(ex).hasMessageThat().isEqualTo("vertex with id a already exists.");
    }

    @SimpleGraphs
    void edgeAfterSetId(Graph<String> graph) {
        graph.edge("id", "b");
        graph.edge("c", "id");
        graph.getVertex("id").setId("a");

        assertThat(graph).hasEdge("a", "b");
        assertThat(graph).hasEdge("c", "a");
    }

    @SimpleGraphs
    void testId(Graph<String> graph) {
        var vertex = graph.getVertex("A").id("B");
        assertThat(vertex).hasId("B");
        assertThat(vertex).isSameAs(graph.getVertex("B"));
    }

    @SimpleGraphs
    void testAdjacentEdges(Graph<String> graph) {
        graph.edge("A", "B");
        graph.edge("A", "C");
        graph.edge("Z", "Y");

        var vertex = graph.getVertex("A");

        var edges = vertex.adjacentEdges();
        assertThat(edges).hasSize(2);
        for(var edge : edges) {
            assertThat(edge.endpoints()).contains("A");
        }
    }

    @SimpleGraphs
    void adjacentEdgesEmpty(Graph<String> graph) {
        assertThat(graph.getVertex("A").adjacentEdges()).isEmpty();
    }

    @SimpleGraphs
    void adjacentEdgesAfterSetId(Graph<String> graph) {
        graph.edge("id", "B");
        graph.edge("id", "C");
        graph.edge("D", "id");
        graph.edge("Z", "Y");

        var vertex = graph.getVertex("id").id("A");

        var edges = vertex.adjacentEdges();
        assertThat(edges).hasSize(3);
        for(var edge : edges) {
            assertThat(edge.endpoints()).contains("A");
        }
    }

    @SimpleGraphs
    void testConnectsTo(Graph<String> graph) {
        var vertex = graph.getVertex("A")
                .connectsTo("B");
        assertThat(graph).hasVertex("B");
        assertThat(graph).hasEdge("A", "B");
    }

    @SimpleGraphs
    void testConnectsFrom(Graph<String> graph) {
        var vertex = graph.getVertex("A")
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
        var edge1 = graph.getEdge("B", "A");
        var edge2 = graph.getEdge("C", "A");
        var edge3 = graph.getEdge("D", "A");
        var result = graph.getVertex("A").inEdges();

        assertThat(result).containsExactly(edge1, edge2, edge3);
    }

    @SimpleGraphs
    void inEdgesAfterSetId(Graph<String> graph) {
        graph.edge("B", "id");
        graph.edge("C", "id");
        graph.edge("D", "id");
        graph.edge("Z", "Y");

        var vertex = graph.getVertex("id").id("A");

        var edges = vertex.adjacentEdges();
        assertThat(edges).hasSize(3);
        for(var edge : edges) {
            assertThat(edge.to()).isEqualTo("A");
        }
    }

    @SimpleGraphs
    void inEdgesEmpty(Graph<String> graph) {
        assertThat(graph.getVertex("A").inEdges()).isEmpty();
    }

    @SimpleGraphs
    void outEdges(Graph<String> graph) {
        var edge1 = graph.getEdge("A", "B");
        var edge2 = graph.getEdge("A", "C");
        var edge3 = graph.getEdge("A", "D");
        var result = graph.getVertex("A").outEdges();

        assertThat(result).containsExactly(edge1, edge2, edge3);
    }

    @SimpleGraphs
    void outEdgesAfterSetId(Graph<String> graph) {
        graph.getEdge("id", "B");
        graph.getEdge("id", "C");
        graph.getEdge("id", "D");
        graph.getEdge("Z", "Y");

        var vertex = graph.getVertex("id").id("A");

        var edges = vertex.adjacentEdges();
        assertThat(edges).hasSize(3);
        for(var edge : edges) {
            assertThat(edge.from()).isEqualTo("A");
        }
    }

    @SimpleGraphs
    void outEdgesEmpty(Graph<String> graph) {
        assertThat(graph.getVertex("A").outEdges()).isEmpty();
    }

    @SimpleGraphs
    void testEquals(Graph<String> graph) {
        var vertex = graph.getVertex("A");
        EqualsVerifier.forClass(vertex.getClass()).suppress(Warning.NONFINAL_FIELDS, Warning.NULL_FIELDS).verify();
    }
}
