package publicapi.greenrobot.graph;

import com.github.moaxcp.graphs.greenrobot.UndirectedGraph;
import com.github.moaxcp.graphs.GraphSubject;
import stubs.TestHandler;
import com.github.moaxcp.graphs.VertexSubject;
import com.github.moaxcp.graphs.event.EdgeRemovedGraphEvent;
import com.github.moaxcp.graphs.event.VertexAddedGraphEvent;
import com.github.moaxcp.graphs.event.VertexRemovedGraphEvent;
import org.greenrobot.eventbus.EventBus;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class GraphVertexMethods {
    UndirectedGraph graph = new UndirectedGraph("graph");

    @Test
    void testRemoveVertex() {
        graph.edge("A", "B");
        graph.edge("A", "C");
        graph.edge("Z", "Y");

        graph.removeVertex("A");
        assertThat(graph.getEdges()).hasSize(1);
        assertThat(graph.getVertices()).doesNotContainKey("A");
    }

    @Test
    void testRemoveVertexEvent() {
        var firstEdge = graph.edge("A", "B");
        var secondEdge = graph.edge("A", "C");
        graph.edge("Z", "Y");
        var vertex = graph.vertex("A");

        var handler = new TestHandler();
        EventBus.getDefault().register(handler);

        graph.removeVertex("A");
        assertThat(handler.getEvents()).hasSize(3);
        assertThat(handler.getEvents().get(0)).isInstanceOf(EdgeRemovedGraphEvent.class);
        var firstEvent = (EdgeRemovedGraphEvent) handler.getEvents().get(0);
        assertThat(firstEvent.getEdge()).isSameAs(firstEdge);
        var secondEvent = (EdgeRemovedGraphEvent) handler.getEvents().get(1);
        assertThat(secondEvent.getEdge()).isSameAs(secondEdge);
        var thirdEvent = (VertexRemovedGraphEvent) handler.getEvents().get(2);
        assertThat(thirdEvent.getVertex()).isSameAs(vertex);
    }

    @Test
    void addNewVertex() {
        var vertex = graph.vertex("id");
        GraphSubject.assertThat(graph).hasVertexThat("id").isSameAs(vertex);
        VertexSubject.assertThat(vertex).hasId("id");
        VertexSubject.assertThat(vertex).thatProperty("id").hasValue("id");
        VertexSubject.assertThat(vertex).thatLocal("id").isEqualTo("id");
    }

    @Test
    void addExistingVertex() {
        UndirectedGraph.Vertex vertexA = graph.vertex("A");
        UndirectedGraph.Vertex vertexB = graph.vertex("A");
        VertexSubject.assertThat(vertexA).isSameAs(vertexB);
    }

    @Test
    void addNewVertexEvent() {
        var handler = new TestHandler();
        EventBus.getDefault().register(handler);
        var vertex = graph.vertex("id");
        assertThat(handler.getEvent()).isInstanceOf(VertexAddedGraphEvent.class);
        var event = (VertexAddedGraphEvent) handler.getEvent();
        GraphSubject.assertThat(event.getGraph()).isSameAs(graph);
        VertexSubject.assertThat(event.getVertex()).isSameAs(vertex);
    }
}
