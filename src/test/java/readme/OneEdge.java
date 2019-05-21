package readme;

import com.github.moaxcp.graphs.DirectedGraph;
import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.UndirectedGraph;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.github.moaxcp.graphs.graphviz.Dot.dot;

public class OneEdge {
    @Test
    void directed() throws IOException {
        Path dir = Paths.get("images");
        Files.createDirectories(dir);
        FileOutputStream out = new FileOutputStream("images/oneEdgeDirected.png");
        Graph<String> graph = new DirectedGraph<>();
        graph.edge("A", "B");
        dot(graph).writeImage(out, "png");
    }

    @Test
    void undirected() throws IOException {
        Path dir = Paths.get("images");
        Files.createDirectories(dir);
        FileOutputStream out = new FileOutputStream("images/oneEdgeUndirected.png");
        Graph<String> graph = new UndirectedGraph<>();
        graph.edge("A", "B");
        dot(graph).writeImage(out, "png");
    }
}
