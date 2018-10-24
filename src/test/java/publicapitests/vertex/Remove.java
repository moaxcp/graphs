package publicapitests.vertex;

import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.TestHandler;
import com.github.moaxcp.graphs.event.EdgeRemovedGraphEvent;
import com.github.moaxcp.graphs.event.VertexRemovedGraphEvent;
import org.greenrobot.eventbus.EventBus;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class Remove {
    Graph graph = new Graph("graph");

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
}
