package publicapi;

import static com.github.moaxcp.graphs.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.github.moaxcp.graphs.SimpleGraph;
import testframework.*;

public class EdgeTest {
    @SimpleGraphs
    void setId(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        edge.setId("id");
        assertThat(graph).hasEdge("A", "B").hasIdThat().hasValue("id");
        assertThat(graph).hasEdge("id").isSameAs(edge);
    }

    @SimpleGraphs
    void changeId(SimpleGraph graph) {
        var edge = graph.edge("A", "B").id("id");
        edge.setId("id2");
        assertThat(graph).hasEdge("id2").isSameAs(edge);
        assertThat(graph).hasEdge("A", "B").hasIdThat().hasValue("id2");
        assertThat(graph).hasEdge("id2").hasIdThat().hasValue("id2");
        assertThat(graph).hasNoEdge("id");
    }

    @SimpleGraphs
    void setIdNullNoId(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        edge.setId(null);
        assertThat(edge).hasNoId();
    }

    @SimpleGraphs
    void setIdNullRemovesId(SimpleGraph graph) {
        var edge = graph.edge("A", "to").id("id");
        edge.setId(null);
        assertThat(edge).hasNoId();
        assertThat(graph).hasNoEdge("id");
    }

    @SimpleGraphs
    void id(SimpleGraph graph) {
        var edge = graph.edge("A", "B").id("id");
        assertThat(graph).hasEdge("A", "B").hasIdThat().hasValue("id");
        assertThat(graph).hasEdge("id").isSameAs(edge);
    }

    @SimpleGraphs
    void setFrom(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        edge.setFrom("C");
        assertThat(graph).hasVertex("C");
        assertThat(edge).hasFromThat().isEqualTo("C");
    }

    @SimpleGraphs
    void setFromNull(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        Throwable thrown = assertThrows(NullPointerException.class, () -> edge.setFrom(null));
        assertThat(thrown).hasMessage("from must not be null.");
    }

    @SimpleGraphs
    void setFromCreatesNewVertex(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        edge.setFrom("C");
        assertThat(graph).hasVertex("C");
    }

    @SimpleGraphs
    void from(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        var result = edge.from("C");
        assertThat(edge).hasFromThat().isEqualTo("C");
        assertThat(result).isSameAs(edge);
    }

    @SimpleGraphs
    void fromGet(SimpleGraph graph) {
        assertThat(graph.edge("A", "B").from()).isEqualTo("A");
    }

    @SimpleGraphs
    void setTo(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        edge.setTo("C");
        assertThat(graph).hasVertex("C");
        assertThat(edge).hasToThat().isEqualTo("C");
    }

    @SimpleGraphs
    void setToNull(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        Throwable thrown = assertThrows(NullPointerException.class, () -> edge.setTo(null));
        assertThat(thrown).hasMessage("to must not be null.");
    }

    @SimpleGraphs
    void setToCreatesNewVertex(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        edge.setTo("C");
        assertThat(graph).hasVertex("C");
    }

    @SimpleGraphs
    void to(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        var result = edge.to("C");
        assertThat(edge).hasToThat().isEqualTo("C");
        assertThat(result).isSameAs(edge);
    }

    @SimpleGraphs
    void toGet(SimpleGraph graph) {
        assertThat(graph.edge("A", "B").to()).isEqualTo("B");
    }

    @SimpleGraphs
    void fromVertex(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        var vertex = graph.findVertex("A").get();
        assertThat(edge.fromVertex()).isEqualTo(vertex);
    }

    @SimpleGraphs
    void toVertex(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        var vertex = graph.findVertex("B").get();
        assertThat(edge.toVertex()).isEqualTo(vertex);
    }

    @SimpleGraphs
    void setProperty(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        edge.setProperty("key", "value");
        assertThat(edge).withProperty("key").hasValue("value");
    }

    @SimpleGraphs
    void setPropertyFrom(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        edge.setProperty("from", "C");
        assertThat(edge).hasFromThat().isEqualTo("C");
    }

    @SimpleGraphs
    void setPropertyTo(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        edge.setProperty("to", "C");
        assertThat(edge).hasToThat().isEqualTo("C");
    }

    @SimpleGraphs
    void setPropertyId(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        edge.setProperty("id", "id");
        assertThat(edge).hasIdThat().hasValue("id");
    }

    @SimpleGraphs
    void removeProperty(SimpleGraph graph) {
        var edge = graph.edge("A", "B").property("name", "value");
        edge.removeProperty("name");
        assertThat(edge).withProperty("name").isEmpty();
    }

    @SimpleGraphs
    void removeIdProperty(SimpleGraph graph) {
        var edge = graph.edge("from", "to").id("id");
        edge.removeProperty("id");
        assertThat(edge).hasNoId();
        assertThat(graph).hasNoEdge("id");
    }

    @SimpleGraphs
    void removeFromProperty(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> edge.removeProperty("from"));
        assertThat(thrown).hasMessage("'from' cannot be removed.");
    }

    @SimpleGraphs
    void removeToProperty(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> edge.removeProperty("to"));
        assertThat(thrown).hasMessage("'to' cannot be removed.");
    }

    @SimpleGraphs
    void equalsSameObject(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        assertThat(edge.equals(edge)).isTrue();
    }

    @SimpleGraphs
    void equalsNotEdge(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        assertThat(edge.equals(1)).isFalse();
    }

    @SimpleGraphs
    void equalsNotSame(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        assertThat(edge.equals("C", "D")).isFalse();
    }

    @SimpleGraphs
    void equalsSameFrom(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        assertThat(edge.equals("A", "C")).isFalse();
    }

    @SimpleGraphs
    void equalsSame(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        assertThat(edge.equals("A", "B")).isTrue();
    }

    @UndirectedSimpleGraphs
    void equalsOpposite(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        assertThat(edge.equals("B", "A")).isTrue();
    }

    @SimpleGraphs
    void removeByFromTo(SimpleGraph graph) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.removeEdge("A", "B"));
        assertThat(thrown).hasMessageThat().isEqualTo("edge from 'A' to 'B' not found.");
    }

    @SimpleGraphs
    void removeById(SimpleGraph graph) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.removeEdge("id"));
        assertThat(thrown).hasMessageThat().isEqualTo("edge with id 'id' not found.");
    }
}
