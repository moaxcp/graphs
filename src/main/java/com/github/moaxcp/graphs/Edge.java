package com.github.moaxcp.graphs;

import java.util.Objects;

public class Edge extends Element {
    public Edge(Object from, Object to) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);
        attributes.put("from", from);
        attributes.put("to", to);
    }
    
    public Object getFrom() {
        return attributes.get("from");
    }

    public void setFrom(Object from) {
        throw new UnsupportedOperationException("Not yet implemented. Needs to create missing vertices in graph.");
    }
    
    public Object getTo() {
        return attributes.get("to");
    }

    public void setTo(Object to) {
        throw new UnsupportedOperationException("Not yet implemented. Needs to create missing vertices in graph.");
    }

    @Override
    public String toString() {
        return "Edge '" + getFrom() + "' to '" + getTo() + "' " + super.getAttributes();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof Edge)) {
            return false;
        }
        Edge edge = (Edge) obj;
        return (getFrom() == edge.getFrom() || getFrom() == edge.getTo()) && (getTo() == edge.getTo() || getTo() == edge.getFrom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFrom(), getTo());
    }
}
