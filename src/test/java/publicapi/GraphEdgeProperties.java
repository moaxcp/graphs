package publicapi;

import static com.github.moaxcp.graphs.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.github.moaxcp.graphs.SimpleGraph;
import testframework.SimpleGraphs;

public class GraphEdgeProperties {

    @SimpleGraphs
    void defaultProperty(SimpleGraph graph) {
        assertThat(graph).withEdgeProperty("property").isEmpty();
    }

    @SimpleGraphs
    void setPropertyNullKey(SimpleGraph graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.setEdgeProperty(null, null));
        assertThat(thrown).hasMessageThat().isEqualTo("name must not be null.");
    }

    @SimpleGraphs
    void setPropertyNullValue(SimpleGraph graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.setEdgeProperty("property", null));
        assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
    }

    @SimpleGraphs
    void setPropertyEmptyName(SimpleGraph graph) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.setEdgeProperty("", ""));
        assertThat(thrown).hasMessage("name must not be empty.");
    }

    @SimpleGraphs
    void setProperty(SimpleGraph graph) {
        graph.setEdgeProperty("property", "value");
        assertThat(graph).withEdgeProperty("property").hasValue("value");
    }

    @SimpleGraphs
    void property(SimpleGraph graph) {
        SimpleGraph next = graph.edgeProperty("property", "value");
        assertThat(graph).withEdgeProperty("property").hasValue("value");
        assertThat(next).isSameAs(graph);
    }

    @SimpleGraphs
    void removePropertyMissing(SimpleGraph graph) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.removeEdgeProperty("property"));
        assertThat(thrown).hasMessageThat().isEqualTo("graph does not contain edge property named 'property'.");
    }

    @SimpleGraphs
    void removeProperty(SimpleGraph graph) {
        graph.edgeProperty("property", "value");
        graph.removeEdgeProperty("property");
        assertThat(graph).withEdgeProperty("property").isEmpty();
    }

    @SimpleGraphs
    void propertyIsInherited(SimpleGraph graph) {
        graph.edgeProperty("property", "value");
        var edge = graph.edge("A", "B");
        assertThat(edge).withProperty("property").hasValue("value");
    }
}
