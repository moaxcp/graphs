package com.github.moaxcp.graphs.graphviz;

import com.github.moaxcp.gifbuilder.GifMethods;
import com.github.moaxcp.gifbuilder.GifSpec;
import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.events.GraphEvent;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;

import static com.github.moaxcp.graphs.graphviz.Dot.dot;

public class SimpleGraphGif<ID> {
  private final Graph<ID> graph;
  private final GifSpec.Builder spec;

  public SimpleGraphGif(Graph<ID> graph, Path file) {
    this.graph = graph;
    spec = GifSpec.builder().file(file);
  }

  public void addImage() {
    try {
      spec.addImages(GifMethods.image(dot(graph).toImage()));
    } catch(IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  public void addEvent(GraphEvent<ID> event) {
    addImage();
  }

  public void writeFile() throws IOException {
    spec.build().create();
  }
}
