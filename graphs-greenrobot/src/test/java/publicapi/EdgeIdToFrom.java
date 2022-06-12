package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.events.*;
import com.github.moaxcp.graphs.testframework.*;
import org.greenrobot.eventbus.*;

import static com.github.moaxcp.graphs.truth.Truth.*;

public class EdgeIdToFrom {

    @TestGreenrobotEventGraphs
    void edgeIdAdded(EventPropertyGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.edge("A", "B");

        var expected = EdgePropertyEvent.<String>builder().graphId("graph").newEdgeId("edge").sourceId("A").targetId("B").build();

        assertThat(bus).withAction(() -> graph.getEdge("A", "B").id("edge")).containsExactly(expected);
    }

    @TestGreenrobotEventGraphs
    void edgeIdRemovedNoId(EventPropertyGraph<String> graph, EventBus bus) {
        graph.edge("A", "B");
        assertThat(bus).withAction(() -> graph.getEdge("A", "B").id(null)).isEmpty();
    }

    @TestGreenrobotEventGraphs
    void edgeIdRemoved(EventPropertyGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getEdge("A", "B").id("edge");

        var expected = EdgePropertyEvent.<String>builder().graphId("graph").edgeId("edge").newEdgeId(null).sourceId("A").targetId("B").build();

        assertThat(bus).withAction(() -> graph.getEdge("A", "B").id(null)).containsExactly(expected);
    }

    @TestGreenrobotEventGraphs
    void edgeIdUpdated(EventPropertyGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getEdge("A", "B").id("oldEdge");

        var expected = EdgePropertyEvent.<String>builder().graphId("graph").edgeId("oldEdge").newEdgeId("edge").sourceId("A").targetId("B").build();

        assertThat(bus).withAction(() -> graph.getEdge("A", "B").id("edge")).containsExactly(expected);
    }

    @TestGreenrobotEventGraphs
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
          .sourceId("oldFrom")
          .newSourceId("A")
          .targetId("B")
          .build();

        assertThat(bus)
          .withAction(() -> graph.getEdge("oldFrom", "B").setSource("A"))
          .containsExactly(expected1, expected2).inOrder();
    }

    @TestGreenrobotEventGraphs
    void edgeToUpdatedCreatedVertex(EventPropertyGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getEdge("A", "oldTo").id("edge");

        var expected1 = VertexCreatedEvent.<String>builder()
          .graphId("graph")
          .vertexId("B")
          .build();

        var expected2 = EdgePropertyEvent.<String>builder().graphId("graph").edgeId("edge").sourceId("A").targetId("oldTo").newTargetId("B").build();

        assertThat(bus)
          .withAction(() -> graph.getEdge("A", "oldTo").setTarget("B"))
          .containsExactly(expected1, expected2);
    }

    @TestGreenrobotEventGraphs
    void edgeFromUpdated(EventPropertyGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");
        graph.getEdge("oldFrom", "B").id("edge");

        var expected = EdgePropertyEvent.<String>builder().graphId("graph").edgeId("edge").sourceId("oldFrom").newSourceId("A").targetId("B").build();

        assertThat(bus).withAction(() -> graph.getEdge("oldFrom", "B").setSource("A")).containsExactly(expected);
    }

    @TestGreenrobotEventGraphs
    void edgeToUpdated(EventPropertyGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("B");
        graph.getEdge("A", "oldTo").id("edge");

        var expected = EdgePropertyEvent.<String>builder().graphId("graph").edgeId("edge").sourceId("A").targetId("oldTo").newTargetId("B").build();

        assertThat(bus).withAction(() -> graph.getEdge("A", "oldTo").setTarget("B")).containsExactly(expected);
    }
}
