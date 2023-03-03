package com.github.moaxcp.graphs.graphviz;

import com.github.moaxcp.graphs.ObservablePropertyGraph;
import com.github.moaxcp.graphs.PropertyGraphObserver;
import com.github.moaxcp.graphs.events.GraphEvent;
import java.nio.file.Path;

public class ObservableGraphGif<ID> extends SimpleGraphGif<ID> implements PropertyGraphObserver<ID> {

  public ObservableGraphGif(ObservablePropertyGraph<ID> graph, Path file) {
    super(graph, file);
    graph.addObserver(this);
  }

  @Override
  public void graphEvent(GraphEvent<ID> event) {
    addImage();
  }
}
