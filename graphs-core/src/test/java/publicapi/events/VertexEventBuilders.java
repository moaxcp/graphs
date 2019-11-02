package publicapi.events;

import com.github.moaxcp.graphs.events.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class VertexEventBuilders {

  @Test
  void vertexCreatedEventMissingId() {
    Throwable thrown = assertThrows(NullPointerException.class, () -> new VertexCreated.Builder<String>().build());
    assertThat(thrown).hasMessageThat().isEqualTo("vertexId must not be null.");
  }

  @Test
  void vertexRemovedEventMissingId() {
    Throwable thrown = assertThrows(NullPointerException.class, () -> new VertexRemoved.Builder<String>().build());
    assertThat(thrown).hasMessageThat().isEqualTo("vertexId must not be null.");
  }

  @Test
  void vertexIdUpdatedMissingId() {
    Throwable thrown = assertThrows(NullPointerException.class, () -> new VertexIdUpdated.Builder<String>().build());
    assertThat(thrown).hasMessageThat().isEqualTo("vertexId must not be null.");
  }

  @Test
  void vertexIdUpdatedMissingOldId() {
    Throwable thrown = assertThrows(NullPointerException.class, () -> new VertexIdUpdated.Builder<String>().vertexId("id1").build());
    assertThat(thrown).hasMessageThat().isEqualTo("oldVertexId must not be null.");
  }

  @Test
  void testVertexCreated() {
    var event = new VertexCreated.Builder<String>()
      .graphId("graph")
      .vertexId("A")
      .properties(Map.of("name1", "value1"))
      .build();

    assertThat(event.getGraphId()).hasValue("graph");
    assertThat(event.getVertexId()).isEqualTo("A");
    assertThat(event.getProperties()).containsExactly("name1", "value1");
  }

  @Test
  void testVertexRemoved() {
    var event = new VertexRemoved.Builder<String>()
      .graphId("graph")
      .vertexId("A")
      .properties(Map.of("name1", "value1"))
      .build();

    assertThat(event.getGraphId()).hasValue("graph");
    assertThat(event.getVertexId()).isEqualTo("A");
    assertThat(event.getProperties()).containsExactly("name1", "value1");
  }

  @Test
  void testVertexIdUpdated() {
    var event = new VertexIdUpdated.Builder<String>()
      .graphId("graph")
      .vertexId("id1")
      .oldVertexId("id2")
      .build();

    assertThat(event.getGraphId()).hasValue("graph");
    assertThat(event.getVertexId()).isEqualTo("id1");
    assertThat(event.getOldVertexId()).isEqualTo("id2");
  }

  @Test
  void testVertexPropertyRemoved() {
    var event = new VertexPropertyRemoved.Builder<String>()
      .graphId("graph")
      .vertexId("A")
      .name("name")
      .value("value")
      .build();

    assertThat(event.getGraphId()).hasValue("graph");
    assertThat(event.getName()).isEqualTo("name");
    assertThat(event.getValue()).isEqualTo("value");
  }
}
