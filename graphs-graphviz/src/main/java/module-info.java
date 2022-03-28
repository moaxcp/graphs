module com.github.moaxcp.graphs.graphviz {
    requires static lombok;
    requires com.github.moaxcp.gifbuilder;
    exports com.github.moaxcp.graphs.graphviz;

    requires transitive com.github.moaxcp.graphs.core;
    requires transitive java.desktop;
}