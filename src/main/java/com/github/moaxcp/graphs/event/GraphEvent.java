package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.greenrobot.UndirectedGraph;

public interface GraphEvent {
    UndirectedGraph getGraph();

    void setGraph(UndirectedGraph graph);

    GraphEvent withGraph(UndirectedGraph graph);

    void check();
}
