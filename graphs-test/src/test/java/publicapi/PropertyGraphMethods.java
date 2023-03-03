package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.testframework.*;

import static com.github.moaxcp.graphs.truth.Truth.*;

public class PropertyGraphMethods {

  @TestPropertyGraphs
  void isEmpty(PropertyGraph<String> graph) {
    assertThat(graph).isEmpty();
  }
}
