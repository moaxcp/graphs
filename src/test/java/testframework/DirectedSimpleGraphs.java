package testframework;

import java.lang.annotation.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@Retention(RetentionPolicy.RUNTIME)
@ParameterizedTest
@MethodSource("testframework.MethodSources#directedEventSimpleGraphs")
public @interface DirectedSimpleGraphs {
}
