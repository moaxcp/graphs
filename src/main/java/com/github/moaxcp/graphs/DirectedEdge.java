package com.github.moaxcp.graphs;

public class DirectedEdge extends Edge {
    public DirectedEdge(Object from, Object to) {
        super(from, to);
    }

    @Override
    public String toString() {
        return "DirectedEdge '" + getFrom() + "' to '" + getTo() + "' " + super.getAttributes();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof DirectedEdge)) {
            return false;
        }
        DirectedEdge edge = (DirectedEdge) obj;
        return getFrom() == edge.getFrom() && getTo() == edge.getTo();
    }
}
