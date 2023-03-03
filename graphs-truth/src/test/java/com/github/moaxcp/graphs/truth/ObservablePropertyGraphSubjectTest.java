package com.github.moaxcp.graphs.truth;

import com.github.moaxcp.graphs.ObservableDirectedPropertyGraph;
import com.github.moaxcp.graphs.ObservablePropertyGraph;
import com.github.moaxcp.graphs.events.VertexCreatedEvent;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.Test;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;

public class ObservablePropertyGraphSubjectTest {
  @Test
  void actionCalledWithGraph() {
    var graph = new ObservableDirectedPropertyGraph<String>();
    var actionGraph = new AtomicReference<ObservablePropertyGraph<String>>();
    assertThat(graph).withAction(g -> actionGraph.set(g));
    assertThat(actionGraph.get()).isSameInstanceAs(graph);
  }

  @Test
  void actionContainsEvents() {
    var graph = new ObservableDirectedPropertyGraph<String>();
    assertThat(graph)
        .withAction(g -> g.vertex("A"))
        .containsExactly(VertexCreatedEvent.<String>builder().vertexId("A").build());
  }
}
