package com.github.moaxcp.graphs.manual;

import com.github.moaxcp.graphs.events.GraphEvent;
import com.github.moaxcp.graphs.graphviz.SimpleGraphGif;
import java.io.IOException;
import org.greenrobot.eventbus.Subscribe;

// tag::graph-subscriber[]
public class GraphSubscriber {
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