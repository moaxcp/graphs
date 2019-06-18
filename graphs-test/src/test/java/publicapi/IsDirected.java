package publicapi;

import static com.github.moaxcp.graphs.Truth.assertThat;
import com.github.moaxcp.graphs.Graph;
import testframework.*;

public class IsDirected {
    @DirectedSimpleGraphs
    void isDirected(Graph graph) {
        assertThat(graph).isDirected();
    }

    @DirectedSimpleGraphs
    void isEdgeDirected(Graph graph) {
        var edge = graph.edge("A", "B");
        assertThat(edge).isDirected();
    }

    @UndirectedSimpleGraphs
    void isUndirected(Graph graph) {
        assertThat(graph).isNotDirected();
    }

    @UndirectedSimpleGraphs
    void isEdgeUndirected(Graph graph) {
        var edge = graph.edge("A", "B");
        assertThat(edge).isNotDirected();
    }
}
