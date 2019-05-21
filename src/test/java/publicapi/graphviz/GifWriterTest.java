package publicapi.graphviz;

import com.github.moaxcp.graphs.graphviz.GifWriter;
import mockit.Injectable;
import org.junit.jupiter.api.Test;

import javax.imageio.stream.ImageOutputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GifWriterTest {

    @Injectable
    private ImageOutputStream out;

    @Test
    void nullOut() {
        var builder = new GifWriter.Builder();
        var exception = assertThrows(NullPointerException.class, builder::build);
        assertThat(exception).hasMessage("out must not be null.");
    }

    @Test
    void lowerWidth() {
        var builder = new GifWriter.Builder().out(out).screenWidth(0);
        var exception = assertThrows(IllegalArgumentException.class, builder::build);
        assertThat(exception).hasMessage("screenWidth must not be less than one.");
    }

    @Test
    void lowerHeight() {
        var builder = new GifWriter.Builder().out(out).screenWidth(1).screenHeight(0);
        var exception = assertThrows(IllegalArgumentException.class, builder::build);
        assertThat(exception).hasMessage("screenHeight must not be less than one.");
    }

    @Test
    void defaultDefaultDelay() {
        var writer = new GifWriter.Builder()
                .out(out)
                .screenWidth(1)
                .screenHeight(1)
                .build();
        assertThat(writer).hasFieldOrPropertyWithValue("defaultDelay", 100);
    }

    @Test
    void defaultDefaultDisposalMethod() {
        var writer = new GifWriter.Builder()
                .out(out)
                .screenWidth(1)
                .screenHeight(1)
                .build();
        assertThat(writer).hasFieldOrPropertyWithValue("defaultDisposalMethod", "none");
    }

    @Test
    void defaultLoop() {
        var writer = new GifWriter.Builder()
                .out(out)
                .screenWidth(1)
                .screenHeight(1)
                .build();
        assertThat(writer).hasFieldOrPropertyWithValue("loop", true);
    }

    @Test
    void wrongDefaultDisposalMethod() {
        var writer = new GifWriter.Builder()
                .out(out)
                .screenWidth(1)
                .screenHeight(1)
                .defaultDisposalMethod("asdf");
        var exception = assertThrows(IllegalArgumentException.class, writer::build);
        assertThat(exception).hasMessage("defaultDisposalMethod most be either non, background.");
    }

    @Test
    void setDefaultDelayInvalid() {
        var writer = new GifWriter.Builder()
                .out(out)
                .screenWidth(1)
                .screenHeight(1)
                .build();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> writer.setDefaultDelay(-1));
        assertThat(exception).hasMessage("defaultDelay must not be less than 0.");
    }

    @Test
    void setDefaultDelay() {
        var writer = new GifWriter.Builder()
                .out(out)
                .screenWidth(1)
                .screenHeight(1)
                .build();
        writer.setDefaultDelay(2000);
        assertThat(writer).hasFieldOrPropertyWithValue("defaultDelay", 2000);
    }
}
