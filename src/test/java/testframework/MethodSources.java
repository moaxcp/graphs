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

    public static Stream<SimpleGraph<String>> simpleGraphs() {
        return Stream.of(
                new UndirectedGraph<>(),
                new DirectedGraph<>(),
                new UndirectedEventGraph<>(testEventBus()),
                new DirectedEventGraph<>(testEventBus()));
    }

    public static Stream<SimpleGraph<String>> undirectedSimpleGraphs() {
        return Stream.of(
                new UndirectedGraph<>(),
                new UndirectedEventGraph<>(testEventBus()));
    }

    public static Stream<SimpleGraph<String>> directedSimpleGraphs() {
        return Stream.of(
                new DirectedGraph<>(),
                new DirectedEventGraph<>(testEventBus()));
    }

    public static Stream<SimpleGraph<String>> nonEventSimpleGraphs() {
        return Stream.of(
            new UndirectedGraph<>(),
            new DirectedGraph<>());
    }

    public static Stream<SimpleEventGraph<String>> eventSimpleGraphs() {
        return Stream.of(
                new UndirectedEventGraph<>(testEventBus()),
                new DirectedEventGraph<>(testEventBus()));
    }
}
