package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.*;

public interface GraphEvent {
    SimpleGraph getGraph();

    void setGraph(SimpleGraph graph);

    GraphEvent withGraph(SimpleGraph graph);

    void check();
}
