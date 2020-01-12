package com.github.moaxcp.graphs.manual;

import com.github.moaxcp.graphs.*;
import org.junit.jupiter.api.*;

import java.io.*;

import static com.github.moaxcp.graphs.graphviz.Dot.*;

public class VertexMethods {
  @Test
  void edge_graph() throws IOException {
    var graph = new DirectedGraph<String>();

    // tag::getVertex[]
    graph.getVertex("A")
      .connectsTo("B")
      .connectsTo("D")
      .connectsFrom("D")
      .connectsTo("E");
    graph.getVertex("B")
      .connectsTo("C")
      .connectsTo("D");
    graph.getVertex("D")
      .connectsTo("E")
      .connectsTo("C")
      .connectsFrom("G");
    graph.getVertex("F")
      .connectsTo("G");
    // end::getVertex[]

    dot(graph).writeImage("src/docs/asciidoc/images/getVertexMethods.png", "png");
  }
}
