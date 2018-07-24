package com.github.moaxcp.graphs;

import de.muspellheim.eventbus.EventBus;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static com.google.common.truth.Truth.assertThat;

public class AllOptionallyIdentifiedInheritingElementsTest {
    static Map<String, Object> inherited = new HashMap<>();

    static Stream<OptionallyIdentifiedInheritingElement> elements() {
        return Stream.of(
                new TestOptionallyIdentifiedInheritingElement(inherited, EventBus.getDefault()));
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testGetIdNull(OptionallyIdentifiedInheritingElement element) {
        assertThat(element.getId()).isNull();
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testSetId(OptionallyIdentifiedInheritingElement element) {
        element.setId("id");
        assertThat(element.getId()).isEqualTo("id");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testSetIdNull(OptionallyIdentifiedInheritingElement element) {
        element.setId(null);
        assertThat(element.getId()).isNull();
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testSetIdFromValueToNull(OptionallyIdentifiedInheritingElement element) {
        element.setId("id");
        element.setId(null);
        assertThat(element.getId()).isNull();
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testSetPropertyId(OptionallyIdentifiedInheritingElement element) {
        element.setProperty("id", "id");
        assertThat(element.getId()).isEqualTo("id");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testSetProperty(OptionallyIdentifiedInheritingElement element) {
        element.setProperty("key", "value");
        assertThat(element.getProperty("key")).isEqualTo("value");
    }
}
