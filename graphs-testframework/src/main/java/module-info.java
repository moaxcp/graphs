module com.github.moaxcp.graphs.testframework {
    requires static lombok;
    exports com.github.moaxcp.graphs.testframework;
    requires com.github.moaxcp.graphs.core;
    requires transitive com.github.moaxcp.graphs.greenrobot;
    requires transitive com.github.moaxcp.graphs.guava;
    requires org.junit.jupiter.params;
}