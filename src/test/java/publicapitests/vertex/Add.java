package publicapitests.vertex;

import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.TestHandler;
import com.github.moaxcp.graphs.event.VertexAddedGraphEvent;
import static com.google.common.truth.Truth.assertThat;
import org.greenrobot.eventbus.EventBus;
import org.junit.jupiter.api.Test;

import static com.github.moaxcp.graphs.GraphSubject.assertThat;
import static com.github.moaxcp.graphs.VertexSubject.assertThat;

public class Add {
    Graph graph = new Graph("graph");

    @Test
    void addNewVertex() {
        var vertex = graph.vertex("id");
        assertThat(graph).hasVertex("id").isSameAs(vertex);
        assertThat(vertex).hasId("id");
        assertThat(vertex).thatProperty("id").hasValue("id");
        assertThat(vertex).thatLocal("id").isEqualTo("id");
    }

    @Test
    void addExistingVertex() {
        Graph.Vertex vertexA = graph.vertex("A");
        Graph.Vertex vertexB = graph.vertex("A");
        assertThat(vertexA).isSameAs(vertexB);
    }

    @Test
    void addNewVertexEvent() {
        var handler = new TestHandler();
        EventBus.getDefault().register(handler);
        var vertex = graph.vertex("id");
        assertThat(handler.getEvent()).isInstanceOf(VertexAddedGraphEvent.class);
        var event = (VertexAddedGraphEvent) handler.getEvent();
        assertThat(event.getGraph()).isSameAs(graph);
        assertThat(event.getVertex()).isSameAs(vertex);
    }

}
