package publicapi;

import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.testframework.SimpleGraphs;

import java.util.Map;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GraphEdgeMethods {

    @SimpleGraphs
    void edge(Graph<String> graph) {
        Graph<String> r = graph.edge("A", "B");
        assertThat(r).isSameInstanceAs(graph);
        assertThat(graph).hasEdge("A", "B");
    }

    @SimpleGraphs
    void getEdge(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        assertThat(graph).hasEdge("A", "B").isSameInstanceAs(edge);
        assertThat(edge).hasToThat().isEqualTo("B");
        assertThat(edge).hasFromThat().isEqualTo("A");
    }

    @SimpleGraphs
    void getEdgeExisting(Graph<String> graph) {
        var edge1 = graph.getEdge("A", "B");
        var edge2 = graph.getEdge("A", "B");
        assertThat(edge1).isSameInstanceAs(edge2);
    }

    @SimpleGraphs
    void getEdgeHasNoId(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        assertThat(edge).hasIdThat().isEmpty();
    }

    @SimpleGraphs
    void getEdgeFromIsSet(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        assertThat(edge).hasFromThat().isEqualTo("A");
    }

    @SimpleGraphs
    void getEdgeToIsSet(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        assertThat(edge).hasToThat().isEqualTo("B");
    }

    @SimpleGraphs
    void getEdgeProperty1(Graph<String> graph) {
        graph.getEdge("A", "B", "name1", "value1");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1");
    }

    @SimpleGraphs
    void getEdgeProperty1Exists(Graph<String> graph) {
        graph.getEdge("A", "B");
        graph.getEdge("A", "B", "name1", "value1");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1");
    }

    @SimpleGraphs
    void getEdgeProperty2(Graph<String> graph) {
        graph.getEdge("A", "B", "name1", "value1", "name2", "value2");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2");
    }

    @SimpleGraphs
    void getEdgeProperty2Exists(Graph<String> graph) {
        graph.getEdge("A", "B");
        graph.getEdge("A", "B", "name1", "value1", "name2", "value2");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2");
    }

    @SimpleGraphs
    void getEdgeProperty3(Graph<String> graph) {
        graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3");
    }

    @SimpleGraphs
    void getEdgeProperty3Exists(Graph<String> graph) {
        graph.getEdge("A", "B");
        graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3");
    }

    @SimpleGraphs
    void getEdgeProperty4(Graph<String> graph) {
        graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
    }

    @SimpleGraphs
    void getEdgeProperty4Exists(Graph<String> graph) {
        graph.getEdge("A", "B");
        graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
    }

    @SimpleGraphs
    void getEdgeProperty5(Graph<String> graph) {
        graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
    }

    @SimpleGraphs
    void getEdgeProperty5Exists(Graph<String> graph) {
        graph.getEdge("A", "B");
        graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
    }

    @SimpleGraphs
    void getEdgeProperty6(Graph<String> graph) {
        graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
    }

    @SimpleGraphs
    void getEdgeProperty6Exists(Graph<String> graph) {
        graph.getEdge("A", "B");
        graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
    }

    @SimpleGraphs
    void getEdgeProperty7(Graph<String> graph) {
        graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
    }

    @SimpleGraphs
    void getEdgeProperty7Exists(Graph<String> graph) {
        graph.getEdge("A", "B");
        graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
    }

    @SimpleGraphs
    void getEdgeProperty8(Graph<String> graph) {
        graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
    }

    @SimpleGraphs
    void getEdgeProperty8Exists(Graph<String> graph) {
        graph.getEdge("A", "B");
        graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
    }

    @SimpleGraphs
    void getEdgeProperty9(Graph<String> graph) {
        graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
    }

    @SimpleGraphs
    void getEdgeProperty9Exists(Graph<String> graph) {
        graph.getEdge("A", "B");
        graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
    }

    @SimpleGraphs
    void getEdgeProperty10(Graph<String> graph) {
        graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
    }

    @SimpleGraphs
    void getEdgeProperty10Exists(Graph<String> graph) {
        graph.getEdge("A", "B");
        graph.getEdge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
    }

    @SimpleGraphs
    void getEdgeWithMapProperties(Graph<String> graph) {
        var edge = graph.getEdge("A", "B", Map.of("name1", "value1", "name2", "value2"));

        assertThat(edge).withProperty("name1").hasValue("value1");
        assertThat(edge).withProperty("name2").hasValue("value2");
        assertThat(graph).hasEdge("A", "B").withProperty("name1").hasValue("value1");
        assertThat(graph).hasEdge("A", "B").withProperty("name2").hasValue("value2");
    }

    @SimpleGraphs
    void getEdgeWithMapPropertiesExisting(Graph<String> graph) {
        graph.edge("A", "B");
        graph.getEdge("A", "B", Map.of("name1", "value1", "name2", "value2"));

        assertThat(graph).hasEdge("A", "B").withProperty("name1").hasValue("value1");
        assertThat(graph).hasEdge("A", "B").withProperty("name2").hasValue("value2");
    }

    @SimpleGraphs
    void edgeProperty1(Graph<String> graph) {
        graph.edge("A", "B", "name1", "value1");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1");
    }

    @SimpleGraphs
    void edgeProperty1Exists(Graph<String> graph) {
        graph.edge("A", "B");
        graph.edge("A", "B", "name1", "value1");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1");
    }

    @SimpleGraphs
    void edgeProperty2(Graph<String> graph) {
        graph.edge("A", "B", "name1", "value1", "name2", "value2");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2");
    }

    @SimpleGraphs
    void edgeProperty2Exists(Graph<String> graph) {
        graph.edge("A", "B");
        graph.edge("A", "B", "name1", "value1", "name2", "value2");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2");
    }

    @SimpleGraphs
    void edgeProperty3(Graph<String> graph) {
        graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3");
    }

    @SimpleGraphs
    void edgeProperty3Exists(Graph<String> graph) {
        graph.edge("A", "B");
        graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3");
    }

    @SimpleGraphs
    void edgeProperty4(Graph<String> graph) {
        graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
    }

    @SimpleGraphs
    void edgeProperty4Exists(Graph<String> graph) {
        graph.edge("A", "B");
        graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4");
    }

    @SimpleGraphs
    void edgeProperty5(Graph<String> graph) {
        graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
    }

    @SimpleGraphs
    void edgeProperty5Exists(Graph<String> graph) {
        graph.edge("A", "B");
        graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5");
    }

    @SimpleGraphs
    void edgeProperty6(Graph<String> graph) {
        graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
    }

    @SimpleGraphs
    void edgeProperty6Exists(Graph<String> graph) {
        graph.edge("A", "B");
        graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6");
    }

    @SimpleGraphs
    void edgeProperty7(Graph<String> graph) {
        graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
    }

    @SimpleGraphs
    void edgeProperty7Exists(Graph<String> graph) {
        graph.edge("A", "B");
        graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7");
    }

    @SimpleGraphs
    void edgeProperty8(Graph<String> graph) {
        graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
    }

    @SimpleGraphs
    void edgeProperty8Exists(Graph<String> graph) {
        graph.edge("A", "B");
        graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8");
    }

    @SimpleGraphs
    void edgeProperty9(Graph<String> graph) {
        graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
    }

    @SimpleGraphs
    void edgeProperty9Exists(Graph<String> graph) {
        graph.edge("A", "B");
        graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9");
    }

    @SimpleGraphs
    void edgeProperty10(Graph<String> graph) {
        graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
    }

    @SimpleGraphs
    void edgeProperty10Exists(Graph<String> graph) {
        graph.edge("A", "B");
        graph.edge("A", "B", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");

        assertThat(graph).hasEdge("A", "B").withLocal().containsExactly("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10");
    }

    @SimpleGraphs
    void edgeWithMapProperties(Graph<String> graph) {
        graph.edge("A", "B", Map.of("name1", "value1", "name2", "value2"));

        assertThat(graph).hasEdge("A", "B").withProperty("name1").hasValue("value1");
        assertThat(graph).hasEdge("A", "B").withProperty("name2").hasValue("value2");
    }

    @SimpleGraphs
    void edgeWithMapPropertiesExisting(Graph<String> graph) {
        graph.edge("A", "B");
        graph.edge("A", "B", Map.of("name1", "value1", "name2", "value2"));

        assertThat(graph).hasEdge("A", "B").withProperty("name1").hasValue("value1");
        assertThat(graph).hasEdge("A", "B").withProperty("name2").hasValue("value2");
    }

    @SimpleGraphs
    void removeEdge(Graph<String> graph) {
        graph.edge("A", "B");
        graph.removeEdge("A", "B");
        assertThat(graph).hasNoEdge("A", "B");
    }

    @SimpleGraphs
    void removeEdgeThatDoesNotExist(Graph<String> graph) {
        graph.vertex("A");
        graph.vertex("B");
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.removeEdge("A", "B"));
        assertThat(thrown).hasMessageThat().isEqualTo("edge from 'A' to 'B' not found.");
    }

    @SimpleGraphs
    void removeEdgeWithIdNullId(Graph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeEdge(null));
        assertThat(thrown).hasMessageThat().isEqualTo("id must not be null.");
    }

    @SimpleGraphs
    void removeEdgeWithId(Graph<String> graph) {
        graph.getEdge("A", "B").id("id");
        graph.removeEdge("id");
        assertThat(graph).hasNoEdge("id");
    }

    @SimpleGraphs
    void removeEdgeWithNoId(Graph<String> graph) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.removeEdge("id"));
        assertThat(thrown).hasMessageThat().isEqualTo("edge with id 'id' not found.");
    }

    @SimpleGraphs
    void removeWithNullId(Graph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeEdge(null));
        assertThat(thrown).hasMessageThat().isEqualTo("id must not be null.");
    }

    @SimpleGraphs
    void removeByFromTo(Graph<String> graph) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.removeEdge("A", "B"));
        assertThat(thrown).hasMessageThat().isEqualTo("edge from 'A' to 'B' not found.");
    }

    @SimpleGraphs
    void removeWithNullFrom(Graph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeEdge(null, "B"));
        assertThat(thrown).hasMessageThat().isEqualTo("from must not be null.");
    }

    @SimpleGraphs
    void removeWithNullTo(Graph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeEdge("A", null));
        assertThat(thrown).hasMessageThat().isEqualTo("to must not be null.");
    }

    @SimpleGraphs
    void findEdgeMissingFrom(Graph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () ->graph.findEdge(null, null));
        assertThat(thrown).hasMessageThat().isEqualTo("from must not be null.");
    }

    @SimpleGraphs
    void findEdgeMissingTo(Graph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () ->graph.findEdge("A", null));
        assertThat(thrown).hasMessageThat().isEqualTo("to must not be null.");
    }

    @SimpleGraphs
    void findEdge(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        assertThat(graph.findEdge("A", "B")).hasValue(edge);
    }

    @SimpleGraphs
    void findEdgeMissingId(Graph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () ->graph.findEdge(null));
        assertThat(thrown).hasMessageThat().isEqualTo("id must not be null.");
    }

    @SimpleGraphs
    void findEdgeById(Graph<String> graph) {
        var edge = graph.getEdge("A", "B").id("id");
        assertThat(graph.findEdge("id")).hasValue(edge);
    }
}
