package publicapi;

import static com.github.moaxcp.graphs.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.github.moaxcp.graphs.SimpleGraph;
import testframework.SimpleGraphs;

public class GraphVertexProperties {

    @SimpleGraphs
    void defaultProperty(SimpleGraph graph) {
        assertThat(graph).withVertexProperty("property").isEmpty();
    }

    @SimpleGraphs
    void setPropertyNullKey(SimpleGraph graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.setVertexProperty(null, null));
        assertThat(thrown).hasMessageThat().isEqualTo("name must not be null.");
    }

    @SimpleGraphs
    void setPropertyNullValue(SimpleGraph graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.setVertexProperty("property", null));
        assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
    }

    @SimpleGraphs
    void setProperty(SimpleGraph graph) {
        graph.setVertexProperty("property", "value");
        assertThat(graph).withVertexProperty("property").hasValue("value");
    }

    @SimpleGraphs
    void setPropertyEmptyName(SimpleGraph graph) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.setVertexProperty("", ""));
        assertThat(thrown).hasMessage("name must not be empty.");
    }

    @SimpleGraphs
    void property(SimpleGraph graph) {
        SimpleGraph next = graph.vertexProperty("property", "value");
        assertThat(graph).withVertexProperty("property").hasValue("value");
        assertThat(next).isSameAs(graph);
    }

    @SimpleGraphs
    void removePropertyMissing(SimpleGraph graph) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.removeVertexProperty("property"));
        assertThat(thrown).hasMessageThat().isEqualTo("graph does not contain edge property named 'property'.");
    }

    @SimpleGraphs
    void removeProperty(SimpleGraph graph) {
        graph.vertexProperty("property", "value");
        graph.removeVertexProperty("property");
        assertThat(graph).withVertexProperty("property").isEmpty();
    }

    @SimpleGraphs
    void propertyIsInherited(SimpleGraph graph) {
        graph.vertexProperty("property", "value");
        var vertex = graph.vertex("id");
        assertThat(vertex).withProperty("property").hasValue("value");
    }
}
