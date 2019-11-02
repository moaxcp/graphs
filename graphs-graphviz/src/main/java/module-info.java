module com.github.moaxcp.graphs.graphviz {
    requires static lombok;
    exports com.github.moaxcp.graphs.graphviz;

    requires com.github.moaxcp.graphs.core;
    requires java.desktop;
}