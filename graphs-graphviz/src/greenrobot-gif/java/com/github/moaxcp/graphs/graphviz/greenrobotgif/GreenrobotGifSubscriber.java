package com.github.moaxcp.graphs.graphviz.greenrobotgif;

import com.github.moaxcp.graphs.events.GraphEvent;
import com.github.moaxcp.graphs.graphviz.gif.SimpleGraphGif;
import com.github.moaxcp.graphs.greenrobot.AbstractEventPropertyGraph;
import java.nio.file.Path;
import org.greenrobot.eventbus.Subscribe;

public class GreenrobotGifSubscriber<ID> {
  private final SimpleGraphGif<ID> gif;

  public GreenrobotGifSubscriber(AbstractEventPropertyGraph<ID> graph, Path file) {
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
