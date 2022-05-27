package com.github.moaxcp.graphs.graphviz.gif;

import com.github.moaxcp.gifbuilder.GifMethods;
import com.github.moaxcp.gifbuilder.GifSpec;
import com.github.moaxcp.graphs.PropertyGraph;
import com.github.moaxcp.graphs.events.GraphEvent;
import com.github.moaxcp.graphs.graphviz.Dot;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;

public class SimpleGraphGif<ID> {
  private final PropertyGraph<ID> graph;
  private final GifSpec.Builder spec;

  public SimpleGraphGif(PropertyGraph<ID> graph, Path file) {
    this.graph = graph;
    spec = GifSpec.builder().file(file);
  }

  public void addImage() {
    try {
      spec.addImages(GifMethods.image(Dot.dot(graph).toImage()));
    } catch(IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  public void addEvent(GraphEvent<ID> event) {
    addImage();
  }

  public void writeFile() {
    try {
      spec.build().create();
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }
}
