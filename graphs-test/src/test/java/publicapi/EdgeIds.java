package publicapi;

import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.testframework.TestGraphs;

import static com.google.common.truth.Truth.assertThat;

public class EdgeIds {

    @TestGraphs
    void defaultValue(Graph<String> graph) {
        assertThat(graph.getEdgeIds()).isEmpty();
    }

    @TestGraphs
    void canAddEdgeId(Graph<String> graph) {
        var edge = graph.getEdge("A", "B").id("id");
        assertThat(graph.getEdgeIds()).containsEntry("id", edge);
    }
}
