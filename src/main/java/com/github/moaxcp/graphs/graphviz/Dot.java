package com.github.moaxcp.graphs.graphviz;

import com.github.moaxcp.graphs.Graph;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import static java.util.Objects.requireNonNull;

public class Dot<ID> {

    private Graph<ID> graph;

    public static <ID> Dot<ID> dot(Graph<ID> graph) {
        return new Dot<>(graph);
    }

    private Dot(Graph<ID> graph) {
        this.graph = requireNonNull(graph, "graph must not be null.");
    }

    public void write(Writer writer) throws IOException {
        requireNonNull(writer, "writer must not be null.");
        PrintWriter printWriter = new PrintWriter(writer);
        if(graph.isDirected()) {
            printWriter.println("strict digraph {");
        } else {
            printWriter.println("strict graph {");
        }
        for(Graph.Edge<ID> edge : graph.getEdges()) {
            writeEdge(edge, printWriter);
        }

        for(Graph.Vertex<ID> vertex : graph.getVertices().values()) {
            writeVertex(vertex, printWriter);
        }
        printWriter.println("}");
    }

    private void writeEdge(Graph.Edge<ID> edge, PrintWriter writer) throws IOException {
        var connector = graph.isDirected() ? "->" : "--";
        writer.printf("  %s %s %s\n", edge.getFrom(), connector, edge.getTo());
    }

    private void writeVertex(Graph.Vertex<ID> vertex, PrintWriter writer) throws IOException {
        if((vertex.inEdges().size() > 0 || vertex.outEdges().size() > 0) && vertex.local().size() == 0) {
            return;
        }
        writer.append("  ").append(vertex.getId().toString()).append("\n");
    }

    public String toString() {
        StringWriter writer = new StringWriter();
        try {
            write(writer);
        } catch (IOException e) {
            throw new IllegalArgumentException("not expected", e);
        }
        return writer.toString();
    }
}
