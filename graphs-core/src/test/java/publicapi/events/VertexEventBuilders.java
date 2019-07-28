package publicapi.events;

import com.github.moaxcp.graphs.events.*;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
            .build();

        assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getVertexId()).isEqualTo("A");
    }

    @Test
    void testVertexRemoved() {
        var event = new VertexRemoved.Builder<String>()
            .graphId("graph")
            .vertexId("A")
            .build();

        assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getVertexId()).isEqualTo("A");
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
    void setNullOriginalProperties() {
        var builder = new VertexPropertiesEvent.Builder<String>()
                .graphId("graph")
                .vertexId("A");

        var exception = assertThrows(NullPointerException.class, () -> builder.originalProperties(null));
        assertThat(exception).hasMessageThat().isEqualTo("originalProperties must not be null.");
    }

    @Test
    void setNullNewProperties() {
        var builder = new VertexPropertiesEvent.Builder<String>()
                .graphId("graph")
                .vertexId("A");

        var exception = assertThrows(NullPointerException.class, () -> builder.newProperties(null));
        assertThat(exception).hasMessageThat().isEqualTo("newProperties must not be null.");
    }

    @Test
    void updateNullNewProperties() {
        var builder = new VertexPropertiesEvent.Builder<String>()
                .graphId("graph")
                .vertexId("A")
                .originalProperties(Map.of("name", "value"));

        var exception = assertThrows(NullPointerException.class, () -> builder.build());
        assertThat(exception).hasMessageThat().isEqualTo("newProperties must not be null.");
    }

    @Test
    void propertiesAdded() {
        var event = new VertexPropertiesEvent.Builder<String>()
                .graphId("graph")
                .vertexId("A")
                .newProperties(Map.of("name1", "value1", "name2", "value2"))
                .build();

        assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getVertexId()).isEqualTo("A");
        assertThat(event.getAddedProperties()).containsExactly("name1", "value1", "name2", "value2");
    }

    @Test
    void propertiesAddedOne() {
        var event = new VertexPropertiesEvent.Builder<String>()
                .graphId("graph")
                .vertexId("A")
                .originalProperties(Map.of("name1", "value1"))
                .newProperties(Map.of("name1", "valueA", "name2", "valueB"))
                .build();

        assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getVertexId()).isEqualTo("A");
        assertThat(event.getAddedProperties()).containsExactly("name2", "valueB");
    }

    @Test
    void propertiesAddedNone() {
        var event = new VertexPropertiesEvent.Builder<String>()
                .graphId("graph")
                .vertexId("A")
                .originalProperties(Map.of("name1", "value1", "name2", "value2"))
                .newProperties(Map.of("name1", "valueA", "name2", "valueB"))
                .build();

        assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getVertexId()).isEqualTo("A");
        assertThat(event.getAddedProperties()).isEmpty();
    }

    @Test
    void propertiesUpdatedNone() {
        var event = new VertexPropertiesEvent.Builder<String>()
                .graphId("graph")
                .vertexId("A")
                .originalProperties(Map.of("name1", "value1"))
                .newProperties(Map.of("name2", "value2"))
                .build();

        assertThat(event.getUpdatedProperties()).isEmpty();
    }

    @Test
    void propertiesUpdatedOne() {
        var event = new VertexPropertiesEvent.Builder<String>()
                .graphId("graph")
                .vertexId("A")
                .originalProperties(Map.of("name1", "value1"))
                .newProperties(Map.of("name1", "valueA"))
                .build();

        assertThat(event.getUpdatedProperties()).containsExactly("name1", new VertexPropertiesEvent.UpdatedProperty("name1", "value1", "valueA"));
    }

    @Test
    void propertiesUpdatedAll() {
        var event = new VertexPropertiesEvent.Builder<String>()
                .graphId("graph")
                .vertexId("A")
                .originalProperties(Map.of("name1", "value1", "name2", "value2"))
                .newProperties(Map.of("name1", "valueA", "name2", "valueB"))
                .build();

        assertThat(event.getUpdatedProperties()).containsExactly("name1", new VertexPropertiesEvent.UpdatedProperty("name1", "value1", "valueA"), "name2", new VertexPropertiesEvent.UpdatedProperty("name2", "value2", "valueB"));
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
