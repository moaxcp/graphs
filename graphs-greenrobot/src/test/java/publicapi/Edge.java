package publicapi;

import com.github.moaxcp.graphs.EventGraph;
import com.github.moaxcp.graphs.events.*;
import com.github.moaxcp.graphs.testframework.EventSimpleGraphs;
import org.greenrobot.eventbus.EventBus;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;

public class Edge {
    @EventSimpleGraphs
    void created(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");

        var expected1 = new VertexCreated.Builder<String>().graphId("graph").vertexId("A").build();
        var expected2 = new VertexCreated.Builder<String>().graphId("graph").vertexId("B").build();
        var expected3 = new EdgeCreated.Builder<String>().graphId("graph").from("A").to("B").build();

        assertThat(bus).withAction(() -> graph.edge("A", "B"))
            .containsExactly(expected1, expected2, expected3).inOrder();
    }

    @EventSimpleGraphs
    void notCreated(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getEdge("A", "B").id("edge");
        assertThat(bus).withAction(() -> graph.edge("A", "B")).isEmpty();
    }

    @EventSimpleGraphs
    void removed(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getEdge("A", "B").id("edge");

        var expected = new EdgeRemoved.Builder<String>().graphId("graph").edgeId("edge").from("A").to("B").build();

        assertThat(bus).withAction(() -> graph.removeEdge("A", "B")).containsExactly(expected);
    }

    @EventSimpleGraphs
    void removeWithId(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getEdge("A", "B").id("edge");

        var expected = new EdgeRemoved.Builder<String>().graphId("graph").edgeId("edge").from("A").to("B").build();

        assertThat(bus).withAction(() -> graph.removeEdge("edge")).containsExactly(expected);
    }

    @EventSimpleGraphs
    void addProperty(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.edge("A", "B").id("edge");
        assertThat(graph).with(bus)
            .hasEventsIn(g -> g.getEdge("A", "B").property("name", "value")).containsExactly(EdgePropertyAdded.class);
    }

    @EventSimpleGraphs
    void removeProperty(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getEdge("A", "B").id("edge").property("name", "value");
        assertThat(graph).with(bus)
            .hasEventsIn(g -> g.getEdge("A", "B").removeProperty("name")).containsExactly(EdgePropertyRemoved.class);
    }

    @EventSimpleGraphs
    void updateProperty(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getEdge("A", "B").id("edge").property("name", "value");
        assertThat(graph).with(bus)
            .hasEventsIn(g -> g.getEdge("A", "B").property("name", "value2")).containsExactly(EdgePropertyUpdated.class);
    }

    @EventSimpleGraphs
    void edgeIdAdded(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.edge("A", "B");

        var expected = new EdgeIdAdded.Builder<String>().graphId("graph").edgeId("edge").from("A").to("B").build();

        assertThat(bus).withAction(() -> graph.getEdge("A", "B").id("edge")).containsExactly(expected);
    }

    @EventSimpleGraphs
    void edgeIdRemovedNoId(EventGraph<String> graph, EventBus bus) {
        graph.edge("A", "B");
        assertThat(bus).withAction(() -> graph.getEdge("A", "B").id(null)).isEmpty();
    }

    @EventSimpleGraphs
    void edgeIdRemoved(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getEdge("A", "B").id("edge");

        var expected = new EdgeIdRemoved.Builder<String>().graphId("graph").edgeId("edge").from("A").to("B").build();

        assertThat(bus).withAction(() -> graph.getEdge("A", "B").id(null)).containsExactly(expected);
    }

    @EventSimpleGraphs
    void edgeIdUpdated(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getEdge("A", "B").id("oldEdge");

        var expected = new EdgeIdUpdated.Builder<String>().graphId("graph").oldEdgeId("oldEdge").edgeId("edge").from("A").to("B").build();

        assertThat(bus).withAction(() -> graph.getEdge("A", "B").id("edge")).containsExactly(expected);
    }

    @EventSimpleGraphs
    void edgeFromUpdatedCreatedVertex(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getEdge("oldFrom", "B").id("edge");

        var expected1 = new VertexCreated.Builder<String>().graphId("graph").vertexId("A").build();
        var expected2 = new EdgeFromUpdated.Builder<String>().graphId("graph").edgeId("edge").oldFrom("oldFrom").from("A").to("B").build();

        assertThat(bus).withAction(() -> graph.getEdge("oldFrom", "B").setFrom("A")).containsExactly(expected1, expected2).inOrder();
    }

    @EventSimpleGraphs
    void edgeToUpdatedCreatedVertex(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getEdge("A", "oldTo").id("edge");

        var expected1 = new VertexCreated.Builder<String>().graphId("graph").vertexId("B").build();
        var expected2 = new EdgeToUpdated.Builder<String>().graphId("graph").edgeId("edge").from("A").oldTo("oldTo").to("B").build();

        assertThat(bus).withAction(() -> graph.getEdge("A", "oldTo").setTo("B")).containsExactly(expected1, expected2);
    }

    @EventSimpleGraphs
    void edgeFromUpdated(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");
        graph.getEdge("oldFrom", "B").id("edge");

        var expected = new EdgeFromUpdated.Builder<String>().graphId("graph").edgeId("edge").oldFrom("oldFrom").from("A").to("B").build();

        assertThat(bus).withAction(() -> graph.getEdge("oldFrom", "B").setFrom("A")).containsExactly(expected);
    }

    @EventSimpleGraphs
    void edgeToUpdated(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("B");
        graph.getEdge("A", "oldTo").id("edge");

        var expected = new EdgeToUpdated.Builder<String>().graphId("graph").edgeId("edge").from("A").oldTo("oldTo").to("B").build();

        assertThat(bus).withAction(() -> graph.getEdge("A", "oldTo").setTo("B")).containsExactly(expected);
    }
}
