package publicapi;

import static com.github.moaxcp.graphs.Truth.assertThat;
import com.github.moaxcp.graphs.SimpleGraph;
import testframework.*;

public class IsDirected {
    @DirectedSimpleGraphs
    void isDirected(SimpleGraph graph) {
        assertThat(graph).isDirected();
    }

    @DirectedSimpleGraphs
    void isEdgeDirected(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        assertThat(edge).isDirected();
    }

    @UndirectedSimpleGraphs
    void isUndirected(SimpleGraph graph) {
        assertThat(graph).isNotDirected();
    }

    @UndirectedSimpleGraphs
    void isEdgeUndirected(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        assertThat(edge).isNotDirected();
    }
}
