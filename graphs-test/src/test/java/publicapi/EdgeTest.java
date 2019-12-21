package publicapi;

import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.UndirectedGraph;
import com.github.moaxcp.graphs.testframework.SimpleGraphs;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import java.util.Map;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EdgeTest {
    @SimpleGraphs
    void setId(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        edge.setId("id");
        assertThat(graph).hasEdge("A", "B").hasIdThat().hasValue("id");
        assertThat(graph).hasEdge("id").isSameInstanceAs(edge);
    }

    @SimpleGraphs
    void changeId(Graph<String> graph) {
        var edge = graph.getEdge("A", "B").id("id");
        edge.setId("id2");
        assertThat(graph).hasEdge("id2").isSameInstanceAs(edge);
        assertThat(graph).hasEdge("A", "B").hasIdThat().hasValue("id2");
        assertThat(graph).hasEdge("id2").hasIdThat().hasValue("id2");
        assertThat(graph).hasNoEdge("id");
    }

    @SimpleGraphs
    void setIdNullNoId(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        edge.setId(null);
        assertThat(edge).hasNoId();
    }

    @SimpleGraphs
    void setIdNullRemovesId(Graph<String> graph) {
        var edge = graph.getEdge("A", "to").id("id");
        edge.setId(null);
        assertThat(edge).hasNoId();
        assertThat(graph).hasNoEdge("id");
    }

    @SimpleGraphs
    void id(Graph<String> graph) {
        var edge = graph.getEdge("A", "B").id("id");
        assertThat(graph).hasEdge("A", "B").hasIdThat().hasValue("id");
        assertThat(graph).hasEdge("id").isSameInstanceAs(edge);
    }

    @SimpleGraphs
    void setFrom(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        edge.setFrom("C");
        assertThat(graph).hasVertex("C");
        assertThat(edge).hasFromThat().isEqualTo("C");
    }

    @SimpleGraphs
    void setFromNull(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        Throwable thrown = assertThrows(NullPointerException.class, () -> edge.setFrom(null));
        assertThat(thrown).hasMessageThat().isEqualTo("from must not be null.");
    }

    @SimpleGraphs
    void setFromCreatesNewVertex(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        edge.setFrom("C");
        assertThat(graph).hasVertex("C");
    }

    @SimpleGraphs
    void setFromChangesChangesAdjacentEdges(Graph<String> graph) {
        var edge = graph.getEdge("id", "B");
        edge.setFrom("A");
        var inEdges = edge.fromVertex().adjacentEdges();
        assertThat(inEdges).containsExactly(edge);
    }

    @SimpleGraphs
    void setFromChangesChangesInEdges(Graph<String> graph) {
        var edge = graph.getEdge("id", "B");
        edge.setFrom("A");
        var inEdges = edge.toVertex().inEdges();
        assertThat(inEdges).containsExactly(edge);
    }

    @SimpleGraphs
    void setFromChangesChangesOutEdges(Graph<String> graph) {
        var edge = graph.getEdge("A", "id");
        edge.setTo("A");
        var outEdges = edge.fromVertex().outEdges();
        assertThat(outEdges).containsExactly(edge);
    }

    @SimpleGraphs
    void from(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        var result = edge.from("C");
        assertThat(edge).hasFromThat().isEqualTo("C");
        assertThat(result).isSameInstanceAs(edge);
    }

    @SimpleGraphs
    void fromGet(Graph<String> graph) {
        assertThat(graph.getEdge("A", "B").from()).isEqualTo("A");
    }

    @SimpleGraphs
    void setTo(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        edge.setTo("C");
        assertThat(graph).hasVertex("C");
        assertThat(edge).hasToThat().isEqualTo("C");
    }

    @SimpleGraphs
    void setToNull(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        Throwable thrown = assertThrows(NullPointerException.class, () -> edge.setTo(null));
        assertThat(thrown).hasMessageThat().isEqualTo("to must not be null.");
    }

    @SimpleGraphs
    void setToCreatesNewVertex(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        edge.setTo("C");
        assertThat(graph).hasVertex("C");
    }

    @SimpleGraphs
    void setToChangesChangesAdjacentEdges(Graph<String> graph) {
        var edge = graph.getEdge("A", "id");
        edge.setTo("B");
        var adjacentEdges = edge.toVertex().adjacentEdges();
        assertThat(adjacentEdges).containsExactly(edge);
    }

    @SimpleGraphs
    void setToChangesChangesInEdges(Graph<String> graph) {
        var edge = graph.getEdge("A", "id");
        edge.setTo("B");
        var inEdges = edge.toVertex().inEdges();
        assertThat(inEdges).containsExactly(edge);
    }

    @SimpleGraphs
    void setToChangesChangesOutEdges(Graph<String> graph) {
        var edge = graph.getEdge("A", "id");
        edge.setTo("A");
        var outEdges = edge.toVertex().inEdges();
        assertThat(outEdges).containsExactly(edge);
    }

    @SimpleGraphs
    void to(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        var result = edge.to("C");
        assertThat(edge).hasToThat().isEqualTo("C");
        assertThat(result).isSameInstanceAs(edge);
    }

    @SimpleGraphs
    void toGet(Graph<String> graph) {
        assertThat(graph.getEdge("A", "B").to()).isEqualTo("B");
    }

    @SimpleGraphs
    void fromVertex(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        var vertex = graph.findVertex("A").get();
        assertThat(edge.fromVertex()).isEqualTo(vertex);
    }

    @SimpleGraphs
    void toVertex(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        var vertex = graph.findVertex("B").get();
        assertThat(edge.toVertex()).isEqualTo(vertex);
    }

    @SimpleGraphs
    void setProperty(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        edge.setProperty("key", "value");
        assertThat(edge).withProperty("key").hasValue("value");
    }

    @SimpleGraphs
    void setPropertyUpdateToNull(Graph<String> graph) {
        graph.getEdge("A", "B").setProperty("name", "value");
        graph.getEdge("A", "B").setProperty("name", null);
        assertThat(graph).hasEdge("A", "B").withProperty("name").isEmpty();
    }

    @SimpleGraphs
    void property(Graph<String> graph) {
        graph.getEdge("A", "B").property("name1", "value1");
        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1");
    }

    @SimpleGraphs
    void property2(Graph<String> graph) {
        graph.getEdge("A", "B").property("name1", "value1", "name2", "value2");
        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2");
    }

    @SimpleGraphs
    void property3(Graph<String> graph) {
        graph.getEdge("A", "B").property("name1", "value1", "name2", "value2", "name3", "value3");
        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3");
    }

    @SimpleGraphs
    void property4(Graph<String> graph) {
        graph.getEdge("A", "B").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
    }

    @SimpleGraphs
    void property5(Graph<String> graph) {
        graph.getEdge("A", "B").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
    }

    @SimpleGraphs
    void property6(Graph<String> graph) {
        graph.getEdge("A", "B").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
    }

    @SimpleGraphs
    void property7(Graph<String> graph) {
        graph.getEdge("A", "B").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
    }

    @SimpleGraphs
    void property8(Graph<String> graph) {
        graph.getEdge("A", "B").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
    }

    @SimpleGraphs
    void property9(Graph<String> graph) {
        graph.getEdge("A", "B").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
    }

    @SimpleGraphs
    void property10(Graph<String> graph) {
        graph.getEdge("A", "B").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
    }

    @SimpleGraphs
    void propertyWithMap(Graph<String> graph) {
        graph.getEdge("A", "B").property(Map.of("name", "value"));
        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("from", "A", "to", "B", "name", "value");
    }

    @SimpleGraphs
    void propertyUpdateToNull(Graph<String> graph) {
        graph.getEdge("A", "B").property("name", "value");
        graph.getEdge("A", "B").property("name", null);
        assertThat(graph).hasEdge("A", "B").withProperty("name").isEmpty();
    }

    @SimpleGraphs
    void propertySetIdFails(Graph<String> graph) {
        var exception = assertThrows(IllegalArgumentException.class, () -> graph.getEdge("A", "B").property("id", "edge"));
        assertThat(exception).hasMessageThat().isEqualTo("id cannot be set as a property.");
    }

    @SimpleGraphs
    void removePropertyIdFails(Graph<String> graph) {
        var exception = assertThrows(IllegalArgumentException.class, () -> graph.getEdge("A", "B").id("edge").removeProperty("id"));
        assertThat(exception).hasMessageThat().isEqualTo("id cannot be set as a property.");
    }

    @SimpleGraphs
    void propertySetFromFails(Graph<String> graph) {
        var exception = assertThrows(IllegalArgumentException.class, () -> graph.getEdge("A", "B").property("from", "C"));
        assertThat(exception).hasMessageThat().isEqualTo("from cannot be set as a property.");
    }

    @SimpleGraphs
    void removePropertyFromFails(Graph<String> graph) {
        var exception = assertThrows(IllegalArgumentException.class, () -> graph.getEdge("A", "B").removeProperty("from"));
        assertThat(exception).hasMessageThat().isEqualTo("from cannot be set as a property.");
    }

    @SimpleGraphs
    void propertySetToFails(Graph<String> graph) {
        var exception = assertThrows(IllegalArgumentException.class, () -> graph.getEdge("A", "B").property("to", "C"));
        assertThat(exception).hasMessageThat().isEqualTo("to cannot be set as a property.");
    }

    @SimpleGraphs
    void removePropertyToFails(Graph<String> graph) {
        var exception = assertThrows(IllegalArgumentException.class, () -> graph.getEdge("A", "B").removeProperty("to"));
        assertThat(exception).hasMessageThat().isEqualTo("to cannot be set as a property.");
    }

    @SimpleGraphs
    void removeProperty(Graph<String> graph) {
        var edge = graph.getEdge("A", "B").property("name", "value");
        edge.removeProperty("name");
        assertThat(edge).withProperty("name").isEmpty();
    }

    @SimpleGraphs
    void testEquals(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        EqualsVerifier.forClass(edge.getClass()).suppress(Warning.NONFINAL_FIELDS, Warning.NULL_FIELDS).verify();
    }

    @SimpleGraphs
    void testEqualsDifferentInherited(Graph<String> graph) {
        var other = new UndirectedGraph<String>();
        other.edgeProperty("name", "value");
        var edge1 = graph.getEdge("A", "B");
        var edge2 = other.getEdge("A", "B");

        assertThat(edge1.equals(edge2)).isFalse();
    }
}
