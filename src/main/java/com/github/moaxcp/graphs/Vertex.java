package com.github.moaxcp.graphs;

import java.util.Objects;

public class Vertex extends IdentifiedElement {
    public Vertex(String id) {
        super(id);
    }

    public void setId(Object id) {
        throw new UnsupportedOperationException("Not yet implemented. Needs to change id in edges first.");
    }

    public final String toString() {
        return "Vertex '" + getId() + "' " + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof Vertex)) {
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
