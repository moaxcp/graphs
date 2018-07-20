package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.event.*;
import de.muspellheim.eventbus.EventBus;

import java.util.Map;

public class TestIdentifiedInheritingElement extends IdentifiedInheritingElement {
    public TestIdentifiedInheritingElement(String id, Map<String, Object> inherited, EventBus bus) {
        super(id, inherited, bus);
    }
    @Override
    public TestIdentifiedInheritingElement withProperty(String name, Object value) {
        setProperty(name, value);
        return this;
    }

    @Override
    protected PropertyAddedGraphEvent propertyAddedEvent(String name, Object value) {
        return new PropertyAddedGraphEvent() {
            @Override
            public Graph getGraph() {
                return null;
            }

            @Override
            public void setGraph(Graph graph) {

            }

            @Override
            public GraphEvent withGraph(Graph graph) {
                return null;
            }

            @Override
            public void check() {

            }

            @Override
            public String getName() {
                return null;
            }

            @Override
            public void setName(String name) {

            }

            @Override
            public Object getValue() {
                return null;
            }

            @Override
            public void setValue(Object value) {

            }

            @Override
            public PropertyGraphEvent withName(String name) {
                return null;
            }

            @Override
            public PropertyGraphEvent withValue(Object value) {
                return null;
            }
        };
    }

    @Override
    protected PropertyRemovedGraphEvent propertyRemovedEvent(String name, Object value) {
        return new PropertyRemovedGraphEvent() {
            @Override
            public Graph getGraph() {
                return null;
            }

            @Override
            public void setGraph(Graph graph) {

            }

            @Override
            public GraphEvent withGraph(Graph graph) {
                return null;
            }

            @Override
            public void check() {

            }

            @Override
            public String getName() {
                return null;
            }

            @Override
            public void setName(String name) {

            }

            @Override
            public Object getValue() {
                return null;
            }

            @Override
            public void setValue(Object value) {

            }

            @Override
            public PropertyGraphEvent withName(String name) {
                return null;
            }

            @Override
            public PropertyGraphEvent withValue(Object value) {
                return null;
            }
        };
    }

    @Override
    protected PropertyUpdatedGraphEvent propertyUpdatedEvent(String name, Object value, Object oldValue) {
        return new PropertyUpdatedGraphEvent() {
            @Override
            public Graph getGraph() {
                return null;
            }

            @Override
            public void setGraph(Graph graph) {

            }

            @Override
            public GraphEvent withGraph(Graph graph) {
                return null;
            }

            @Override
            public void check() {

            }

            @Override
            public String getName() {
                return null;
            }

            @Override
            public void setName(String name) {

            }

            @Override
            public Object getValue() {
                return null;
            }

            @Override
            public void setValue(Object value) {

            }

            @Override
            public PropertyGraphEvent withName(String name) {
                return null;
            }

            @Override
            public PropertyGraphEvent withValue(Object value) {
                return null;
            }

            @Override
            public Object getOldValue() {
                return null;
            }

            @Override
            public void setOldValue(Object oldValue) {

            }

            @Override
            public PropertyUpdatedGraphEvent withOldValue(Object oldValue) {
                return null;
            }
        };
    }
}
