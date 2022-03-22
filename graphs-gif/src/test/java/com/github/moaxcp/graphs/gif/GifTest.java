package com.github.moaxcp.graphs.gif;

import com.github.moaxcp.graphs.events.GraphEvent;
import com.github.moaxcp.graphs.greenrobot.DirectedEventGraph;
import java.io.IOException;
import java.nio.file.Path;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.junit.jupiter.api.Test;

public class GifTest {

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

  @Test
  void create() throws IOException {
    var bus = new EventBus();
    var graph = new DirectedEventGraph<String>(bus);
    var gif = new SimpleGraphGif<String>(graph, Path.of("testCreate.gif"));
    bus.register(new GraphSubscriber(gif));

    graph.vertex("A");
    graph.vertex("B");
    graph.edge("D", "C");
    graph.removeVertex("C");
    graph.removeVertex("D");
    graph.removeVertex("B");
    graph.removeVertex("A");

    gif.writeFile();
  }
}
