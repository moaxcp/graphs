package com.github.moaxcp.graphs.truth.greenrobot;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.events.*;
import org.greenrobot.eventbus.*;

import java.util.*;
import java.util.stream.*;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static java.util.stream.Collectors.*;

/**
 * Using this class depends on an eventbus that throws exceptions. Checking for updates is tricky. This still does
 * not verify old values in events.
 */
public class GraphEventCheck {

    private final List<Class> classes = new ArrayList<>();
    private final Graph actual;

    GraphEventCheck(Graph actual) {
        this.actual = actual;
    }

    List<Class> getEventClasses() {
        return Collections.unmodifiableList(classes);
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
    public void edgeIdAdded(EdgeIdAdded event) {
        assertThat(actual).hasEdge(event.getEdgeId()).hasIdThat().hasValue(event.getEdgeId());
        assertThat(actual).hasEdge(event.getFrom(), event.getTo());
        classes.add(event.getClass());
    }

    @Subscribe
    public void edgeIdRemoved(EdgeIdRemoved event) {
        assertThat(actual).hasEdge(event.getFrom(), event.getTo()).hasIdThat().isEmpty();
        classes.add(event.getClass());
    }

    @Subscribe
    public void edgeIdUpdated(EdgeIdUpdated event) {
        assertThat(event.getEdgeId()).isNotNull();
        assertThat(actual).hasEdge(event.getFrom(), event.getTo()).hasIdThat().hasValue(event.getEdgeId());
        classes.add(event.getClass());
    }

    @Subscribe
    public void edgeToUpdated(EdgeToUpdated event) {
        assertThat(actual).hasEdge(event.getFrom(), event.getTo());
        assertThat(actual).hasNoEdge(event.getFrom(), event.getOldTo());
        classes.add(event.getClass());
    }

    @Subscribe
    public void edgeFromUpdated(EdgeFromUpdated event) {
        assertThat(actual).hasEdge(event.getFrom(), event.getTo());
        assertThat(actual).hasNoEdge(event.getOldFrom(), event.getTo());
        classes.add(event.getClass());
    }

    @Subscribe
    public void vertexCreated(VertexCreated event) {
        assertThat(actual).hasIdThat().isEqualTo(event.getGraphId());
        assertThat(actual).hasVertex(event.getVertexId());
        classes.add(event.getClass());
    }

    @Subscribe
    public void vertexRemoved(VertexRemoved event) {
        assertThat(actual).hasIdThat().isEqualTo(event.getGraphId());
        assertThat(actual).hasNoVertex(event.getVertexId());
        classes.add(event.getClass());
    }

    @Subscribe
    public void vertexIdUpdated(VertexIdUpdated event) {
        assertThat(actual).hasNoVertex(event.getOldVertexId());
        assertThat(actual).hasVertex(event.getVertexId()).hasId(event.getVertexId());
        classes.add(event.getClass());
    }

    @Subscribe
    public void vertexPropertyRemoved(VertexPropertyRemoved event) {
        assertThat(actual).hasIdThat().isEqualTo(event.getGraphId());
        assertThat(actual).hasVertex(event.getVertexId()).withProperty(event.getName()).isEmpty();
        classes.add(event.getClass());
    }

    @Subscribe
    public void otherEvent(Object event) {
        List<Class<?>> supported = Stream.of(
            VertexCreated.class,
            VertexRemoved.class,
            VertexIdUpdated.class,
            VertexPropertyRemoved.class,
            EdgeCreated.class,
            EdgePropertyAdded.class,
            EdgePropertyRemoved.class,
            EdgePropertyUpdated.class,
            EdgeRemoved.class,
            EdgeIdAdded.class,
            EdgeIdRemoved.class,
            EdgeIdUpdated.class,
            EdgeFromUpdated.class,
            EdgeToUpdated.class)
            .collect(toList());
        assertThat(supported).contains(event.getClass());
    }
}
