package com.github.moaxcp.graphs.events;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import static java.util.Collections.unmodifiableMap;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toMap;

public final class VertexPropertiesEvent<K> extends VertexEvent<K> {

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
    private final Map<String, UpdatedProperty> updatedProperties;

    private VertexPropertiesEvent(Builder<K> builder) {
        super(builder);
        addedProperties = builder.addedProperties;
        updatedProperties = builder.oldUpdated;
    }

    public Map<String, Object> getAddedProperties() {
        return unmodifiableMap(addedProperties);
    }

    public Map<String, UpdatedProperty> getUpdatedProperties() {
        return unmodifiableMap(updatedProperties);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VertexPropertiesEvent<?> that = (VertexPropertiesEvent<?>) o;
        return Objects.equals(addedProperties, that.addedProperties) &&
                Objects.equals(updatedProperties, that.updatedProperties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), addedProperties, updatedProperties);
    }

    @SuppressWarnings("squid:S2176")
    public static final class Builder<K> extends VertexEvent.Builder<K, Builder<K>> {

        private Map<String, Object> originalProperties = Map.of();
        private Map<String, Object> newProperties;
        private Map<String, UpdatedProperty> oldUpdated;
        private Map<String, Object> addedProperties;

        public Builder<K> originalProperties(Map<String, Object> originalProperties) {
            requireNonNull(originalProperties, "originalProperties must not be null.");
            if(originalProperties.isEmpty()) {
                return this;
            }
            this.originalProperties = new LinkedHashMap<>(originalProperties);
            return this;
        }

        public Builder<K> newProperties(Map<String, Object> newProperties) {
            requireNonNull(newProperties, "newProperties must not be null.");
            if(newProperties.isEmpty()) {
                return this;
            }
            this.newProperties = new LinkedHashMap<>(newProperties);
            return this;
        }

        @Override
        public Builder<K> self() {
            return this;
        }

        @Override
        public VertexPropertiesEvent<K> build() {
            requireNonNull(newProperties, "newProperties must not be null.");
            oldUpdated = originalProperties.entrySet().stream()
                    .filter(e -> newProperties.containsKey(e.getKey()))
                    .collect(toMap(Map.Entry::getKey, e -> new UpdatedProperty(e.getKey(), e.getValue(), newProperties.get(e.getKey()))));
            addedProperties = newProperties.entrySet().stream()
                    .filter(e -> !originalProperties.containsKey(e.getKey()))
                    .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
            if(oldUpdated.isEmpty() && addedProperties.isEmpty()) {
                throw new IllegalStateException("Invalid event: No properties were changed.");
            }

            return new VertexPropertiesEvent<>(this);
        }
    }
}
