package com.github.moaxcp.graphs.testframework;

import com.github.moaxcp.graphs.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static com.github.moaxcp.graphs.testframework.MethodSources.*;
import static java.util.stream.Collectors.*;

public class MethodSourcesTest {
  @Test
  void testSimpleGraphs() {
    List<PropertyGraph<String>> result = simpleGraphs().collect(toList());
  }
}
