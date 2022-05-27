package readme;

import com.github.moaxcp.graphs.DirectedPropertyGraph;
import com.github.moaxcp.graphs.PropertyGraph;
import com.github.moaxcp.graphs.UndirectedPropertyGraph;
import org.junit.jupiter.api.Test;

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
        PropertyGraph<String> graph = new DirectedPropertyGraph<>();
        graph.edge("A", "B");
        dot(graph).writeImage(out, "png");
    }

    @Test
    void undirected() throws IOException {
        Path dir = Paths.get("images");
        Files.createDirectories(dir);
        FileOutputStream out = new FileOutputStream("images/oneEdgeUndirected.png");
        PropertyGraph<String> graph = new UndirectedPropertyGraph<>();
        graph.edge("A", "B");
        dot(graph).writeImage(out, "png");
    }
}
