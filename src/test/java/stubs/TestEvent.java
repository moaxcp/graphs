package stubs;

import com.github.moaxcp.graphs.greenrobot.UndirectedGraph;
import com.github.moaxcp.graphs.event.GraphEvent;

public class TestEvent implements GraphEvent {

    @Override
    public UndirectedGraph getGraph() {
        return null;
    }

    @Override
    public void setGraph(UndirectedGraph graph) {

    }

    @Override
    public GraphEvent withGraph(UndirectedGraph graph) {
        return null;
    }

    @Override
    public void check() {

    }
}
