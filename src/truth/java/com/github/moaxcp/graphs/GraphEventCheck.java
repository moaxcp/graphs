package com.github.moaxcp.graphs;

import static com.github.moaxcp.graphs.Truth.assertThat;
import static org.junit.Assert.fail;
import com.github.moaxcp.graphs.events.*;
import java.util.*;
import org.greenrobot.eventbus.Subscribe;

public class GraphEventCheck {

    private final List<Class> classes = new ArrayList<>();
    private final SimpleGraph graph;

    public GraphEventCheck(SimpleGraph graph) {
        this.graph = graph;
    }

    public List<Class> getEventClasses() {
        return Collections.unmodifiableList(classes);
    }

    @Subscribe
    public void vertexCreated(VertexCreated event) {
        assertThat(graph).hasIdThat().isEqualTo(event.getGraphId());
        assertThat(graph).hasVertex(event.getVertexId());
        classes.add(event.getClass());
    }

    @Subscribe
    public void edgeCreated(EdgeCreated event) {
        assertThat(graph).hasIdThat().isEqualTo(event.getGraphId());
        assertThat(graph).hasEdge(event.getFrom(), event.getTo()).hasIdThat().isEqualTo(event.getEdgeId());
        classes.add(event.getClass());
    }

    @Subscribe
    public void edgeRemoved(EdgeRemoved event) {
        assertThat(graph).hasIdThat().isEqualTo(event.getGraphId());
        assertThat(graph).hasNoEdge(event.getFrom(), event.getTo());
        event.getEdgeId().ifPresent(id -> assertThat(graph).hasNoEdge(id));
        classes.add(event.getClass());
    }

    @Subscribe
    public void otherEvent(Object event) {
        fail("Invalid event sent " + event);
    }
}
