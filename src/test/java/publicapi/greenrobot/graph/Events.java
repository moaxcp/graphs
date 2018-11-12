package publicapi.greenrobot.graph;

import com.github.moaxcp.graphs.greenrobot.UndirectedGraph;
import com.github.moaxcp.graphs.GraphSubject;
import com.github.moaxcp.graphs.event.EdgeAddedGraphEvent;
import com.github.moaxcp.graphs.event.EdgeRemovedGraphEvent;
import org.greenrobot.eventbus.EventBus;
import org.junit.jupiter.api.Test;
import stubs.TestHandler;

import static com.google.common.truth.Truth.assertThat;

public class Events {
    UndirectedGraph graph = new UndirectedGraph("graph");

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
}
