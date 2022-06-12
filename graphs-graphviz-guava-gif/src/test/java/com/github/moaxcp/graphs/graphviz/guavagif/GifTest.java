package com.github.moaxcp.graphs.graphviz.guavagif;

import com.github.moaxcp.graphs.events.GraphEvent;
import com.github.moaxcp.graphs.graphviz.SimpleGraphGif;
import com.github.moaxcp.graphs.guava.DirectedEventPropertyGraph;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GifTest {

  public static class GraphSubscriber {
    private final SimpleGraphGif<String> gif;
    public GraphSubscriber(SimpleGraphGif<String> gif) {
      this.gif = gif;
    }

    @Subscribe
    public void event(GraphEvent<String> event) throws IOException {
      gif.addEvent(event);
    }
  }

  @Test
  void create() {
    var bus = new EventBus();
    var graph = new DirectedEventPropertyGraph<String>(bus);
    var path = Path.of("build/tmp/testCreate.gif");
    var gif = new SimpleGraphGif<String>(graph, path);
    bus.register(new GraphSubscriber(gif));

    graph.vertex("A");
    graph.vertex("B");
    graph.edge("D", "C");
    graph.removeVertex("C");
    graph.removeVertex("D");
    graph.removeVertex("B");
    graph.removeVertex("A");

    gif.writeFile();

    assertThat(path).exists();
  }
}
