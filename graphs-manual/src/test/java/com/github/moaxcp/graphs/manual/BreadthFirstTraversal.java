package com.github.moaxcp.graphs.manual;

import com.github.moaxcp.graphs.DirectedPropertyGraph;
import com.github.moaxcp.graphs.ObservableDirectedPropertyGraph;
import com.github.moaxcp.graphs.PropertyGraph.Vertex;
import com.github.moaxcp.graphs.graphviz.ObservableGraphGif;
import java.nio.file.Path;
import java.util.Iterator;
import org.junit.jupiter.api.Test;

public class BreadthFirstTraversal {

  @Test
  void breadthFirstTraversal() {
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
    var gif = new ObservableGraphGif<>(graph, Path.of("src/docs/asciidoc/images/breadthFirstTraversal.gif"));

    // tag::breadthFirstIterator[]
    Iterator<Vertex<String>> iterator = graph.breadthFirstIterator("A");
    while(iterator.hasNext()) {
      Vertex<String> vertex = iterator.next();
      vertex.property("color", "green");
    }
    // end::breadthFirstIterator[]
    gif.writeFile();
  }

  @Test
  void breadthFirstStream() {
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
    // tag::breadthFirstStream[]
    graph.breadthFirstStream("A").forEach(v -> v.property("color", "green"));
    // end::breadthFirstStream[]
  }
}
