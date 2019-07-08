package publicapi;

import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.testframework.SimpleGraphs;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EdgeTest {
    @SimpleGraphs
    void setId(Graph<String> graph) {
        var edge = graph.edge("A", "B");
        edge.setId("id");
        assertThat(graph).hasEdge("A", "B").hasIdThat().hasValue("id");
        assertThat(graph).hasEdge("id").isSameAs(edge);
    }

    @SimpleGraphs
    void changeId(Graph<String> graph) {
        var edge = graph.edge("A", "B").id("id");
        edge.setId("id2");
        assertThat(graph).hasEdge("id2").isSameAs(edge);
        assertThat(graph).hasEdge("A", "B").hasIdThat().hasValue("id2");
        assertThat(graph).hasEdge("id2").hasIdThat().hasValue("id2");
        assertThat(graph).hasNoEdge("id");
    }

    @SimpleGraphs
    void setIdNullNoId(Graph<String> graph) {
        var edge = graph.edge("A", "B");
        edge.setId(null);
        assertThat(edge).hasNoId();
    }

    @SimpleGraphs
    void setIdNullRemovesId(Graph<String> graph) {
        var edge = graph.edge("A", "to").id("id");
        edge.setId(null);
        assertThat(edge).hasNoId();
        assertThat(graph).hasNoEdge("id");
    }

    @SimpleGraphs
    void id(Graph<String> graph) {
        var edge = graph.edge("A", "B").id("id");
        assertThat(graph).hasEdge("A", "B").hasIdThat().hasValue("id");
        assertThat(graph).hasEdge("id").isSameAs(edge);
    }

    @SimpleGraphs
    void setFrom(Graph<String> graph) {
        var edge = graph.edge("A", "B");
        edge.setFrom("C");
        assertThat(graph).hasVertex("C");
        assertThat(edge).hasFromThat().isEqualTo("C");
    }

    @SimpleGraphs
    void setFromNull(Graph<String> graph) {
        var edge = graph.edge("A", "B");
        Throwable thrown = assertThrows(NullPointerException.class, () -> edge.setFrom(null));
        assertThat(thrown).hasMessage("from must not be null.");
    }

    @SimpleGraphs
    void setFromCreatesNewVertex(Graph<String> graph) {
        var edge = graph.edge("A", "B");
        edge.setFrom("C");
        assertThat(graph).hasVertex("C");
    }

    @SimpleGraphs
    void setFromChangesChangesAdjacentEdges(Graph<String> graph) {
        var edge = graph.edge("id", "B");
        edge.setFrom("A");
        var inEdges = edge.fromVertex().adjacentEdges();
        assertThat(inEdges).containsExactly(edge);
    }

    @SimpleGraphs
    void setFromChangesChangesInEdges(Graph<String> graph) {
        var edge = graph.edge("id", "B");
        edge.setFrom("A");
        var inEdges = edge.toVertex().inEdges();
        assertThat(inEdges).containsExactly(edge);
    }

    @SimpleGraphs
    void setFromChangesChangesOutEdges(Graph<String> graph) {
        var edge = graph.edge("A", "id");
        edge.setTo("A");
        var outEdges = edge.fromVertex().outEdges();
        assertThat(outEdges).containsExactly(edge);
    }

    @SimpleGraphs
    void from(Graph<String> graph) {
        var edge = graph.edge("A", "B");
        var result = edge.from("C");
        assertThat(edge).hasFromThat().isEqualTo("C");
        assertThat(result).isSameAs(edge);
    }

    @SimpleGraphs
    void fromGet(Graph<String> graph) {
        assertThat(graph.edge("A", "B").from()).isEqualTo("A");
    }

    @SimpleGraphs
    void setTo(Graph<String> graph) {
        var edge = graph.edge("A", "B");
        edge.setTo("C");
        assertThat(graph).hasVertex("C");
        assertThat(edge).hasToThat().isEqualTo("C");
    }

    @SimpleGraphs
    void setToNull(Graph<String> graph) {
        var edge = graph.edge("A", "B");
        Throwable thrown = assertThrows(NullPointerException.class, () -> edge.setTo(null));
        assertThat(thrown).hasMessage("to must not be null.");
    }

    @SimpleGraphs
    void setToCreatesNewVertex(Graph<String> graph) {
        var edge = graph.edge("A", "B");
        edge.setTo("C");
        assertThat(graph).hasVertex("C");
    }

    @SimpleGraphs
    void setToChangesChangesAdjacentEdges(Graph<String> graph) {
        var edge = graph.edge("A", "id");
        edge.setTo("B");
        var adjacentEdges = edge.toVertex().adjacentEdges();
        assertThat(adjacentEdges).containsExactly(edge);
    }

    @SimpleGraphs
    void setToChangesChangesInEdges(Graph<String> graph) {
        var edge = graph.edge("A", "id");
        edge.setTo("B");
        var inEdges = edge.toVertex().inEdges();
        assertThat(inEdges).containsExactly(edge);
    }

    @SimpleGraphs
    void setToChangesChangesOutEdges(Graph<String> graph) {
        var edge = graph.edge("A", "id");
        edge.setTo("A");
        var outEdges = edge.toVertex().inEdges();
        assertThat(outEdges).containsExactly(edge);
    }

    @SimpleGraphs
    void to(Graph<String> graph) {
        var edge = graph.edge("A", "B");
        var result = edge.to("C");
        assertThat(edge).hasToThat().isEqualTo("C");
        assertThat(result).isSameAs(edge);
    }

    @SimpleGraphs
    void toGet(Graph<String> graph) {
        assertThat(graph.edge("A", "B").to()).isEqualTo("B");
    }

    @SimpleGraphs
    void fromVertex(Graph<String> graph) {
        var edge = graph.edge("A", "B");
        var vertex = graph.findVertex("A").get();
        assertThat(edge.fromVertex()).isEqualTo(vertex);
    }

    @SimpleGraphs
    void toVertex(Graph<String> graph) {
        var edge = graph.edge("A", "B");
        var vertex = graph.findVertex("B").get();
        assertThat(edge.toVertex()).isEqualTo(vertex);
    }

    @SimpleGraphs
    void setProperty(Graph<String> graph) {
        var edge = graph.edge("A", "B");
        edge.setProperty("key", "value");
        assertThat(edge).withProperty("key").hasValue("value");
    }

    @SimpleGraphs
    void removeProperty(Graph<String> graph) {
        var edge = graph.edge("A", "B").property("name", "value");
        edge.removeProperty("name");
        assertThat(edge).withProperty("name").isEmpty();
    }

    @SimpleGraphs
    void testEquals(Graph<String> graph) {
        var edge = graph.edge("A", "B");
        EqualsVerifier.forClass(edge.getClass()).suppress(Warning.NONFINAL_FIELDS, Warning.NULL_FIELDS).verify();
    }
}
