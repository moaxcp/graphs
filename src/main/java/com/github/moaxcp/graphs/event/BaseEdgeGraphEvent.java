package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.SimpleGraph.*;
import java.util.*;

public abstract class BaseEdgeGraphEvent extends BaseGraphEvent implements EdgeGraphEvent {
    private Edge edge;

    @Override
    public Edge getEdge() {
        return edge;
    }

    @Override
    public void setEdge(Edge edge) {
        this.edge = edge;
    }

    @Override
    public void check() {
        super.check();
        Objects.requireNonNull(edge);
    }
}
