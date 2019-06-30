package publicapi.bus;

import com.github.moaxcp.graphs.EventGraph;
import com.github.moaxcp.graphs.events.*;
import com.github.moaxcp.graphs.testframework.EventSimpleGraphs;
import org.greenrobot.eventbus.EventBus;

import static com.github.moaxcp.graphs.Truth.assertThat;

public class Edge {
    @EventSimpleGraphs
    void created(EventGraph<String> graph, EventBus bus) {
        graph.id("id");
        assertThat(graph).with(bus).hasEventsIn(g -> g.edge("A", "B"))
            .containsExactly(VertexCreated.class, VertexCreated.class, EdgeCreated.class).inOrder();
    }

    @EventSimpleGraphs
    void notCreated(EventGraph<String> graph, EventBus bus) {
        graph.id("id");
        graph.edge("A", "B").id("id");
        assertThat(graph).with(bus).hasNoEventsIn(g -> g.edge("A", "B"));
    }

    @EventSimpleGraphs
    void removed(EventGraph<String> graph, EventBus bus) {
        graph.id("id");
        graph.edge("A", "B");
        assertThat(graph).with(bus).hasEventsIn(g -> g.removeEdge("A", "B")).containsExactly(EdgeRemoved.class);
    }

    @EventSimpleGraphs
    void removeWithId(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.edge("A", "B").id("edge");
        assertThat(graph).with(bus).hasEventsIn(g -> g.removeEdge("edge")).containsExactly(EdgeRemoved.class);
    }

    @EventSimpleGraphs
    void addProperty(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.edge("A", "B").id("edge");
        assertThat(graph).with(bus)
            .hasEventsIn(g -> g.edge("A", "B").property("name", "value")).containsExactly(EdgePropertyAdded.class);
    }

    @EventSimpleGraphs
    void removeProperty(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.edge("A", "B").id("edge").property("name", "value");
        assertThat(graph).with(bus)
            .hasEventsIn(g -> g.edge("A", "B").removeProperty("name")).containsExactly(EdgePropertyRemoved.class);
    }

    @EventSimpleGraphs
    void updateProperty(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.edge("A", "B").id("edge").property("name", "value");
        assertThat(graph).with(bus)
            .hasEventsIn(g -> g.edge("A", "B").property("name", "value2")).containsExactly(EdgePropertyUpdated.class);
    }

    @EventSimpleGraphs
    void edgeIdAdded(EventGraph<String> graph, EventBus bus) {
        graph.edge("A", "B");
        assertThat(graph).with(bus).hasEventsIn(g -> g.edge("A", "B").id("edge")).containsExactly(EdgeIdAdded.class);
    }

    @EventSimpleGraphs
    void edgeIdRemovedNoId(EventGraph<String> graph, EventBus bus) {
        graph.edge("A", "B");
        assertThat(graph).with(bus).hasNoEventsIn(g -> g.edge("A", "B").id(null));
    }

    @EventSimpleGraphs
    void edgeIdRemoved(EventGraph<String> graph, EventBus bus) {
        graph.edge("A", "B").id("edge");
        assertThat(graph).with(bus).hasEventsIn(g -> g.edge("A", "B").id(null)).containsExactly(EdgeIdRemoved.class);
    }

    @EventSimpleGraphs
    void edgeIdUpdated(EventGraph<String> graph, EventBus bus) {
        graph.edge("A", "B").id("id1");
        assertThat(graph).with(bus).hasEventsIn(g -> g.edge("A", "B").id("id2")).containsExactly(EdgeIdUpdated.class);
    }

    @EventSimpleGraphs
    void edgeFromUpdatedCreatedVertex(EventGraph<String> graph, EventBus bus) {
        graph.edge("id", "B");
        assertThat(graph).with(bus).hasEventsIn(g -> g.edge("id", "B").setFrom("A")).containsExactly(VertexCreated.class, EdgeFromUpdated.class);
    }

    @EventSimpleGraphs
    void edgeToUpdatedCreatedVertex(EventGraph<String> graph, EventBus bus) {
        graph.edge("A", "id");
        assertThat(graph).with(bus).hasEventsIn(g -> g.edge("A", "id").setTo("B")).containsExactly(VertexCreated.class, EdgeToUpdated.class);
    }

    @EventSimpleGraphs
    void edgeFromUpdated(EventGraph<String> graph, EventBus bus) {
        graph.vertex("A");
        graph.edge("id", "B");
        assertThat(graph).with(bus).hasEventsIn(g -> g.edge("id", "B").setFrom("A")).containsExactly(EdgeFromUpdated.class);
    }

    @EventSimpleGraphs
    void edgeToUpdated(EventGraph<String> graph, EventBus bus) {
        graph.vertex("B");
        graph.edge("A", "id");
        assertThat(graph).with(bus).hasEventsIn(g -> g.edge("A", "id").setTo("B")).containsExactly(EdgeToUpdated.class);
    }
}
