package publicapi;

import com.github.moaxcp.graphs.EventGraph;
import com.github.moaxcp.graphs.events.EdgeCreated;
import com.github.moaxcp.graphs.events.EdgeRemoved;
import com.github.moaxcp.graphs.events.VertexCreated;
import com.github.moaxcp.graphs.testframework.EventSimpleGraphs;
import org.greenrobot.eventbus.EventBus;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;

public class EventGraphEdgeMethods {
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
}
