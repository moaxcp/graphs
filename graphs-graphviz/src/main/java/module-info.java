module com.github.moaxcp.graphs.graphviz {
    requires static lombok;
    requires transitive com.github.moaxcp.graphs.core;
    requires transitive com.github.moaxcp.gifbuilder;
    requires transitive java.desktop;
    exports com.github.moaxcp.graphs.graphviz;
}