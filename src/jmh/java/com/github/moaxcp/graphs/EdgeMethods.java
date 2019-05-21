package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.Graph.Edge;
import com.github.moaxcp.graphs.Graph.Vertex;
import org.openjdk.jmh.annotations.*;

import java.util.Optional;
import java.util.Set;

@State(Scope.Thread)
public class EdgeMethods {

    @Param({"2", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100"})
    public int vertices;

    @Param({"true", "false"})
    public boolean directed;

    private Graph<Integer> graph;
    private Vertex<Integer> vertex;
    private Integer lastFrom;
    private Integer lastTo;

    @Setup(Level.Trial)
    public void setup() {
        if(directed) {
            graph = new DirectedGraph<>();
            for(int i = 0; i < vertices; i++) {
                for(int j = 0; j < vertices; j++) {
                    graph.edge(i, j);
                    vertex = graph.edge(j, i).toVertex();
                    lastFrom = j;
                    lastTo = i;
                }
            }
        } else {
            graph = new UndirectedGraph<>();
            for(int i = 0; i < vertices; i++) {
                for(int j = 0; j < vertices; j++) {
                    vertex = graph.edge(i, j).toVertex();
                    lastFrom = i;
                    lastTo = j;
                }
            }
        }
        System.out.println("edges: " + graph.getEdges().size());
    }

    @Benchmark
    public Optional<Edge<Integer>> findFirstEdge() {
        return graph.findEdge(0, 0);
    }

    @Benchmark
    public Optional<Edge<Integer>> findLastEdge() {
        return graph.findEdge(lastFrom, lastTo);
    }

    @Benchmark
    public Set<Edge<Integer>> adjacentEdges() {
        return vertex.adjacentEdges();
    }

    @Benchmark
    public Set<Edge<Integer>> inEdges() {
        return vertex.inEdges();
    }

    @Benchmark
    public Set<Edge<Integer>> outEdges() {
        return vertex.outEdges();
    }
}
