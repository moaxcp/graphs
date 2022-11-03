package com.github.moaxcp.graphs.testframework;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@Retention(RetentionPolicy.RUNTIME)
@ParameterizedTest
@MethodSource("com.github.moaxcp.graphs.testframework.MethodSources#observablePropertyGraphs")
public @interface TestObservablePropertyGraphs {
}
