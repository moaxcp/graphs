package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.greenrobot.UndirectedGraph;
import com.github.moaxcp.graphs.greenrobot.UndirectedGraph.Vertex;
import com.google.common.truth.FailureMetadata;
import com.google.common.truth.Subject;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.HashSet;
import java.util.Optional;

import static com.github.moaxcp.graphs.EdgeSubject.edges;
import static com.github.moaxcp.graphs.VertexSubject.vertices;
import static com.google.common.truth.Fact.simpleFact;
import static com.google.common.truth.OptionalSubject.optionals;
import static com.google.common.truth.Truth.assertAbout;

public final class GraphSubject extends Subject<GraphSubject, UndirectedGraph> {

    /**
     * Constructor for use by subclasses. If you want to create an instance of this class itself, call
     * {@link Subject#check}{@code .that(actual)}.
     *
     * @param metadata
     * @param actual
     */
    private GraphSubject(FailureMetadata metadata, UndirectedGraph actual) {
        super(metadata, actual);
    }

    public static Subject.Factory<GraphSubject, UndirectedGraph> graphs() {
        return GraphSubject::new;
    }

    public static GraphSubject assertThat(@Nullable UndirectedGraph actual) {
        return assertAbout(graphs()).that(actual);
    }

    public VertexSubject hasVertexThat(Object id) {
        Optional<Vertex> optional = actual().findVertex(id);
        check("findVertex(%s)", id).about(optionals()).that(optional).isPresent();
        return assertAbout(vertices()).that(optional.orElse(null));
    }

    public void hasVertex(Object id) {
        Optional<Vertex> optional = actual().findVertex(id);
        check("findVertex(%s)", id).about(optionals()).that(optional).isPresent();
    }

    public EdgeSubject hasEdge(Object from, Object to) {
        hasVertexThat(from);
        hasVertexThat(to);
        Optional<UndirectedGraph.Edge> find = actual().findEdge(from, to);
        check("findEdge(%s, %s)", from, to).about(optionals()).that(find).isPresent();
        return assertAbout(edges()).that(find.orElse(null));
    }

    public EdgeSubject hasEdge(Object id) {
        Optional<UndirectedGraph.Edge> find = actual().findEdge(id);
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
}
