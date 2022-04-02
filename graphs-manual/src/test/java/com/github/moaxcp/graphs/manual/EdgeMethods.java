package com.github.moaxcp.graphs.manual;

import com.github.moaxcp.graphs.DirectedGraph;
import java.io.IOException;
import org.junit.jupiter.api.Test;

import static com.github.moaxcp.graphs.graphviz.Dot.dot;

class EdgeMethods {
  @Test
  void edge_graph() throws IOException {
    var graph = new DirectedGraph<String>();

    // tag::edge[]
    graph.edge("A", "B", "color", "black")
      .edge("B", "C", "color", "black")
      .edge("B", "D", "color", "black")
      .edge("D", "E", "color", "black")
      .edge("D", "C", "color", "gray", "style", "dotted")
      .edge("A", "D", "color", "gray", "style", "dotted")
      .edge("D", "A", "color", "red")
      .edge("A", "E", "color", "black")
      .edge("F", "G", "color", "black")
      .edge("G", "D", "color", "blue");
    // end::edge[]
  }

  @Test
  void getEdge_graph() throws IOException {
    var graph = new DirectedGraph<String>();

    // tag::getEdge[]
    graph.getEdge("A", "B", "color", "black");
    graph.getEdge("B", "C", "color", "black");
    graph.getEdge("B", "D", "color", "black");
    graph.getEdge("D", "E", "color", "black");
    graph.getEdge("D", "C").property("color", "gray").property("style", "dotted");
    graph.getEdge("A", "D").property("color", "gray", "style", "dotted");
    graph.getEdge("D", "A").property("color", "red");
    graph.getEdge("A", "E", "color", "black");
    graph.getEdge("F", "G", "color", "black");
    graph.getEdge("G", "D").property("color", "blue");
    // end::getEdge[]

    dot(graph).writeImage("src/docs/asciidoc/images/getEdgeMethods.png", "png");
  }
}
