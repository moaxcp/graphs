package com.github.moaxcp.graphs;

import static com.google.common.truth.OptionalSubject.*;
import static com.google.common.truth.Truth.*;
import com.github.moaxcp.graphs.SimpleGraph.*;
import com.google.common.truth.*;
import java.util.*;
import org.checkerframework.checker.nullness.compatqual.*;
import org.checkerframework.checker.nullness.qual.*;

public class VertexSubject extends Subject<VertexSubject, Vertex> {
    /**
     * Constructor for use by subclasses. If you want to create an instance of this class itself, call
     * {@link Subject#check}{@code .that(actual)}.
     *
     * @param metadata
     * @param actual
     */
    private VertexSubject(FailureMetadata metadata, @NullableDecl Vertex actual) {
        super(metadata, actual);
    }

    public static Subject.Factory<VertexSubject, Vertex> vertices() {
        return VertexSubject::new;
    }

    public static VertexSubject assertThat(@Nullable Vertex actual) {
        return assertAbout(vertices()).that(actual);
    }

    public void hasId(Object id) {
        check("getId()").that(actual().getId()).isEqualTo(id);
    }

    public OptionalSubject withProperty(String name) {
        Optional<Object> optional = actual().getProperty(name);
        return check("getProperty(%s)", name).about(optionals()).that(optional);
    }

    public MapSubject withLocal() {
        return check("local()").that(actual().local());
    }

    public MapSubject withInherited() {
        return check("inherited()").that(actual().inherited());
    }
}
