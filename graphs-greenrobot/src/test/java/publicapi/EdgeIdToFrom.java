package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.events.*;
import com.github.moaxcp.graphs.testframework.*;
import org.greenrobot.eventbus.*;

import static com.github.moaxcp.graphs.truth.Truth.*;

public class EdgeIdToFrom {

    @TestEventGraphs
    void edgeIdAdded(EventPropertyGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.edge("A", "B");

        var expected = EdgePropertyEvent.<String>builder().graphId("graph").newEdgeId("edge").fromId("A").toId("B").build();

        assertThat(bus).withAction(() -> graph.getEdge("A", "B").id("edge")).containsExactly(expected);
    }

    @TestEventGraphs
    void edgeIdRemovedNoId(EventPropertyGraph<String> graph, EventBus bus) {
        graph.edge("A", "B");
        assertThat(bus).withAction(() -> graph.getEdge("A", "B").id(null)).isEmpty();
    }

    @TestEventGraphs
    void edgeIdRemoved(EventPropertyGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getEdge("A", "B").id("edge");

        var expected = EdgePropertyEvent.<String>builder().graphId("graph").edgeId("edge").newEdgeId(null).fromId("A").toId("B").build();

        assertThat(bus).withAction(() -> graph.getEdge("A", "B").id(null)).containsExactly(expected);
    }

    @TestEventGraphs
    void edgeIdUpdated(EventPropertyGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getEdge("A", "B").id("oldEdge");

        var expected = EdgePropertyEvent.<String>builder().graphId("graph").edgeId("oldEdge").newEdgeId("edge").fromId("A").toId("B").build();

        assertThat(bus).withAction(() -> graph.getEdge("A", "B").id("edge")).containsExactly(expected);
    }

    @TestEventGraphs
    void edgeFromUpdatedCreatedVertex(EventPropertyGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getEdge("oldFrom", "B").id("edge");

        var expected1 = VertexCreatedEvent.<String>builder()
          .graphId("graph")
          .vertexId("A")
          .build();

        var expected2 = EdgePropertyEvent.<String>builder()
          .graphId("graph")
          .edgeId("edge")
          .fromId("oldFrom")
          .newFromId("A")
          .toId("B")
          .build();

        assertThat(bus)
          .withAction(() -> graph.getEdge("oldFrom", "B").setFrom("A"))
          .containsExactly(expected1, expected2).inOrder();
    }

    @TestEventGraphs
    void edgeToUpdatedCreatedVertex(EventPropertyGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getEdge("A", "oldTo").id("edge");

        var expected1 = VertexCreatedEvent.<String>builder()
          .graphId("graph")
          .vertexId("B")
          .build();

        var expected2 = EdgePropertyEvent.<String>builder().graphId("graph").edgeId("edge").fromId("A").toId("oldTo").newToId("B").build();

        assertThat(bus)
          .withAction(() -> graph.getEdge("A", "oldTo").setTo("B"))
          .containsExactly(expected1, expected2);
    }

    @TestEventGraphs
    void edgeFromUpdated(EventPropertyGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");
        graph.getEdge("oldFrom", "B").id("edge");

        var expected = EdgePropertyEvent.<String>builder().graphId("graph").edgeId("edge").fromId("oldFrom").newFromId("A").toId("B").build();

        assertThat(bus).withAction(() -> graph.getEdge("oldFrom", "B").setFrom("A")).containsExactly(expected);
    }

    @TestEventGraphs
    void edgeToUpdated(EventPropertyGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("B");
        graph.getEdge("A", "oldTo").id("edge");

        var expected = EdgePropertyEvent.<String>builder().graphId("graph").edgeId("edge").fromId("A").toId("oldTo").newToId("B").build();

        assertThat(bus).withAction(() -> graph.getEdge("A", "oldTo").setTo("B")).containsExactly(expected);
    }
}
