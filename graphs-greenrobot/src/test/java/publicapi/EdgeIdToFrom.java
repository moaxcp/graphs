package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.events.*;
import com.github.moaxcp.graphs.newevents.*;
import com.github.moaxcp.graphs.testframework.*;
import org.greenrobot.eventbus.*;

import static com.github.moaxcp.graphs.truth.Truth.*;

public class EdgeIdToFrom {

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

        var expected1 = VertexCreatedEvent.<String>builder()
          .graphId("graph")
          .vertexId("A")
          .build();

        var expected2 = new EdgeFromUpdated.Builder<String>()
          .graphId("graph")
          .edgeId("edge")
          .oldFrom("oldFrom")
          .from("A")
          .to("B")
          .build();

        assertThat(bus)
          .withAction(() -> graph.getEdge("oldFrom", "B").setFrom("A"))
          .containsExactly(expected1, expected2).inOrder();
    }

    @EventSimpleGraphs
    void edgeToUpdatedCreatedVertex(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getEdge("A", "oldTo").id("edge");

        var expected1 = VertexCreatedEvent.<String>builder()
          .graphId("graph")
          .vertexId("B")
          .build();

        var expected2 = new EdgeToUpdated.Builder<String>().graphId("graph").edgeId("edge").from("A").oldTo("oldTo").to("B").build();

        assertThat(bus)
          .withAction(() -> graph.getEdge("A", "oldTo").setTo("B"))
          .containsExactly(expected1, expected2);
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
