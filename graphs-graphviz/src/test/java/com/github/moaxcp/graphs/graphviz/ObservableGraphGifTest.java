package com.github.moaxcp.graphs.graphviz;

import com.github.moaxcp.graphs.ObservableDirectedPropertyGraph;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ObservableGraphGifTest {
  @Test
  void addObserver() {
    var graph = new ObservableDirectedPropertyGraph<String>();
    var gif = new ObservableGraphGif<>(graph, Path.of("build/tmp/observablegraphgif.gif"));
    assertThat(graph.getObservers()).containsExactly(gif);
  }
}
