package com.github.moaxcp.graphs.manual;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.Graph.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.*;

import static com.github.moaxcp.graphs.graphviz.Dot.*;

public class PreorderTraversal {

  @Test
  void preOrderTraversal() throws IOException {

    var graph = new DirectedGraph<String>();

    graph.edge("A", "B")
      .edge("B", "C")
      .edge("B", "D")
      .edge("D", "E")
      .edge("D", "C")
      .edge("A", "D")
      .edge("D", "A")
      .edge("A", "E")
      .edge("F", "G")
      .edge("G", "D");

    // tag::preOrderIterator[]
    int visit = 1;
    Iterator<Vertex<String>> iterator = graph.preOrderIterator();
    while(iterator.hasNext()) {
      Vertex<String> vertex = iterator.next();
      vertex.property("label", vertex.findProperty("id").get().toString() + visit++);
    }
    // end::preOrderIterator[]

    dot(graph).writeImage("src/docs/asciidoc/images/preOrderTraversal.png", "png");
  }
}
