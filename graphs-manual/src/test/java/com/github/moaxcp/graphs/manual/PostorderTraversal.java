package com.github.moaxcp.graphs.manual;

import com.github.moaxcp.graphs.DirectedGraph;
import com.github.moaxcp.graphs.Graph.Vertex;
import com.github.moaxcp.graphs.graphviz.GreenrobotGifSubscriber;
import com.github.moaxcp.graphs.greenrobot.DirectedEventGraph;
import java.nio.file.Path;
import java.util.Iterator;
import org.greenrobot.eventbus.EventBus;
import org.junit.jupiter.api.Test;

public class PostorderTraversal {

  @Test
  void postOrderTraversal() {
    var bus = new EventBus();
    var graph = new DirectedEventGraph<String>(bus);

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
    var gif = new GreenrobotGifSubscriber<>(graph, Path.of("src/docs/asciidoc/images/postOrderTraversal.gif"));

    // tag::postOrderIterator[]
    Iterator<Vertex<String>> iterator = graph.postOrderIterator("A");
    while(iterator.hasNext()) {
      Vertex<String> vertex = iterator.next();
      vertex.property("color", "green");
    }
    // end::postOrderIterator[]
    gif.writeFile();
  }

  @org.junit.jupiter.api.Test
  void postOrderStream() {
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
    // tag::postOrderStream[]
    graph.postOrderStream("A").forEach(v -> v.property("color", "green"));
    // end::postOrderStream[]
  }
}
