package com.github.moaxcp.graphs.events;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toMap;

public abstract class PropertiesEvent<K> extends GraphEvent<K> {

    public static class UpdatedProperty {
        private String name;
        private Object oldValue;
        private Object newValue;

        public UpdatedProperty(String name, Object oldValue, Object newValue) {
            this.name = name;
            this.oldValue = oldValue;
            this.newValue = newValue;
        }

        public String getName() {
            return name;
        }

        public Object getOldValue() {
            return oldValue;
        }

        public Object getNewValue() {
            return newValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UpdatedProperty that = (UpdatedProperty) o;
            return name.equals(that.name) &&
                oldValue.equals(that.oldValue) &&
                newValue.equals(that.newValue);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, oldValue, newValue);
        }
    }

    private final Map<String, Object> addedProperties;
    private final Map<String, PropertiesEvent.UpdatedProperty> updatedProperties;

    PropertiesEvent(Builder<K, ? extends Builder> builder) {
        super(builder);
        addedProperties = builder.addedProperties;
        updatedProperties = builder.oldUpdated;
    }

    public Map<String, Object> getAddedProperties() {
        return addedProperties;
    }

    public Map<String, PropertiesEvent.UpdatedProperty> getUpdatedProperties() {
        return updatedProperties;
    }

    @SuppressWarnings("squid:S2176")
    public static abstract class Builder<K, S extends Builder<K, S>> extends GraphEvent.Builder<K, S> {

        private Map<String, Object> originalProperties = Map.of();
        private Map<String, Object> newProperties;
        private Map<String, UpdatedProperty> oldUpdated;
        private Map<String, Object> addedProperties;

        public S originalProperties(Map<String, Object> originalProperties) {
            requireNonNull(originalProperties, "originalProperties must not be null.");
            if (originalProperties.isEmpty()) {
                return self();
            }
            this.originalProperties = new LinkedHashMap<>(originalProperties);
            return self();
        }

        public S newProperties(Map<String, Object> newProperties) {
            requireNonNull(newProperties, "newProperties must not be null.");
            if (newProperties.isEmpty()) {
                return self();
            }
            this.newProperties = new LinkedHashMap<>(newProperties);
            return self();
        }

        void buildProperties() {
            requireNonNull(newProperties, "newProperties must not be null.");
            oldUpdated = originalProperties.entrySet().stream()
                .filter(e -> newProperties.containsKey(e.getKey()))
                .collect(toMap(Map.Entry::getKey, e -> new UpdatedProperty(e.getKey(), e.getValue(), newProperties.get(e.getKey()))));
            addedProperties = newProperties.entrySet().stream()
                .filter(e -> !originalProperties.containsKey(e.getKey()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
            if (oldUpdated.isEmpty() && addedProperties.isEmpty()) {
                throw new IllegalStateException("Invalid event: No properties were changed.");
            }
        }
    }
}
