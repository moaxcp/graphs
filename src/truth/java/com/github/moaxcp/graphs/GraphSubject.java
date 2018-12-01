package com.github.moaxcp.graphs;

import static com.github.moaxcp.graphs.EdgeSubject.*;
import static com.github.moaxcp.graphs.VertexSubject.*;
import static com.google.common.truth.Fact.*;
import static com.google.common.truth.OptionalSubject.*;
import static com.google.common.truth.Truth.*;
import com.github.moaxcp.graphs.SimpleGraph.*;
import com.google.common.truth.*;
import java.util.*;
import org.checkerframework.checker.nullness.qual.*;

public final class GraphSubject extends Subject<GraphSubject, SimpleGraph> {

    /**
     * Constructor for use by subclasses. If you want to create an instance of this class itself, call
     * {@link Subject#check}{@code .that(actual)}.
     *
     * @param metadata
     * @param actual
     */
    private GraphSubject(FailureMetadata metadata, SimpleGraph actual) {
        super(metadata, actual);
    }

    public static Subject.Factory<GraphSubject, SimpleGraph> graphs() {
        return GraphSubject::new;
    }

    static GraphSubject assertThat(@Nullable SimpleGraph actual) {
        return assertAbout(graphs()).that(actual);
    }

    public OptionalSubject hasIdThat() {
        return check("getId()").about(optionals()).that(actual().getId());
    }

    public VertexSubject hasVertex(Object id) {
        Optional<Vertex> find = actual().findVertex(id);
        check("findVertex(%s)", id).about(optionals()).that(find).isPresent();
        return assertAbout(vertices()).that(find.orElse(null));
    }

    public EdgeSubject hasEdge(Object from, Object to) {
        hasVertex(from);
        hasVertex(to);
        Optional<Edge> find = actual().findEdge(from, to);
        check("findEdge(%s, %s)", from, to).about(optionals()).that(find).isPresent();
        return assertAbout(edges()).that(find.orElse(null));
    }

    public EdgeSubject hasEdge(Object id) {
        Optional<Edge> find = actual().findEdge(id);
        check("edge(%s)", id).about(optionals()).that(find).isPresent();
        return assertAbout(edges()).that(find.orElse(null));
    }

    public void hasNoEdge(Object from, Object to) {
        var find = actual().findEdge(from, to);
        check("findEdge(%s, %s)", from, to).about(optionals()).that(find).isEmpty();
    }

    public void hasNoEdge(Object id) {
        var find =actual().findEdge(id);
        check("findEdge(%s)", id).about(optionals()).that(find).isEmpty();
    }

    public void hasVertices(String id, String... ids) {
        hasVertex(id);
        for(String i : ids) {
            hasVertex(i);
        }
    }

    public void hasExactVertices(String id, String... ids) {
        var set = new HashSet<String>();
        set.add(id);
        for(String i : ids) {
            if(!set.add(id)) {
                failWithActual(simpleFact("duplicate hasIdThat in arguments."));
            }
        }
        var keys = actual().getVertices().keySet();
        if(set.size() != keys.size()) {
            failWithActual(simpleFact("Expected graph with size " + set.size() + " but found was size " + keys.size() ));
        }
        var removed = new HashSet<String>();
        for(String s : set) {
            if(keys.remove(s)) {
                removed.add(s);
            }
        }
        set.removeAll(removed);
        if(set.size() != 0) {
            //missing vertices
        }
        if(keys.size() != 0) {
            //graph has other vertices
        }
    }

    public void isDirected() {
        if(!actual().isDirected()) {
            failWithActual(simpleFact("Expected directed graph."));
        }
    }

    public void isNotDirected() {
        if(actual().isDirected()) {
            failWithActual(simpleFact("Expected undirected graph."));
        }
    }

    public OptionalSubject withProperty(String name) {
        return check("getProperty(%s)", name).about(optionals()).that(actual().getProperty(name));
    }

    public OptionalSubject withEdgeProperty(String name) {
        return check("getEdgeProperty(%s)", name).about(optionals()).that(actual().getEdgeProperty(name));
    }

    public OptionalSubject withVertexProperty(String name) {
        return check("getVertexProperty(%s)", name).about(optionals()).that(actual().getVertexProperty(name));
    }
}
