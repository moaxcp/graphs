package com.github.moaxcp.graphs.jackson;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@Retention(RetentionPolicy.RUNTIME)
@ParameterizedTest
@MethodSource("com.github.moaxcp.graphs.jackson.MethodSources#inclusionMappers")
public @interface InclusionMapperTest {
}
