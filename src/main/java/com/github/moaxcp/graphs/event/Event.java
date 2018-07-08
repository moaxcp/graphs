package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.Graph;

public abstract class Event {

    private Graph graph;

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public Event withGraph(Graph graph) {
        setGraph(graph);
        return this;
    }
    /**
     * @throws IllegalStateException when this GraphEven is invalid.
     */
    public abstract void checkEvent();
}
