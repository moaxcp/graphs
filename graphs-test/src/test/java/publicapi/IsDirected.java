package publicapi;

import com.github.moaxcp.graphs.PropertyGraph;
import com.github.moaxcp.graphs.testframework.TestDirectedPropertyGraphs;
import com.github.moaxcp.graphs.testframework.TestUndirectedPropertyGraphs;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;

public class IsDirected {
    @TestDirectedPropertyGraphs
    void isDirected(PropertyGraph<String> graph) {
        assertThat(graph).isDirected();
    }

    @TestDirectedPropertyGraphs
    void isEdgeDirected(PropertyGraph<String> graph) {
        var edge = graph.getEdge("A", "B");
        assertThat(edge).isDirected();
    }

    @TestUndirectedPropertyGraphs
    void isUndirected(PropertyGraph<String> graph) {
        assertThat(graph).isUndirected();
    }

    @TestUndirectedPropertyGraphs
    void isEdgeUndirected(PropertyGraph<String> graph) {
        var edge = graph.getEdge("A", "B");
        assertThat(edge).isNotDirected();
    }
}
