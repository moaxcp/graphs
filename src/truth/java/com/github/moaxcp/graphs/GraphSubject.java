package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.Graph.Vertex;
import com.google.common.truth.FailureMetadata;
import com.google.common.truth.Subject;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.HashSet;
import java.util.Optional;

import static com.google.common.truth.Fact.simpleFact;
import static com.google.common.truth.Truth.assertAbout;

public final class GraphSubject extends Subject<GraphSubject, Graph> {

    /**
     * Constructor for use by subclasses. If you want to create an instance of this class itself, call
     * {@link Subject#check}{@code .that(actual)}.
     *
     * @param metadata
     * @param actual
     */
    private GraphSubject(FailureMetadata metadata, Graph actual) {
        super(metadata, actual);
    }

    public static Subject.Factory<GraphSubject, Graph> graphs() {
        return GraphSubject::new;
    }

    public static GraphSubject assertThat(@Nullable Graph actual) {
        return assertAbout(graphs()).that(actual);
    }

    public void hasVertex(Object id) {
        Vertex vertex = actual().getVertices().get(id);
        if(vertex == null) {
            failWithActual(simpleFact("vertices did not contain " + id));
        }
        hasVertex(id, vertex);
    }

    public void hasVertex(Object id, Vertex vertex) {
        Vertex existing = actual().vertex(id);
        if(vertex != existing) {
            failWithActual(simpleFact("vertex in graph not same instance."));
        }
        if(!vertex.getId().equals(id)) {
            failWithActual(simpleFact("Vertex with key of " + id + " had id of " + vertex.getId()));
        }
        if(!vertex.getProperty("id").get().equals(id)) {
            failWithActual(simpleFact("Vertex '" + id + "' had id property of " + vertex.getProperty("id")));
        }
        String localId = (String) vertex.getLocal().get("id");
        if(!localId.equals(id)) {
            failWithActual(simpleFact("Vertex '" + id + "' + had local id of " + localId));
        }
    }

    public void hasEdge(Object from, Object to) {
        hasVertex(from);
        hasVertex(to);
        Optional<Graph.Edge> find = actual().findEdge(from, to);

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
                failWithActual(simpleFact("duplicate id in arguments."));
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
