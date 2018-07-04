package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.event.EdgeAdded;
import de.muspellheim.eventbus.EventBus;

import java.util.*;
import java.util.function.Consumer;

public class Graph<K> extends IdentifiedElement<K> {
    protected Map<K, Vertex> vertices;
    protected LinkedHashSet<Edge> edges;
    protected EventBus bus = EventBus.getDefault();

    public class Edge extends Element {
        public Edge(K from, K to) {
            Objects.requireNonNull(from);
            Objects.requireNonNull(to);
            attributes.put("from", from);
            attributes.put("to", to);
        }

        public K getFrom() {
            return (K) attributes.get("from");
        }

        public void setFrom(K from) {
            throw new UnsupportedOperationException("Not yet implemented. Needs to create missing vertices in graph.");
        }

        public K getTo() {
            return (K) attributes.get("to");
        }

        public void setTo(K to) {
            throw new UnsupportedOperationException("Not yet implemented. Needs to create missing vertices in graph.");
        }

        @Override
        public String toString() {
            return "Edge '" + getFrom() + "' to '" + getTo() + "' " + super.getAttributes();
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

    public class Vertex extends IdentifiedElement<K> {
        public Vertex(K id) {
            super(id);
        }

        @Override
        public void setId(K id) {
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

    public Graph(K id) {
        super(id);
        vertices = new LinkedHashMap<>();
        edges = new LinkedHashSet<>();
    }

    public Map<K, Vertex> getVertices() {
        return Collections.unmodifiableMap(vertices);
    }

    public Set<Edge> getEdges() {
        return Collections.unmodifiableSet(edges);
    }

    public Vertex vertex(K id) {
        var vertex = vertices.getOrDefault(id, new Vertex(id));
        if(!vertices.containsKey(id)) {
            vertices.put(id, vertex);
        }
        return vertex;
    }

    public Edge edge(K from, K to) {
        var search = new Edge(from, to);
        if(edges.contains(search)) {
            for (Edge edge : edges) {
                if (search.equals(edge)) {
                    return edge;
                }
            }
        }
        vertex(from);
        vertex(to);
        edges.add(search);
        bus.publish(new EdgeAdded<>(this, search));
        return search;
    }

    public <T> void subscribe(Class<? extends T> eventType, Consumer<T> subscriber) {
        bus.subscribe(eventType, subscriber);
    }
}
