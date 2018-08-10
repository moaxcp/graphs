package com.github.moaxcp.graphs;

import org.greenrobot.eventbus.EventBus;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.google.common.truth.Truth.assertThat;

public class AllOptionallyIdentifiedElementsTest {

    static Stream<OptionallyIdentifiedElement> elements() {
        return Stream.of(
                new TestOptionallyIdentifiedElement(EventBus.getDefault()),
                new Graph());
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testGetIdNull(OptionallyIdentifiedElement element) {
        assertThat(element.getId()).isNull();
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testSetId(OptionallyIdentifiedElement element) {
        element.setId("id");
        assertThat(element.getId()).isEqualTo("id");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testSetIdNull(OptionallyIdentifiedElement element) {
        element.setId(null);
        assertThat(element.getId()).isNull();
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testSetIdFromValueToNull(OptionallyIdentifiedElement element) {
        element.setId("id");
        element.setId(null);
        assertThat(element.getId()).isNull();
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testSetPropertyId(OptionallyIdentifiedElement element) {
        element.setProperty("id", "id");
        assertThat(element.getId()).isEqualTo("id");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testSetProperty(OptionallyIdentifiedElement element) {
        element.setProperty("key", "value");
        assertThat(element.getProperty("key")).isEqualTo("value");
    }
}
