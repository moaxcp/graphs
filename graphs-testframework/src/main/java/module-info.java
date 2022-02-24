module com.github.moaxcp.graphs.testframework {
    requires static lombok;
    exports com.github.moaxcp.graphs.testframework;
    requires com.github.moaxcp.graphs.core;
    requires com.github.moaxcp.graphs.greenrobot;
    requires eventbus.java;
    requires org.junit.jupiter.params;
}