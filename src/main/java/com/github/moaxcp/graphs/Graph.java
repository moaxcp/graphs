package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.event.EdgeAdded;
import com.github.moaxcp.graphs.event.EdgeAttributeAdded;
import com.github.moaxcp.graphs.event.EdgeRemoved;
import com.github.moaxcp.graphs.event.Event;
import de.muspellheim.eventbus.EventBus;

import java.util.*;
import java.util.function.Consumer;

public class Graph extends IdentifiedElement {
    protected Map<String, Vertex> vertices;
    protected LinkedHashSet<Edge> edges;
    protected EventBus bus = EventBus.getDefault();

    public class Edge extends FromToElement {
        public Edge(String from, String to) {
            super(from, to);
        }

        @Override
        public void setFrom(String from) {
            throw new UnsupportedOperationException("Not yet implemented. Needs to create missing vertices in graph.");
        }

        @Override
        public void setTo(String to) {
            throw new UnsupportedOperationException("Not yet implemented. Needs to create missing vertices in graph.");
        }

        @Override
        public Object put(String key, Object value) {
            if(containsKey(key)) {
                return super.put(key, value);
            }
            super.put(key, value);
            bus.publish(new EdgeAttributeAdded()
                    .withGraph(Graph.this).withEdge(this).withAttributeKey(key).withAttributeValue(value));
            return null;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == this) return true;
            if(!(obj instanceof Graph.Edge)) {
                return false;
            }
            Edge edge = (Edge) obj;
            return (Objects.equals(getFrom(), edge.getFrom()) || Objects.equals(getFrom(), edge.getTo())) && (Objects.equals(getTo(), edge.getTo()) || Objects.equals(getTo(), edge.getFrom()));
        }

        @Override
        public int hashCode() {
            return Objects.hash(getFrom(), getTo());
        }
    }

    public class Vertex extends IdentifiedElement {
        public Vertex(String id) {
            super(id);
        }

        @Override
        public void setId(String id) {
            throw new UnsupportedOperationException("Not yet implemented. Needs to change id in edges first.");
        }

        public final String toString() {
            return "Vertex '" + getId() + "' " + super.toString();
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == this) return true;
            if(!(obj instanceof Graph.Vertex)) {
                return false;
            }
            Vertex vertex = (Vertex) obj;
            return Objects.equals(getId(), vertex.getId());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId());
        }
    }

    public Graph(String id) {
        super(id);
        vertices = new LinkedHashMap<>();
        edges = new LinkedHashSet<>();
    }

    public Map<String, Vertex> getVertices() {
        return Collections.unmodifiableMap(vertices);
    }

    public Set<Edge> getEdges() {
        return Collections.unmodifiableSet(edges);
    }

    public Vertex vertex(String id) {
        var vertex = vertices.getOrDefault(id, new Vertex(id));
        if(!vertices.containsKey(id)) {
            vertices.put(id, vertex);
        }
        return vertex;
    }

    public void removeEdge(String from, String to) {
        var search = new Edge(from, to);
        findEdge(search).ifPresent(this::removeAndNotify);
    }

    private void removeAndNotify(Edge edge) {
        edges.remove(edge);
        publish(new EdgeRemoved().withGraph(this).withEdge(edge));
    }

    private Optional<Edge> findEdge(Edge search) {
        if(!edges.contains(search)) {
            return Optional.empty();
        }
        return edges.stream()
                .filter(search::equals)
                .findFirst();
    }

    public Edge edge(String from, String to) {
        var search = new Edge(from, to);
        return findEdge(search).orElseGet(() -> addEdge(search));
    }

    private Edge addEdge(Edge edge) {
        vertex(edge.getFrom());
        vertex(edge.getTo());
        edges.add(edge);
        publish(new EdgeAdded().withGraph(this).withEdge(edge));
        return edge;
    }

    void publish(Event event) {
        event.checkEvent();
        bus.publish(event);
    }

    public <T> void subscribe(Class<? extends T> eventType, Consumer<T> subscriber) {
        bus.subscribe(eventType, subscriber);
    }
}
