package com.github.moaxcp.graphs.graphviz.guavagif;

import com.github.moaxcp.graphs.events.GraphEvent;
import com.github.moaxcp.graphs.graphviz.SimpleGraphGif;
import com.github.moaxcp.graphs.guava.AbstractEventPropertyGraph;
import com.google.common.eventbus.Subscribe;
import java.nio.file.Path;

public class GuavaGifSubscriber<ID> {
  private final SimpleGraphGif<ID> gif;

  public GuavaGifSubscriber(AbstractEventPropertyGraph<ID> graph, Path file) {
    gif = new SimpleGraphGif<>(graph, file);
    gif.addImage();
    graph.getBus().register(this);
  }

  public void writeFile() {
    gif.writeFile();
  }

  @Subscribe
  public void event(GraphEvent<ID> event) {
    gif.addEvent(event);
  }
}
