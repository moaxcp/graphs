package publicapi;

import static com.github.moaxcp.graphs.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.greenrobot.*;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class GraphVertexMethods {

    static Stream<SimpleGraph> graphs() {
        return Stream.of(
                new UnidrectedGraph(),
                new UndirectedEventGraph(),
                new DirectedEventGraph());
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void testRemoveVertex(SimpleGraph graph) {
        graph.edge("A", "B");
        graph.edge("A", "C");
        graph.edge("Z", "Y");

        graph.removeVertex("A");
        assertThat(graph.getEdges()).hasSize(1);
        assertThat(graph.getVertices()).doesNotContainKey("A");
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void addNewVertex(SimpleGraph graph) {
        var vertex = graph.vertex("id");
        GraphSubject.assertThat(graph).hasVertexThat("id").isSameAs(vertex);
        assertThat(vertex).hasId("id");
        assertThat(vertex).thatProperty("id").hasValue("id");
        assertThat(vertex).thatLocal("id").isEqualTo("id");
    }

    @ParameterizedTest
    @MethodSource("graphs")
    void addExistingVertex(SimpleGraph graph) {
        var vertexA = graph.vertex("A");
        var vertexB = graph.vertex("A");
        assertThat(vertexA).isSameAs(vertexB);
    }
}
