package publicapi;

import com.github.moaxcp.graphs.EventGraph;
import com.github.moaxcp.graphs.events.*;
import com.github.moaxcp.graphs.testframework.EventSimpleGraphs;
import org.greenrobot.eventbus.EventBus;

import static com.github.moaxcp.graphs.Truth.assertThat;

public class Vertex {
    @EventSimpleGraphs
    void created(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        assertThat(graph).with(bus).hasEventsIn(g -> g.vertex("A")).containsExactly(VertexCreated.class);
    }

    @EventSimpleGraphs
    void notCreated(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");
        assertThat(graph).with(bus).hasNoEventsIn(g -> g.vertex("A"));
    }

    @EventSimpleGraphs
    void remove(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");
        assertThat(graph).with(bus).hasEventsIn(g -> g.removeVertex("A")).containsExactly(VertexRemoved.class);
    }

    @EventSimpleGraphs
    void removeWithEdges(EventGraph<String> graph, EventBus bus) {
        graph.edge("A", "B");
        graph.edge("A", "C");
        graph.edge("A", "D");
        assertThat(graph).with(bus).hasEventsIn(g->g.removeVertex("A"))
            .containsExactly(EdgeRemoved.class, EdgeRemoved.class, EdgeRemoved.class, VertexRemoved.class).inOrder();
    }

    @EventSimpleGraphs
    void updateId(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");
        assertThat(graph).with(bus).hasEventsIn(g -> g.vertex("A").id("B")).containsExactly(VertexIdUpdated.class);
    }

    @EventSimpleGraphs
    void addProperty(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");
        assertThat(graph).with(bus).hasEventsIn(g-> g.vertex("A").property("name", "value")).containsExactly(VertexPropertyAdded.class);
    }

    @EventSimpleGraphs
    void removeProperty(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A").property("name", "value");
        assertThat(graph).with(bus).hasEventsIn(g-> g.vertex("A").removeProperty("name")).containsExactly(VertexPropertyRemoved.class);
    }

    @EventSimpleGraphs
    void updateProperty(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A").property("name", "value");
        assertThat(graph).with(bus).hasEventsIn(g-> g.vertex("A").property("name", "value2")).containsExactly(VertexPropertyUpdated.class);
    }
}
