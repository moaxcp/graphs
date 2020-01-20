package com.github.moaxcp.graphs.graphviz;

import com.github.moaxcp.graphs.*;

import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import java.util.Map.*;

import static java.lang.String.*;
import static java.util.Objects.*;
import static java.util.stream.Collectors.*;

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
                    .filter(e -> !e.getKey().equals("id"))
                    .map(this::attributeString)
                    .collect(joining("\n  ", "  ", "\n"));
            writer.append(joined);
        }
    }

    private void writeAttributes(Writer writer, Map<String, Object> attributes) throws IOException {
        if(attributes.isEmpty()) {
            return;
        }
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

        var attributes = edge.local().entrySet().stream()
          .filter(e -> !e.getKey().equals("id"))
          .filter(e -> !e.getKey().equals("to"))
          .filter(e -> !e.getKey().equals("from"))
          .collect(toMap(Entry::getKey, Entry::getValue));

        if(!attributes.isEmpty()) {
            writer.append(" ");
            writeAttributes(writer, attributes);
        }
        writer.append("\n");
    }

    private void writeVertex(Graph.Vertex<ID> vertex, PrintWriter writer) throws IOException {

        var attributes = vertex.local().entrySet().stream()
          .filter(e -> !e.getKey().equals("id"))
          .collect(toMap(Entry::getKey, Entry::getValue));

        if((vertex.inEdges().size() > 0 || vertex.outEdges().size() > 0) && attributes.isEmpty()) {
            return;
        }
        writer.append("  ").append(vertex.getId().toString());

        if(!attributes.isEmpty()) {
            writer.append(" ");
            writeAttributes(writer, attributes);
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
        BufferedImage image = ImageIO.read(process.getInputStream());
        String error = new String(process.getErrorStream().readAllBytes());
        if(!error.isBlank()) {
            throw new IllegalStateException(toString() + "\n" + error);
        }
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

    public void writeImage(String fileName, String format) throws IOException {
        File file = new File(fileName);
        file.getParentFile().mkdirs();
        FileOutputStream output = new FileOutputStream(file);
        writeImage(output, format);
    }
}
