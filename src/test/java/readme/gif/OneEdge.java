package readme.gif;

import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.graphviz.GifBuilder;
import testframework.SimpleGraphs;

import java.io.IOException;
import java.nio.file.Paths;

import static com.github.moaxcp.graphs.graphviz.Dot.dot;

public class OneEdge {
    @SimpleGraphs
    public void gif(Graph<String> graph) throws IOException {
        GifBuilder gif = new GifBuilder()
                .file(Paths.get("images/oneEdge-" + graph.getClass().getSimpleName() + ".gif"))
                .delay(100)
                .loop(0);
        graph.vertex("A");
        gif.image(dot(graph).type("gif").toImage());
        graph.vertex("B");
        gif.image(dot(graph).type("gif").toImage());
        graph.edge("A", "B");
        gif.image(dot(graph).type("gif").toImage());
        gif.build();
    }
}
