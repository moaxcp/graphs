package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.testframework.*;
import com.google.common.truth.*;

import java.util.*;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class PropertyGraphVertexProperties {

    @TestPropertyGraphs
    void defaultProperty(PropertyGraph<String> graph) {
        assertThat(graph).withVertexProperty("property").isEmpty();
    }

    @TestPropertyGraphs
    void setPropertyNullKey(PropertyGraph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.setVertexProperty(null, null));
        assertThat(thrown).hasMessageThat().isEqualTo("name is marked non-null but is null");
    }

    @TestPropertyGraphs
    void setPropertyNullValue(PropertyGraph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.setVertexProperty("property", null));
        assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
    }

    @TestPropertyGraphs
    void setProperty(PropertyGraph<String> graph) {
        graph.setVertexProperty("property", "value");
        assertThat(graph).withVertexProperty("property").hasValue("value");
    }

    @TestPropertyGraphs
    void setPropertyEmptyName(PropertyGraph<String> graph) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.setVertexProperty("", ""));
        assertThat(thrown).hasMessageThat().isEqualTo("name must not be empty.");
    }

    @TestPropertyGraphs
    void property(PropertyGraph<String> graph) {
        PropertyGraph next = graph.vertexProperty("property", "value");
        assertThat(graph).withVertexProperty("property").hasValue("value");
        assertThat(next).isSameInstanceAs(graph);
    }

    @TestPropertyGraphs
    void removePropertyMissing(PropertyGraph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeVertexProperty("property"));
        assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
    }

    @TestPropertyGraphs
    void removeProperty(PropertyGraph<String> graph) {
        graph.vertexProperty("property", "value");
        graph.removeVertexProperty("property");
        assertThat(graph).withVertexProperty("property").isEmpty();
    }

    @TestPropertyGraphs
    void propertyIsInherited(PropertyGraph<String> graph) {
        graph.vertexProperty("property", "value");
        var vertex = graph.getVertex("id");
        assertThat(vertex).withProperty("property").hasValue("value");
    }

    @TestPropertyGraphs
    void getProperties(PropertyGraph<String> graph) {
        graph.vertexProperty("property", "value");
        assertThat(graph.getVertexProperties()).containsExactly("property", "value");
    }

    @TestPropertyGraphs
    void findPropertyNull(PropertyGraph<String> graph) {
        var exception = assertThrows(NullPointerException.class, () -> graph.findVertexProperty(null));
        assertThat(exception).hasMessageThat().isEqualTo("name is marked non-null but is null");
    }

    @TestPropertyGraphs
    void findProperty(PropertyGraph<String> graph) {
        graph.vertexProperty("name", "value");
        Optional<String> result = graph.findVertexProperty("name");
        Truth8.assertThat(result).hasValue("value");
    }

    @TestPropertyGraphs
    void getPropertyNull(PropertyGraph<String> graph) {
        var exception = assertThrows(NullPointerException.class, () -> graph.getVertexProperty(null));
        assertThat(exception).hasMessageThat().isEqualTo("name is marked non-null but is null");
    }

    @TestPropertyGraphs
    void getProperty(PropertyGraph<String> graph) {
        graph.vertexProperty("name", "value");
        String value = graph.getVertexProperty("name");
        assertThat(value).isEqualTo("value");
    }
}
