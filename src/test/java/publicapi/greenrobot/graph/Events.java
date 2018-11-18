package publicapi.greenrobot.graph;

import static com.google.common.truth.Truth.assertThat;
import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.event.*;
import com.github.moaxcp.graphs.greenrobot.UndirectedEventGraph;
import org.greenrobot.eventbus.EventBus;
import org.junit.jupiter.api.Test;
import stubs.TestHandler;

public class Events {
    UndirectedEventGraph graph = new UndirectedEventGraph("graph");

    @Test
    void testAddNewEdgeEvent() {
        var handler = new TestHandler();
        EventBus.getDefault().register(handler);
        var edge = graph.edge("from", "to");
        assertThat(handler.event).isInstanceOf(EdgeAddedGraphEvent.class);
        var event = (EdgeAddedGraphEvent) handler.event;
        GraphSubject.assertThat(event.getGraph()).isSameAs(graph);
        assertThat(event.getEdge()).isSameAs(edge);
    }

    @Test
    void testRemoveEdgeEvent() {
        var handler = new TestHandler();
        EventBus.getDefault().register(handler);
        var edge = graph.edge("from", "to");
        graph.removeEdge("from", "to");
        assertThat(handler.event).isInstanceOf(EdgeRemovedGraphEvent.class);
        var event = (EdgeRemovedGraphEvent) handler.event;
        GraphSubject.assertThat(event.getGraph()).isSameAs(graph);
        assertThat(event.getEdge()).isSameAs(edge);
    }

    @Test
    void addNewVertexEvent() {
        var handler = new TestHandler();
        EventBus.getDefault().register(handler);
        var vertex = graph.vertex("id");
        assertThat(handler.getEvent()).isInstanceOf(VertexAddedGraphEvent.class);
        var event = (VertexAddedGraphEvent) handler.getEvent();
        GraphSubject.assertThat(event.getGraph()).isSameAs(graph);
        Truth.assertThat(event.getVertex()).isSameAs(vertex);
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
        Truth.assertThat(firstEvent.getEdge()).isSameAs(firstEdge);
        var secondEvent = (EdgeRemovedGraphEvent) handler.getEvents().get(1);
        Truth.assertThat(secondEvent.getEdge()).isSameAs(secondEdge);
        var thirdEvent = (VertexRemovedGraphEvent) handler.getEvents().get(2);
        Truth.assertThat(thirdEvent.getVertex()).isSameAs(vertex);
    }
}
