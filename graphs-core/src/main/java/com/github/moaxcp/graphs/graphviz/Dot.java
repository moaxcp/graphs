package com.github.moaxcp.graphs.graphviz;

import com.github.moaxcp.graphs.Graph;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Map;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.joining;

public class Dot<ID> {

    private Graph<ID> graph;
    private String type;

    public static <ID> Dot<ID> dot(Graph<ID> graph) {
        return new Dot<>(graph);
    }

    private Dot(Graph<ID> graph) {
        this.graph = requireNonNull(graph, "graph must not be null.");
        type = "png";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Dot type(String type) {
        setType(type);
        return this;
    }

    public void write(Writer writer) throws IOException {
        requireNonNull(writer, "writer must not be null.");
        PrintWriter printWriter = new PrintWriter(writer);
        if(graph.isDirected()) {
            printWriter.println("strict digraph {");
        } else {
            printWriter.println("strict graph {");
        }
        writeGraphAttributes(writer);
        writeVertexAttributes(writer);
        writeEdgeAttributes(writer);
        for(Graph.Edge<ID> edge : graph.getEdges()) {
            writeEdge(edge, printWriter);
        }

        for(Graph.Vertex<ID> vertex : graph.getVertices().values()) {
            writeVertex(vertex, printWriter);
        }
        printWriter.println("}");
    }

    private void writeGraphAttributes(Writer writer) throws IOException {
        if(!graph.getProperties().isEmpty()) {
            var joined = graph.getProperties().entrySet().stream()
                    .map(this::attributeString)
                    .collect(joining("\n  ", "  ", "\n"));
            writer.append(joined);
        }
    }

    private void writeAttributes(Writer writer, Map<String, Object> attributes) throws IOException {
        var joined = attributes.entrySet().stream()
                .map(this::attributeString)
                .collect(joining(", ", "[", "]"));
        writer.append(joined);
    }

    private String attributeString(Map.Entry<String, Object> e) {
        return format("%s=%s", e.getKey(), e.getValue());
    }

    private void writeVertexAttributes(Writer writer) throws IOException {
        if(!graph.getVertexProperties().isEmpty()) {
            writer.append("  node ");
            writeAttributes(writer, graph.getVertexProperties());
            writer.append("\n");
        }
    }

    private void writeEdgeAttributes(Writer writer) throws IOException {
        if(!graph.getEdgeProperties().isEmpty()) {
            writer.append("  edge ");
            writeAttributes(writer, graph.getEdgeProperties());
            writer.append("\n");
        }

    }

    private void writeEdge(Graph.Edge<ID> edge, PrintWriter writer) throws IOException {
        var connector = graph.isDirected() ? "->" : "--";
        writer.printf("  %s %s %s", edge.getFrom(), connector, edge.getTo());
        if(!edge.local().isEmpty()) {
            writer.append(" ");
            writeAttributes(writer, edge.local());
        }
        writer.append("\n");
    }

    private void writeVertex(Graph.Vertex<ID> vertex, PrintWriter writer) throws IOException {
        if((vertex.inEdges().size() > 0 || vertex.outEdges().size() > 0) && vertex.local().size() == 0) {
            return;
        }
        writer.append("  ").append(vertex.getId().toString());
        if(!vertex.local().isEmpty()) {
            writer.append(" ");
            writeAttributes(writer, vertex.local());
        }
        writer.append("\n");
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

    public BufferedImage toImage() throws IOException {
        var command = "dot";
        if(graph.isDirected()) {
            command = "neato";
        }
        ProcessBuilder builder = new ProcessBuilder(command, "-T" + type);
        Process process = builder.start();
        try(PrintWriter writer = new PrintWriter(process.getOutputStream())) {
            writer.append(toString());
        }
        String error = new String(process.getErrorStream().readAllBytes());
        BufferedImage image = ImageIO.read(process.getInputStream());
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return image;
    }

    public void writeImage(OutputStream out, String format) throws IOException {
        ImageIO.write(toImage(), format, out);
    }
}
