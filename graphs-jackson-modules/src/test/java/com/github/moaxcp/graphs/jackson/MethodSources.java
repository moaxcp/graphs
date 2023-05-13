package com.github.moaxcp.graphs.jackson;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.moaxcp.graphs.DirectedPropertyGraph;
import com.github.moaxcp.graphs.UndirectedPropertyGraph;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class MethodSources {

  public static ObjectMapper mapper = new ObjectMapper().registerModule(new GraphsModule());

  public static Stream<ObjectMapper> inclusionMappers() {
    return List.of(new ObjectMapper().registerModule(new GraphsModule()).setDefaultPropertyInclusion(Include.NON_EMPTY),
        new ObjectMapper().registerModule(new GraphsModule()).setDefaultPropertyInclusion(Include.NON_NULL)).stream();
  }

  public static Stream<Arguments> mappersToGraphs() {
    return inclusionMappers().flatMap(mapper -> Stream.of(Arguments.of(mapper, new DirectedPropertyGraph<String>()), Arguments.of(mapper, new UndirectedPropertyGraph<String>())));
  }
}
