package publicapi;

import static com.github.moaxcp.graphs.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.github.moaxcp.graphs.Graph;
import nl.jqno.equalsverifier.*;
import testframework.*;

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
    void id(Graph graph) {
        var edge = graph.edge("A", "B").id("id");
        assertThat(graph).hasEdge("A", "B").hasIdThat().hasValue("id");
        assertThat(graph).hasEdge("id").isSameAs(edge);
    }

    @SimpleGraphs
    void setFrom(Graph graph) {
        var edge = graph.edge("A", "B");
        edge.setFrom("C");
        assertThat(graph).hasVertex("C");
        assertThat(edge).hasFromThat().isEqualTo("C");
    }

    @SimpleGraphs
    void setFromNull(Graph graph) {
        var edge = graph.edge("A", "B");
        Throwable thrown = assertThrows(NullPointerException.class, () -> edge.setFrom(null));
        assertThat(thrown).hasMessage("from must not be null.");
    }

    @SimpleGraphs
    void setFromCreatesNewVertex(Graph graph) {
        var edge = graph.edge("A", "B");
        edge.setFrom("C");
        assertThat(graph).hasVertex("C");
    }

    @SimpleGraphs
    void from(Graph graph) {
        var edge = graph.edge("A", "B");
        var result = edge.from("C");
        assertThat(edge).hasFromThat().isEqualTo("C");
        assertThat(result).isSameAs(edge);
    }

    @SimpleGraphs
    void fromGet(Graph graph) {
        assertThat(graph.edge("A", "B").from()).isEqualTo("A");
    }

    @SimpleGraphs
    void setTo(Graph graph) {
        var edge = graph.edge("A", "B");
        edge.setTo("C");
        assertThat(graph).hasVertex("C");
        assertThat(edge).hasToThat().isEqualTo("C");
    }

    @SimpleGraphs
    void setToNull(Graph graph) {
        var edge = graph.edge("A", "B");
        Throwable thrown = assertThrows(NullPointerException.class, () -> edge.setTo(null));
        assertThat(thrown).hasMessage("to must not be null.");
    }

    @SimpleGraphs
    void setToCreatesNewVertex(Graph graph) {
        var edge = graph.edge("A", "B");
        edge.setTo("C");
        assertThat(graph).hasVertex("C");
    }

    @SimpleGraphs
    void to(Graph graph) {
        var edge = graph.edge("A", "B");
        var result = edge.to("C");
        assertThat(edge).hasToThat().isEqualTo("C");
        assertThat(result).isSameAs(edge);
    }

    @SimpleGraphs
    void toGet(Graph graph) {
        assertThat(graph.edge("A", "B").to()).isEqualTo("B");
    }

    @SimpleGraphs
    void fromVertex(Graph graph) {
        var edge = graph.edge("A", "B");
        var vertex = graph.findVertex("A").get();
        assertThat(edge.fromVertex()).isEqualTo(vertex);
    }

    @SimpleGraphs
    void toVertex(Graph graph) {
        var edge = graph.edge("A", "B");
        var vertex = graph.findVertex("B").get();
        assertThat(edge.toVertex()).isEqualTo(vertex);
    }

    @SimpleGraphs
    void setProperty(Graph graph) {
        var edge = graph.edge("A", "B");
        edge.setProperty("key", "value");
        assertThat(edge).withProperty("key").hasValue("value");
    }

    @SimpleGraphs
    void removeProperty(Graph graph) {
        var edge = graph.edge("A", "B").property("name", "value");
        edge.removeProperty("name");
        assertThat(edge).withProperty("name").isEmpty();
    }

    @SimpleGraphs
    void testEquals(Graph<String> graph) {
        var edge = graph.edge("A", "B");
        EqualsVerifier.forClass(edge.getClass()).suppress(Warning.NONFINAL_FIELDS).verify();
    }
}
