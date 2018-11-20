package publicapi;

import static com.google.common.truth.Truth.assertThat;
import com.github.moaxcp.graphs.SimpleGraph;
import testframework.SimpleGraphs;

public class EdgeIds {

    @SimpleGraphs
    void defaultValue(SimpleGraph graph) {
        assertThat(graph.getEdgeIds()).isEmpty();
    }

    @SimpleGraphs
    void canAddEdgeId(SimpleGraph graph) {
        var edge = graph.edge("A", "B").id("id");
        assertThat(graph.getEdgeIds()).containsEntry("id", edge);
    }
}
