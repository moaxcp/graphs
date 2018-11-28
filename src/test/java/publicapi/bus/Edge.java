package publicapi.bus;

import static com.github.moaxcp.graphs.EventBusSubject.assertEvents;
import static com.github.moaxcp.graphs.events.Builders.edgeCreated;
import static com.github.moaxcp.graphs.events.Builders.vertexCreated;
import com.github.moaxcp.graphs.SimpleGraph;
import testframework.EventSimpleGraphs;

public class Edge {
    @EventSimpleGraphs
    void created(SimpleGraph graph) {
        graph.id("id");
        assertEvents(() -> graph.edge("A", "B")).containsExactly(vertexCreated().graphId("id").vertexId("A").build(),
                vertexCreated().graphId("id").vertexId("B").build(),
                edgeCreated().graphId("id").from("A").to("B").build())
        .inOrder();
    }
}
