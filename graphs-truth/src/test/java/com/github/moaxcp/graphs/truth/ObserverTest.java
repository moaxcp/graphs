package com.github.moaxcp.graphs.truth;

import com.github.moaxcp.graphs.events.DirectedGraphCreatedEvent;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class ObserverTest {
  @Test
  void addEvent() {
    var observer = new Observer<String>();
    var event = DirectedGraphCreatedEvent.<String>builder().build();
    observer.graphEvent(event);
    assertThat(observer.getEvents()).containsExactly(event);
  }
}
