package com.github.moaxcp.graphs.jackson;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.github.moaxcp.graphs.PropertyGraph;

public class GraphSerializers extends Serializers.Base {
  @Override
  public JsonSerializer<?> findSerializer(SerializationConfig config, JavaType type, BeanDescription beanDesc) {
    Class<?> raw = type.getRawClass();
    if (PropertyGraph.class.isAssignableFrom(raw)) {
      return PropertyGraphSerializer.INSTANCE;
    }
    return super.findSerializer(config, type, beanDesc);
  }
}
