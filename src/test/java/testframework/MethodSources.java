package testframework;

import com.github.moaxcp.graphs.*;
import java.util.stream.Stream;
import org.greenrobot.eventbus.EventBus;

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

    public static Stream<EventGraph<String>> eventSimpleGraphs() {
        return Stream.of(
                new UndirectedEventGraph<>(testEventBus()),
                new DirectedEventGraph<>(testEventBus()));
    }
}
