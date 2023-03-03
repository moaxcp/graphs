package com.github.moaxcp.graphs.manual;

import com.github.moaxcp.graphs.DirectedPropertyGraph;
import com.github.moaxcp.graphs.ObservableDirectedPropertyGraph;
import com.github.moaxcp.graphs.PropertyGraph.Vertex;
import com.github.moaxcp.graphs.graphviz.ObservableGraphGif;
import java.nio.file.Path;
import java.util.Iterator;
import org.junit.jupiter.api.Test;

public class ReversePostorderTraversal {

  @Test
  void reversePostOrderTraversal() {
    var graph = new ObservableDirectedPropertyGraph<String>();

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
    var gif = new ObservableGraphGif<>(graph, Path.of("src/docs/asciidoc/images/reversePostOrderTraversal.gif"));

    // tag::reversePostOrderIterator[]
    Iterator<Vertex<String>> iterator = graph.reversePostOrderIterator("A");
    while(iterator.hasNext()) {
      Vertex<String> vertex = iterator.next();
      vertex.property("color", "green");
    }
    // end::reversePostOrderIterator[]
    gif.writeFile();
  }

  @Test
  void postOrderStream() {
    var graph = new DirectedPropertyGraph<String>();

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
    // tag::reversePostOrderStream[]
    graph.reversePostOrderStream("A").forEach(v -> v.property("color", "green"));
    // end::reversePostOrderStream[]
  }
}
