package com.github.moaxcp.graphs;

import static com.github.moaxcp.graphs.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import com.github.moaxcp.graphs.events.*;
import java.util.*;
import java.util.stream.*;
import org.greenrobot.eventbus.Subscribe;

/**
 * Using this class depends on an eventbus that throws exceptions. Checking for updates is tricky. This still does
 * not verify old values in events.
 */
public class GraphEventCheck {

    private final List<Class> classes = new ArrayList<>();
    private final SimpleGraph actual;

    public GraphEventCheck(SimpleGraph actual) {
        this.actual = actual;
    }

    public List<Class> getEventClasses() {
        return Collections.unmodifiableList(classes);
    }

    @Subscribe
    public void graphPropertyAdded(GraphPropertyAdded event) {
        assertThat(actual).hasIdThat().isEqualTo(event.getGraphId());
        assertThat(actual).withProperty(event.getName()).hasValue(event.getValue());
        classes.add(event.getClass());
    }

    @Subscribe
    public void vertexCreated(VertexCreated event) {
        assertThat(actual).hasIdThat().isEqualTo(event.getGraphId());
        assertThat(actual).hasVertex(event.getVertexId());
        classes.add(event.getClass());
    }

    @Subscribe
    public void edgeCreated(EdgeCreated event) {
        assertThat(actual).hasIdThat().isEqualTo(event.getGraphId());
        assertThat(actual).hasEdge(event.getFrom(), event.getTo()).hasIdThat().isEqualTo(event.getEdgeId());
        classes.add(event.getClass());
    }

    @Subscribe
    public void edgePropertyAdded(EdgePropertyAdded event) {
        assertThat(actual).hasIdThat().isEqualTo(event.getGraphId());
        assertThat(actual).hasEdge(event.getFrom(), event.getTo()).hasIdThat().isEqualTo(event.getEdgeId());
        assertThat(actual).hasEdge(event.getFrom(), event.getTo())
                .withLocalProperty(event.getName())
                .isEqualTo(event.getValue());
        classes.add(event.getClass());
    }

    @Subscribe
    public void edgePropertyRemoved(EdgePropertyRemoved event) {
        assertThat(actual).hasIdThat().isEqualTo(event.getGraphId());
        assertThat(actual).hasEdge(event.getFrom(), event.getTo()).hasIdThat().isEqualTo(event.getEdgeId());
        assertThat(actual).hasEdge(event.getFrom(), event.getTo())
            .withLocalProperty(event.getName())
            .isNull();
        classes.add(event.getClass());
    }

    @Subscribe
    public void edgePropertyUpdated(EdgePropertyUpdated event) {
        assertThat(actual).hasIdThat().isEqualTo(event.getGraphId());
        assertThat(actual).hasEdge(event.getFrom(), event.getTo()).hasIdThat().isEqualTo(event.getEdgeId());
        assertThat(actual).hasEdge(event.getFrom(), event.getTo())
            .withLocalProperty(event.getName())
            .isEqualTo(event.getValue());
        assertThat(event.getOldValue()).isNotNull();
        classes.add(event.getClass());
    }

    @Subscribe
    public void edgeRemoved(EdgeRemoved event) {
        assertThat(actual).hasIdThat().isEqualTo(event.getGraphId());
        assertThat(actual).hasNoEdge(event.getFrom(), event.getTo());
        event.getEdgeId().ifPresent(id -> assertThat(actual).hasNoEdge(id));
        classes.add(event.getClass());
    }

    @Subscribe
    public void otherEvent(Object event) {
        List<Class<? extends GraphEvent>> supported = Stream.of(GraphPropertyAdded.class, VertexCreated.class, EdgeCreated.class, EdgePropertyAdded.class, EdgePropertyRemoved.class, EdgePropertyUpdated.class, EdgeRemoved.class).collect(Collectors.toList());
        assertThat(supported).contains(event.getClass());
    }
}
