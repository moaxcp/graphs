package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.*;
import java.util.*;

public abstract class BaseGraphEvent implements GraphEvent {

    private SimpleGraph graph;

    @Override
    public SimpleGraph getGraph() {
        return graph;
    }

    @Override
    public void setGraph(SimpleGraph graph) {
        this.graph = graph;
    }

    @Override
    public void check() {
        Objects.requireNonNull(graph);
    }
}
