package stubs;

import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.event.GraphEvent;

public class TestEvent implements GraphEvent {

    @Override
    public Graph getGraph() {
        return null;
    }

    @Override
    public void setGraph(Graph graph) {

    }

    @Override
    public GraphEvent withGraph(Graph graph) {
        return null;
    }

    @Override
    public void check() {

    }
}
