package publicapi.bus;

import static com.github.moaxcp.graphs.Truth.assertEvents;
import static com.github.moaxcp.graphs.events.Builders.vertexCreated;
import com.github.moaxcp.graphs.SimpleGraph;
import testframework.EventSimpleGraphs;

public class Vertex {
    @EventSimpleGraphs
    void created(SimpleGraph graph) {
        graph.id("id");
        assertEvents(() -> graph.vertex("A")).containsExactly(vertexCreated().graphId("id").vertexId("A").build());
    }
}