package com.github.moaxcp.graphs;

import static com.github.moaxcp.graphs.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import com.github.moaxcp.graphs.events.*;
import java.util.*;
import java.util.stream.*;
import org.greenrobot.eventbus.Subscribe;

/**
 * Using this class depends on an eventbus that throws exceptions.
 */
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
    public void graphPropertyAdded(GraphPropertyAdded event) {
        assertThat(graph).hasIdThat().isEqualTo(event.getGraphId());
        assertThat(graph).withProperty(event.getName()).hasValue(event.getValue());
        classes.add(event.getClass());
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
    public void edgePropertyAdded(EdgePropertyAdded event) {
        assertThat(graph).hasIdThat().isEqualTo(event.getGraphId());
        assertThat(graph).hasEdge(event.getFrom(), event.getTo()).hasIdThat().isEqualTo(event.getEdgeId());
        assertThat(graph).hasEdge(event.getFrom(), event.getTo())
                .withLocalProperty(event.getName())
                .isEqualTo(event.getValue());
        classes.add(event.getClass());
    }

    @Subscribe
    public void edgePropertyRemoved(EdgePropertyRemoved event) {
        assertThat(graph).hasIdThat().isEqualTo(event.getGraphId());
        assertThat(graph).hasEdge(event.getFrom(), event.getTo()).hasIdThat().isEqualTo(event.getEdgeId());
        assertThat(graph).hasEdge(event.getFrom(), event.getTo())
            .withLocalProperty(event.getName())
            .isNull();
        classes.add(event.getClass());
    }

    @Subscribe
    public void edgePropertyUpdated(EdgePropertyUpdated event) {
        assertThat(graph).hasIdThat().isEqualTo(event.getGraphId());
        assertThat(graph).hasEdge(event.getFrom(), event.getTo()).hasIdThat().isEqualTo(event.getEdgeId());
        assertThat(graph).hasEdge(event.getFrom(), event.getTo())
            .withLocalProperty(event.getName())
            .isEqualTo(event.getValue());
        assertThat(event.getOldValue()).isNotNull();
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
        List<Class<? extends GraphEvent>> supported = Stream.of(GraphPropertyAdded.class, VertexCreated.class, EdgeCreated.class, EdgePropertyAdded.class, EdgePropertyRemoved.class, EdgePropertyUpdated.class, EdgeRemoved.class).collect(Collectors.toList());
        assertThat(supported).contains(event.getClass());
    }
}
