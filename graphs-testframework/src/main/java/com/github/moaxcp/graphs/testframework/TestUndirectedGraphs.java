package com.github.moaxcp.graphs.testframework;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@ParameterizedTest
@MethodSource("com.github.moaxcp.graphs.testframework.MethodSources#undirectedPropertyGraphs")
public @interface TestUndirectedGraphs {
}
