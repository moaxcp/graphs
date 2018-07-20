package com.github.moaxcp.graphs;

import de.muspellheim.eventbus.EventBus;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

class ElementTest {

    Element element = new TestElement(EventBus.getDefault());
    @Test
    void testCosntructor() {
        assertThat(element.getLocal()).isEmpty();
    }
}
