package publicapi.bus;

import static com.github.moaxcp.graphs.Truth.assertThat;
import com.github.moaxcp.graphs.SimpleEventGraph;
import com.github.moaxcp.graphs.events.VertexCreated;
import testframework.EventSimpleGraphs;

public class Vertex {
    @EventSimpleGraphs
    void created(SimpleEventGraph graph) {
        graph.id("id");
        assertThat(graph).hasEventsIn(g -> g.vertex("A")).containsExactly(VertexCreated.class);
    }
}
