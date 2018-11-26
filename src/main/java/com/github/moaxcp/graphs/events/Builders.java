package com.github.moaxcp.graphs.events;

public class Builders {
    public static UndirectedGraphCreated.Builder undirectedGraphCreated() {
        return new UndirectedGraphCreated.Builder();
    }
    public static DirectedGraphCreated.Builder directedGraphCreated() {
        return new DirectedGraphCreated.Builder();
    }
}
