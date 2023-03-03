package publicapi.events;

import com.github.moaxcp.graphs.ObservableDirectedPropertyGraph;
import com.github.moaxcp.graphs.ObservablePropertyGraph;
import com.github.moaxcp.graphs.PropertyGraphObserver;
import com.github.moaxcp.graphs.ObservableUndirectedPropertyGraph;
import com.github.moaxcp.graphs.truth.Observer;
import com.google.common.truth.Truth;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Constructors {

    static Stream<Supplier<ObservablePropertyGraph<String>>> empty() {
        return Stream.of(
            ObservableUndirectedPropertyGraph::new,
            ObservableDirectedPropertyGraph::new
        );
    }

    static Stream<Function<String, ObservablePropertyGraph<String>>> id() {
        return Stream.of(
            ObservableUndirectedPropertyGraph::new,
            ObservableDirectedPropertyGraph::new
        );
    }

    static Stream<Function<PropertyGraphObserver<String>[], ObservablePropertyGraph<String>>> observer() {
        return Stream.of(
            ObservableUndirectedPropertyGraph<String>::new,
            ObservableDirectedPropertyGraph<String>::new
        );
    }

    static Stream<BiFunction<String, PropertyGraphObserver[], ObservablePropertyGraph<String>>> idObserver() {
        return Stream.of(
            ObservableUndirectedPropertyGraph::new,
            ObservableDirectedPropertyGraph::new
        );
    }

    @ParameterizedTest
    @MethodSource("empty")
    void emptyConstructor(Supplier<ObservablePropertyGraph<String>> constructor) {
        assertThat(constructor.get().getObservers()).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("id")
    void idConstructor(Function<String, ObservablePropertyGraph<String>> constructor) {
        var graph = constructor.apply("id");
        assertThat(graph).hasIdThat().hasValue("id");
    }

    @ParameterizedTest
    @MethodSource("id")
    void idConstructorWithNull(Function<String, ObservablePropertyGraph<String>> constructor) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> constructor.apply(null));
        Truth.assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
    }

    @ParameterizedTest
    @MethodSource("observer")
    void observerConstructor(Function<PropertyGraphObserver[], ObservablePropertyGraph<String>> constructor) {
        var graph = constructor.apply(new PropertyGraphObserver[]{new Observer<String>(), new Observer<String>()});
        assertThat(graph.getObservers()).hasSize(2);
    }

    @ParameterizedTest
    @MethodSource("observer")
    void observerConstructorWithNullArray(Function<PropertyGraphObserver[], ObservablePropertyGraph<String>> constructor) {
        var thrown = assertThrows(NullPointerException.class, () -> constructor.apply(null));
        Truth.assertThat(thrown).hasMessageThat().isEqualTo("observers is marked non-null but is null");
    }

    @ParameterizedTest
    @MethodSource("observer")
    void observerConstructorWithNullItem(Function<PropertyGraphObserver[], ObservablePropertyGraph<String>> constructor) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> constructor.apply(new PropertyGraphObserver[]{new Observer<String>(), null}));
        Truth.assertThat(thrown).hasMessageThat().isEqualTo("observer must not be null.");
    }

    @ParameterizedTest
    @MethodSource("idObserver")
    void idObserverConstructor(BiFunction<String, PropertyGraphObserver[], ObservablePropertyGraph<String>> constructor) {
        var graph = constructor.apply("id", new PropertyGraphObserver[]{new Observer<String>()});
        assertThat(graph).hasIdThat().hasValue("id");
        assertThat(graph.getObservers()).hasSize(1);
    }

    @ParameterizedTest
    @MethodSource("idObserver")
    void idObserverWithNullId(BiFunction<String, PropertyGraphObserver[], ObservablePropertyGraph<String>> constructor) {
        var thrown = assertThrows(NullPointerException.class, () -> constructor.apply(null, new PropertyGraphObserver[]{new Observer<String>()}));
        assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
    }

    @ParameterizedTest
    @MethodSource("idObserver")
    void idObserverWithNullObservers(BiFunction<String, PropertyGraphObserver[], ObservablePropertyGraph<String>> constructor) {
        var thrown = assertThrows(NullPointerException.class, () -> constructor.apply("id", null));
        assertThat(thrown).hasMessageThat().isEqualTo("observers is marked non-null but is null");
    }

    @ParameterizedTest
    @MethodSource("idObserver")
    void idObserverWithNullObserversItem(BiFunction<String, PropertyGraphObserver[], ObservablePropertyGraph<String>> constructor) {
        var thrown = assertThrows(NullPointerException.class, () -> constructor.apply("id", new PropertyGraphObserver[]{new Observer<String>(), null}));
        assertThat(thrown).hasMessageThat().isEqualTo("observer must not be null.");
    }
}
