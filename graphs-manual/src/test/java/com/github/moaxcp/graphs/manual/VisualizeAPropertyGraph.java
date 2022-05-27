package com.github.moaxcp.graphs.manual;

import com.github.moaxcp.graphs.DirectedPropertyGraph;
import com.github.moaxcp.graphs.graphviz.greenrobotgif.GreenrobotGifSubscriber;
import com.github.moaxcp.graphs.greenrobot.DirectedEventPropertyGraph;
import java.io.IOException;
import java.nio.file.Path;
import org.greenrobot.eventbus.EventBus;
import org.junit.jupiter.api.Test;

import static com.github.moaxcp.graphs.graphviz.Dot.dot;

public class VisualizeAPropertyGraph {
  @Test
  void edge_graph() throws IOException {
    var graph = new DirectedPropertyGraph<String>();

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

    // tag::dot[]
    dot(graph).writeImage("src/docs/asciidoc/images/edgeMethods.png", "png");
    // end::dot[]
  }

  @Test
   void gif() throws IOException {

    // tag::gif[]
    var graph = new DirectedEventPropertyGraph<String>(new EventBus());
    var gif = new GreenrobotGifSubscriber<>(graph, Path.of("src/docs/asciidoc/images/gifExample.gif"));

    graph.vertex("A");
    graph.vertex("B");
    graph.edge("D", "C");
    graph.removeVertex("C");
    graph.removeVertex("D");
    graph.removeVertex("B");
    graph.removeVertex("A");

    gif.writeFile();
    // end::gif[]
  }
}
