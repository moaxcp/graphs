package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.testframework.*;

import static com.github.moaxcp.graphs.truth.Truth.*;

public class GraphMethods {

  @SimpleGraphs
  void isEmpty(Graph<String> graph) {
    assertThat(graph).isEmpty();
  }
}
