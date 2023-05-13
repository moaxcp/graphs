package com.github.moaxcp.graphs.jackson;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.github.moaxcp.graphs.DirectedPropertyGraph;
import com.github.moaxcp.graphs.UndirectedPropertyGraph;

public class GraphDeserializers extends Deserializers.Base {

  @Override
  public JsonDeserializer<?> findBeanDeserializer(JavaType type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
    if(type.hasRawClass(DirectedPropertyGraph.class)) {
      return new DirectedPropertyGraphDeserializer();
    }
    if(type.hasRawClass(UndirectedPropertyGraph.class)) {
      return new UndirectedPropertyGraphDeserializer();
    }
    return super.findBeanDeserializer(type, config, beanDesc);
  }
}
