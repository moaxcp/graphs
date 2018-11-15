package publicapi.greenrobot;

import static com.github.moaxcp.graphs.Truth.*;
import com.github.moaxcp.graphs.greenrobot.DirectedGraph;
import com.github.moaxcp.graphs.greenrobot.UndirectedGraph;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


public class EdgeTest {

    static Stream<UndirectedGraph> graphs() {
        return Stream.of(
                new UndirectedGraph(),
                new DirectedGraph());
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void setId(UndirectedGraph graph) {
        var edge = graph.edge("A", "B");
        edge.setId("id");
        assertThat(graph).hasEdge("A", "B").hasIdThat().hasValue("id");
        assertThat(graph).hasEdge("id").isSameAs(edge);
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void id(UndirectedGraph graph) {
        var edge = graph.edge("A", "B").id("id");
        assertThat(graph).hasEdge("A", "B").hasIdThat().hasValue("id");
        assertThat(graph).hasEdge("id").isSameAs(edge);
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void changeId(UndirectedGraph graph) {
        var edge = graph.edge("A", "B").id("id");
        edge.setId("id2");
        assertThat(graph).hasEdge("id2").isSameAs(edge);
        assertThat(graph).hasEdge("A", "B").hasIdThat().hasValue("id2");
        assertThat(graph).hasEdge("id2").hasIdThat().hasValue("id2");
        assertThat(graph).hasNoEdge("id");
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void setIdNullRemovesId(UndirectedGraph graph) {
        var edge = graph.edge("A", "to").id("id");
        edge.setId(null);
        assertThat(edge).hasNoId();
        assertThat(graph).hasNoEdge("id");
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void removeIdProperty(UndirectedGraph graph) {
        var edge = graph.edge("from", "to").id("id");
        edge.removeProperty("id");
        assertThat(edge).hasNoId();
        assertThat(graph).hasNoEdge("id");
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void setFrom(UndirectedGraph graph) {
        var edge = graph.edge("A", "B");
        edge.setFrom("C");
        assertThat(graph).hasVertex("C");
        assertThat(edge).hasFromThat().isEqualTo("C");
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void setTo(UndirectedGraph graph) {
        var edge = graph.edge("A", "B");
        edge.setTo("C");
        assertThat(graph).hasVertex("C");
        assertThat(edge).hasToThat().isEqualTo("C");
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void from(UndirectedGraph graph) {
        var edge = graph.edge("A", "B");
        var vertex = graph.findVertex("A").get();
        assertThat(edge.fromVertex()).isEqualTo(vertex);
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void to(UndirectedGraph graph) {
        var edge = graph.edge("A", "B");
        var vertex = graph.findVertex("B").get();
        assertThat(edge.toVertex()).isEqualTo(vertex);
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void setProperty(UndirectedGraph graph) {
        var edge = graph.edge("A", "B");
        edge.setProperty("key", "value");
        assertThat(edge).hasPropertyThat("key").hasValue("value");
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void setPropertyFrom(UndirectedGraph graph) {
        var edge = graph.edge("A", "B");
        edge.setProperty("from", "C");
        assertThat(edge).hasFromThat().isEqualTo("C");
    }
}
