module com.github.moaxcp.graphs.graphviz {
    requires static lombok;
    requires static transitive com.github.moaxcp.graphs.greenrobot; //greenrobot gif
    requires transitive com.github.moaxcp.gifbuilder;
    requires transitive com.github.moaxcp.graphs.core;
    requires transitive java.desktop;
    exports com.github.moaxcp.graphs.graphviz;
}