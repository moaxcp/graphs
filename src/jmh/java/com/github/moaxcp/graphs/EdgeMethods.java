package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.SimpleGraph.Edge;
import java.util.Set;
import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
public class EdgeMethods {

    @Param({"2", "3", "4", "5", "6", "7", "8"})
    public int vertices;

    @Param({"true", "false"})
    public boolean directed;

    private SimpleGraph<Integer> graph;
    private Integer lastFrom;
    private Integer lastTo;

    @Setup(Level.Trial)
    public void setup() {
        if(directed) {
            graph = new DirectedGraph<>();
            for(int i = 0; i < vertices; i++) {
                for(int j = 0; j < vertices; j++) {
                    graph.edge(i, j);
                    graph.edge(j, i);
                    lastFrom = j;
                    lastTo = i;
                }
            }
        } else {
            graph = new UndirectedGraph<>();
            for(int i = 0; i < vertices; i++) {
                for(int j = 0; j < vertices; j++) {
                    graph.edge(i, j);
                    lastFrom = i;
                    lastTo = j;
                }
            }
        }
    }

    @Benchmark
    public Edge<Integer> findLastEdge() {
        return graph.edge(lastFrom, lastTo);
    }

    @Benchmark
    public Edge<Integer> findFirstEdge() {
        return graph.edge(0, 0);
    }

    @Benchmark
    public Set<Edge<Integer>> adjaentEdges() {
        return graph.vertex(lastTo).adjacentEdges();
    }

    @Benchmark
    public Set<Edge<Integer>> inEdges() {
        return graph.vertex(lastTo).inEdges();
    }

    @Benchmark
    public Set<Edge<Integer>> outEdges() {
        return graph.vertex(lastTo).outEdges();
    }
}
