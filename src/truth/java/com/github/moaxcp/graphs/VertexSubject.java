package com.github.moaxcp.graphs;

import com.google.common.truth.FailureMetadata;
import com.google.common.truth.Subject;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.checkerframework.checker.nullness.qual.Nullable;

import static com.google.common.truth.Truth.assertAbout;

public class VertexSubject extends Subject<VertexSubject, Graph.Vertex> {
    /**
     * Constructor for use by subclasses. If you want to create an instance of this class itself, call
     * {@link Subject#check}{@code .that(actual)}.
     *
     * @param metadata
     * @param actual
     */
    private VertexSubject(FailureMetadata metadata, @NullableDecl Graph.Vertex actual) {
        super(metadata, actual);
    }

    public static Subject.Factory<VertexSubject, Graph.Vertex> vertices() {
        return VertexSubject::new;
    }

    public static VertexSubject assertThat(@Nullable Graph.Vertex actual) {
        return assertAbout(vertices()).that(actual);
    }
}
