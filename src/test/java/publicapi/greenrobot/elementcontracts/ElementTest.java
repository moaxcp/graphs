package publicapi.greenrobot.elementcontracts;

import com.github.moaxcp.graphs.greenrobot.element.Element;
import org.greenrobot.eventbus.EventBus;
import org.junit.jupiter.api.Test;
import stubs.TestElement;

import static com.google.common.truth.Truth.assertThat;

class ElementTest {

    Element element = new TestElement(EventBus.getDefault());
    @Test
    void testCosntructor() {
        assertThat(element.getLocal()).isEmpty();
    }
}
