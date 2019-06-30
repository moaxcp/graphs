package com.github.moaxcp.graphs.testframework;

import com.github.moaxcp.graphs.DirectedGraph;
import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.UndirectedGraph;
import com.github.moaxcp.graphs.greenrobot.DirectedEventGraph;
import com.github.moaxcp.graphs.greenrobot.UndirectedEventGraph;
import org.greenrobot.eventbus.EventBus;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class MethodSources {

    public static EventBus testEventBus() {
        return EventBus.builder()
                .throwSubscriberException(true)
                .logNoSubscriberMessages(false)
                .build();
    }

    public static Stream<Graph<String>> simpleGraphs() {
        return Stream.of(
                new UndirectedGraph<>(),
                new DirectedGraph<>(),
                new UndirectedEventGraph<>(testEventBus()),
                new DirectedEventGraph<>(testEventBus()));
    }

    public static Stream<Graph<String>> undirectedSimpleGraphs() {
        return Stream.of(
                new UndirectedGraph<>(),
                new UndirectedEventGraph<>(testEventBus()));
    }

    public static Stream<Graph<String>> directedSimpleGraphs() {
        return Stream.of(
                new DirectedGraph<>(),
                new DirectedEventGraph<>(testEventBus()));
    }

    public static Stream<Graph<String>> nonEventSimpleGraphs() {
        return Stream.of(
            new UndirectedGraph<>(),
            new DirectedGraph<>());
    }

    public static Stream<Arguments> eventSimpleGraphs() {
        EventBus undirected = testEventBus();
        EventBus directed = testEventBus();
        return Stream.of(
                Arguments.arguments(new UndirectedEventGraph<>(undirected), undirected),
                Arguments.arguments(new DirectedEventGraph<>(directed), directed));
    }
}
