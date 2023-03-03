package com.github.moaxcp.graphs.truth;

import com.github.moaxcp.graphs.ObservablePropertyGraph;
import com.github.moaxcp.graphs.PropertyGraph;
import com.github.moaxcp.graphs.PropertyGraph.Edge;
import com.github.moaxcp.graphs.PropertyGraph.Vertex;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Truth {
    public static PropertyGraphSubject assertThat(PropertyGraph graph) {
        return PropertyGraphSubject.assertThat(graph);
    }
    public static ObservablePropertyGraphSubject assertThat(ObservablePropertyGraph graph) {
        return ObservablePropertyGraphSubject.assertThat(graph);
    }

    public static EdgeSubject assertThat(Edge edge) {
        return EdgeSubject.assertThat(edge);
    }

    public static VertexSubject assertThat(Vertex vertex) {
        return VertexSubject.assertThat(vertex);
    }
}
