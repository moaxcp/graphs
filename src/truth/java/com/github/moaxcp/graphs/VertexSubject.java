package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.greenrobot.UndirectedGraph;
import com.google.common.truth.*;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Optional;

import static com.google.common.truth.OptionalSubject.optionals;
import static com.google.common.truth.Truth.assertAbout;

public class VertexSubject extends Subject<VertexSubject, UndirectedGraph.Vertex> {
    /**
     * Constructor for use by subclasses. If you want to create an instance of this class itself, call
     * {@link Subject#check}{@code .that(actual)}.
     *
     * @param metadata
     * @param actual
     */
    private VertexSubject(FailureMetadata metadata, @NullableDecl UndirectedGraph.Vertex actual) {
        super(metadata, actual);
    }

    public static Subject.Factory<VertexSubject, UndirectedGraph.Vertex> vertices() {
        return VertexSubject::new;
    }

    public static VertexSubject assertThat(@Nullable UndirectedGraph.Vertex actual) {
        return assertAbout(vertices()).that(actual);
    }

    public void hasId(Object id) {
        check("getId()").that(actual().getId()).isEqualTo(id);
    }

    public OptionalSubject thatProperty(String name) {
        Optional<Object> optional = actual().getProperty(name);
        return check("getProperty(%s)", name).about(optionals()).that(optional);
    }

    public Subject<DefaultSubject, Object> thatLocal(String name) {
        return check("getLocal(%s)", name).that(actual().getLocal().get(name));
    }
}
