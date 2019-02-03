package publicapi.bus;

import static com.github.moaxcp.graphs.Truth.assertThat;
import com.github.moaxcp.graphs.greenrobot.EventGraph;
import com.github.moaxcp.graphs.events.*;
import testframework.EventSimpleGraphs;

public class Vertex {
    @EventSimpleGraphs
    void created(EventGraph<String> graph) {
        graph.id("graph");
        assertThat(graph).hasEventsIn(g -> g.vertex("A")).containsExactly(VertexCreated.class);
    }

    @EventSimpleGraphs
    void notCreated(EventGraph<String> graph) {
        graph.id("graph");
        graph.vertex("A");
        assertThat(graph).hasNoEventsIn(g -> g.vertex("A"));
    }

    @EventSimpleGraphs
    void remove(EventGraph<String> graph) {
        graph.id("graph");
        graph.vertex("A");
        assertThat(graph).hasEventsIn(g -> g.removeVertex("A")).containsExactly(VertexRemoved.class);
    }

    @EventSimpleGraphs
    void removeWithEdges(EventGraph<String> graph) {
        graph.edge("A", "B");
        graph.edge("A", "C");
        graph.edge("A", "D");
        assertThat(graph).hasEventsIn(g->g.removeVertex("A"))
            .containsExactly(EdgeRemoved.class, EdgeRemoved.class, EdgeRemoved.class, VertexRemoved.class).inOrder();
    }

    @EventSimpleGraphs
    void updateId(EventGraph<String> graph) {
        graph.id("graph");
        graph.vertex("A");
        assertThat(graph).hasEventsIn(g -> g.vertex("A").id("B")).containsExactly(VertexIdUpdated.class);
    }

    @EventSimpleGraphs
    void addProperty(EventGraph<String> graph) {
        graph.id("graph");
        graph.vertex("A");
        assertThat(graph).hasEventsIn(g-> g.vertex("A").property("name", "value")).containsExactly(VertexPropertyAdded.class);
    }

    @EventSimpleGraphs
    void removeProperty(EventGraph<String> graph) {
        graph.id("graph");
        graph.vertex("A").property("name", "value");
        assertThat(graph).hasEventsIn(g-> g.vertex("A").removeProperty("name")).containsExactly(VertexPropertyRemoved.class);
    }

    @EventSimpleGraphs
    void updateProperty(EventGraph<String> graph) {
        graph.id("graph");
        graph.vertex("A").property("name", "value");
        assertThat(graph).hasEventsIn(g-> g.vertex("A").property("name", "value2")).containsExactly(VertexPropertyUpdated.class);
    }
}
