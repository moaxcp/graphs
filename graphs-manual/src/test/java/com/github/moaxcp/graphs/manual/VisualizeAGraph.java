package com.github.moaxcp.graphs.manual;

import com.github.moaxcp.graphs.DirectedGraph;
import com.github.moaxcp.graphs.events.GraphEvent;
import com.github.moaxcp.graphs.graphviz.SimpleGraphGif;
import com.github.moaxcp.graphs.greenrobot.DirectedEventGraph;
import java.io.IOException;
import java.nio.file.Path;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.junit.jupiter.api.Test;

import static com.github.moaxcp.graphs.graphviz.Dot.dot;

public class VisualizeAGraph {
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

    // tag::dot[]
    dot(graph).writeImage("src/docs/asciidoc/images/edgeMethods.png", "png");
    // end::dot[]
  }

  // tag::graph-subscriber[]
  public static class GraphSubscriber {
    private final SimpleGraphGif gif;
    public GraphSubscriber(SimpleGraphGif gif) {
      this.gif = gif;
    }

    @Subscribe
    public void event(GraphEvent<String> event) throws IOException {
      gif.addEvent(event);
    }
  }
  // end::graph-subscriber[]

  @Test
  void gif() throws IOException {

    // tag::gif[]
    var bus = new EventBus();
    var graph = new DirectedEventGraph<String>(bus);
    var gif = new SimpleGraphGif<>(graph, Path.of("src/docs/asciidoc/images/gifExample.gif"));
    bus.register(new GraphSubscriber(gif));

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
