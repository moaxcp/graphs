package publicapi;

import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.testframework.SimpleGraphs;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GraphVertexProperties {

    @SimpleGraphs
    void defaultProperty(Graph graph) {
        assertThat(graph).withVertexProperty("property").isEmpty();
    }

    @SimpleGraphs
    void setPropertyNullKey(Graph graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.setVertexProperty(null, null));
        assertThat(thrown).hasMessageThat().isEqualTo("name is marked non-null but is null");
    }

    @SimpleGraphs
    void setPropertyNullValue(Graph graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.setVertexProperty("property", null));
        assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
    }

    @SimpleGraphs
    void setProperty(Graph graph) {
        graph.setVertexProperty("property", "value");
        assertThat(graph).withVertexProperty("property").hasValue("value");
    }

    @SimpleGraphs
    void setPropertyEmptyName(Graph graph) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.setVertexProperty("", ""));
        assertThat(thrown).hasMessageThat().isEqualTo("name must not be empty.");
    }

    @SimpleGraphs
    void property(Graph graph) {
        Graph next = graph.vertexProperty("property", "value");
        assertThat(graph).withVertexProperty("property").hasValue("value");
        assertThat(next).isSameInstanceAs(graph);
    }

    @SimpleGraphs
    void removePropertyMissing(Graph graph) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.removeVertexProperty("property"));
        assertThat(thrown).hasMessageThat().isEqualTo("local properties do not contain name 'property'.");
    }

    @SimpleGraphs
    void removeProperty(Graph graph) {
        graph.vertexProperty("property", "value");
        graph.removeVertexProperty("property");
        assertThat(graph).withVertexProperty("property").isEmpty();
    }

    @SimpleGraphs
    void propertyIsInherited(Graph graph) {
        graph.vertexProperty("property", "value");
        var vertex = graph.getVertex("id");
        assertThat(vertex).withProperty("property").hasValue("value");
    }

    @SimpleGraphs
    void getProperties(Graph graph) {
        graph.vertexProperty("property", "value");
        assertThat(graph.getVertexProperties()).containsExactly("property", "value");
    }
}
