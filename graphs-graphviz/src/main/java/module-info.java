module com.github.moaxcp.graphs.graphviz {
    requires static lombok;
    exports com.github.moaxcp.graphs.graphviz;

    requires transitive com.github.moaxcp.graphs.core;
    requires transitive java.desktop;
}