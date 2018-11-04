package publicapi;

import com.github.moaxcp.graphs.DirectedGraph;
import com.github.moaxcp.graphs.Graph;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static com.github.moaxcp.graphs.Truth.assertThat;

public class EdgeTest {

    static Stream<Graph> graphs() {
        return Stream.of(
                new Graph(),
                new DirectedGraph());
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void setId(Graph graph) {
        var edge = graph.edge("A", "B");
        edge.setId("id");
        assertThat(graph).hasEdge("A", "B").hasIdThat().hasValue("id");
        assertThat(graph).hasEdge("id").isSameAs(edge);
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void id(Graph graph) {
        var edge = graph.edge("A", "B").id("id");
        assertThat(graph).hasEdge("A", "B").hasIdThat().hasValue("id");
        assertThat(graph).hasEdge("id").isSameAs(edge);
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void changeId(Graph graph) {
        var edge = graph.edge("A", "B").id("id");
        edge.setId("id2");
        assertThat(graph).hasEdge("id2").isSameAs(edge);
        assertThat(graph).hasEdge("A", "B").hasIdThat().hasValue("id2");
        assertThat(graph).hasEdge("id2").hasIdThat().hasValue("id2");
        assertThat(graph).hasNoEdge("id");
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void setIdNullRemovesId(Graph graph) {
        var edge = graph.edge("A", "to").id("id");
        edge.setId(null);
        assertThat(edge).hasNoId();
        assertThat(graph).hasNoEdge("id");
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void removeIdProperty(Graph graph) {
        var edge = graph.edge("from", "to").id("id");
        edge.removeProperty("id");
        assertThat(edge).hasNoId();
        assertThat(graph).hasNoEdge("id");
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void setFrom(Graph graph) {
        var edge = graph.edge("A", "B");
        edge.setFrom("C");
        assertThat(graph).hasVertex("C");
        assertThat(edge).hasFromThat().isEqualTo("C");
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void setTo(Graph graph) {
        var edge = graph.edge("A", "B");
        edge.setTo("C");
        assertThat(graph).hasVertex("C");
        assertThat(edge).hasToThat().isEqualTo("C");
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void from(Graph graph) {
        var edge = graph.edge("A", "B");
        var vertex = graph.findVertex("A").get();
        assertThat(edge.fromVertex()).isEqualTo(vertex);
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void to(Graph graph) {
        var edge = graph.edge("A", "B");
        var vertex = graph.findVertex("B").get();
        assertThat(edge.toVertex()).isEqualTo(vertex);
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void setProperty(Graph graph) {
        var edge = graph.edge("A", "B");
        edge.setProperty("key", "value");
        assertThat(edge).hasPropertyThat("key").hasValue("value");
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void setPropertyFrom(Graph graph) {
        var edge = graph.edge("A", "B");
        edge.setProperty("from", "C");
        assertThat(edge).hasFromThat().isEqualTo("C");
    }
}
