package publicapi;

import static com.github.moaxcp.graphs.Truth.assertThat;
import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.greenrobot.*;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class GraphEdgeMethods {
    static Stream<SimpleGraph> graphs() {
        return Stream.of(
                new UnidrectedGraph(),
                new UndirectedEventGraph(),
                new DirectedEventGraph());
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void addNewEdge(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        assertThat(graph).hasEdge("A", "B").isSameAs(edge);
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void newEdgeHasNoId(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        assertThat(edge).hasIdThat().isEmpty();
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void newEdgeHasToFromProperties(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        assertThat(edge).hasPropertyThat("from").hasValue("A");
        assertThat(edge).hasPropertyThat("to").hasValue("B");
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void newEdgeHasToFromLocalProperties(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        assertThat(edge).thatLocal("from").isEqualTo("A");
        assertThat(edge).thatLocal("to").isEqualTo("B");
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void addExistingEdge(SimpleGraph graph) {
        var edge1 = graph.edge("A", "B");
        var edge2 = graph.edge("A", "B");
        assertThat(edge1).isSameAs(edge2);
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void removeEdge(SimpleGraph graph) {
        graph.edge("A", "B");
        graph.removeEdge("A", "B");
        assertThat(graph).hasNoEdge("A", "B");
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void removeEdgeThatDoesNotExist(SimpleGraph graph) {
        graph.vertex("A");
        graph.vertex("B");
        graph.removeEdge("A", "B");
        assertThat(graph).hasNoEdge("A", "B");
        assertThat(graph).hasVertices("A", "B");
    }
}
