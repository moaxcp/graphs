package com.github.moaxcp.graphs;

import java.util.ConcurrentModificationException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;

public abstract class FromToElement extends Element {
    public FromToElement(String from, String to) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);
        attributes.put("from", from);
        attributes.put("to", to);
    }

    public String getFrom() {
        return (String) get("from");
    }

    public void setFrom(String from) {
        Objects.requireNonNull(from);
        attributes.put("from", from);
    }

    public String getTo() {
        return (String) get("to");
    }

    public void setTo(String to) {
        Objects.requireNonNull(to);
        attributes.put("to", to);
    }

    @Override
    public Object put(String key, Object value) {
        if("from".equals(key)) {
            String old = getFrom();
            setFrom((String) value);
            return old;
        }
        if("to".equals(key)) {
            String old = getTo();
            setTo((String) value);
            return old;
        }
        return super.put(key, value);
    }

    @Override
    public void putAll(Map<? extends String, ? extends Object> m) {
        if(m.containsKey("from") || m.containsKey("to")) {
            var removed = new LinkedHashMap<String, Object>();
            String from = (String) removed.remove("from");
            if(from != null) {
                setFrom(from);
            }
            String to = (String) removed.remove("to");
            if(to != null) {
                setTo(to);
            }
            attributes.putAll(removed);
            return;
        }
        attributes.putAll(m);
    }

    @Override
    public Object remove(Object key) {
        if("from".equals(key)) {
            throw new IllegalArgumentException("cannot remove 'from'");
        }
        if("to".equals(key)) {
            throw new IllegalArgumentException("cannot remove 'to'");
        }
        return super.remove(key);
    }

    @Override
    public void replaceAll(BiFunction<? super String, ? super Object, ? extends Object> function) {
        Objects.requireNonNull(function);
        for (Map.Entry<String, Object> entry : entrySet()) {
            String k;
            Object v;
            try {
                k = entry.getKey();
                v = entry.getValue();
            } catch (IllegalStateException ise) {
                // this usually means the entry is no longer in the map.
                throw new ConcurrentModificationException(ise);
            }

            // ise thrown from function is not a cme.
            v = function.apply(k, v);

            try {
                if ("from".equals(k)) {
                    setFrom((String) v);
                } else if ("to".equals(k)) {
                    setTo((String) v);
                } else {
                    entry.setValue(v);
                }
            } catch (IllegalStateException ise) {
                // this usually means the entry is no longer in the map.
                throw new ConcurrentModificationException(ise);
            }
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " '" + getFrom() + "' to '" + getTo() + "' " + super.getAttributes();
    }
}
