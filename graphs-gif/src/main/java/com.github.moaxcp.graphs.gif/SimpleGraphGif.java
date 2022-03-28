package com.github.moaxcp.graphs.gif;

import com.github.moaxcp.gifbuilder.GifSpec;
import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.events.GraphEvent;
import java.io.IOException;
import java.nio.file.Path;

import static com.github.moaxcp.gifbuilder.GifMethods.image;
import static com.github.moaxcp.graphs.graphviz.Dot.dot;

public class SimpleGraphGif<ID> {
  private final Graph<ID> graph;
  private final GifSpec.Builder spec;

  public SimpleGraphGif(Graph<ID> graph, Path file) {
    this.graph = graph;
    spec = GifSpec.builder().file(file);
  }

  public void addEvent(GraphEvent<ID> event) throws IOException {
    spec.addImages(image(dot(graph).toImage()));
  }

  public void writeFile() throws IOException {
    spec.build().create();
  }
}