package publicapi.graphviz;

import com.github.moaxcp.graphs.graphviz.GifWriter;
import mockit.Injectable;
import org.junit.jupiter.api.Test;

import javax.imageio.stream.ImageOutputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GifWriterBuilderTest {

    @Injectable
    private ImageOutputStream out;

    @Test
    void defaultOut() {
        var builder = new GifWriter.Builder();
        var exception = assertThrows(NullPointerException.class, builder::build);
        assertThat(exception).hasMessage("out must not be null.");
    }

    @Test
    void nullOut() {
        var builder = new GifWriter.Builder();
        var exception = assertThrows(NullPointerException.class, () -> builder.out(null));
        assertThat(exception).hasMessage("out must not be null.");
    }

    @Test
    void lowerScreenWidth() {
        var exception = assertThrows(IllegalArgumentException.class, () -> new GifWriter.Builder().out(out).screenWidth(0));
        assertThat(exception).hasMessage("screenWidth must not be less than one.");
    }

    @Test
    void defaultScreenWidth() {
        var writer = new GifWriter.Builder().out(out).build();
        assertThat(writer).hasFieldOrPropertyWithValue("screenWidth", 1);
    }

    @Test
    void screenWidth() {
        var writer = new GifWriter.Builder().out(out).screenWidth(12).build();
        assertThat(writer).hasFieldOrPropertyWithValue("screenWidth", 12);
    }

    @Test
    void lowerScreenHeight() {
        var exception = assertThrows(IllegalArgumentException.class, () -> new GifWriter.Builder().out(out).screenWidth(1).screenHeight(0));
        assertThat(exception).hasMessage("screenHeight must not be less than one.");
    }

    @Test
    void defaultScreenHeight() {
        var writer = new GifWriter.Builder().out(out).build();
        assertThat(writer).hasFieldOrPropertyWithValue("screenHeight", 1);
    }

    @Test
    void screenHeight() {
        var writer = new GifWriter.Builder().out(out).screenHeight(12).build();
        assertThat(writer).hasFieldOrPropertyWithValue("screenHeight", 12);
    }

    @Test
    void lowerDefaultDelay() {
        var exception = assertThrows(IllegalArgumentException.class, () -> new GifWriter.Builder().out(out).defaultDelay(-1));
        assertThat(exception).hasMessage("defaultDelay must not be less than one.");
    }

    @Test
    void defaultDefaultDelay() {
        var writer = new GifWriter.Builder()
                .out(out)
                .build();
        assertThat(writer).hasFieldOrPropertyWithValue("defaultDelay", 100);
    }

    @Test
    void defaultDelay() {
        var writer = new GifWriter.Builder()
                .out(out)
                .defaultDelay(200)
                .build();
        assertThat(writer).hasFieldOrPropertyWithValue("defaultDelay", 200);
    }

    @Test
    void invalidDefaultDisposalMethod() {
        var exception = assertThrows(IllegalArgumentException.class, () -> new GifWriter.Builder().defaultDisposalMethod("asdf"));
        assertThat(exception).hasMessage("defaultDisposalMethod must be one of [none, doNotDispose, restoreToBackgroundColor, restoreToPrevious].");
    }

    @Test
    void defaultDefaultDisposalMethod() {
        var writer = new GifWriter.Builder()
                .out(out)
                .build();
        assertThat(writer).hasFieldOrPropertyWithValue("defaultDisposalMethod", "none");
    }

    @Test
    void defaultDisposalMethod() {
        var writer = new GifWriter.Builder()
                .out(out)
                .defaultDisposalMethod("doNotDispose")
                .build();
        assertThat(writer).hasFieldOrPropertyWithValue("defaultDisposalMethod", "doNotDispose");
    }

    @Test
    void defaultLoop() {
        var writer = new GifWriter.Builder()
                .out(out)
                .build();
        assertThat(writer).hasFieldOrPropertyWithValue("loop", 0);
    }

    @Test
    void invalidLoop() {
        var exception = assertThrows(IllegalArgumentException.class, () -> new GifWriter.Builder().defaultDelay(-1));
        assertThat(exception).hasMessage("defaultDelay must not be less than one.");
    }

    @Test
    void loop() {
        var writer = new GifWriter.Builder()
                .out(out)
                .loop(2)
                .build();
        assertThat(writer).hasFieldOrPropertyWithValue("loop", 2);
    }

    @Test
    void setDefaultDelayInvalid() {
        var writer = new GifWriter.Builder()
                .out(out)
                .build();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> writer.setDefaultDelay(-1));
        assertThat(exception).hasMessage("defaultDelay must not be less than 0.");
    }

    @Test
    void setDefaultDelay() {
        var writer = new GifWriter.Builder()
                .out(out)
                .build();
        writer.setDefaultDelay(2000);
        assertThat(writer).hasFieldOrPropertyWithValue("defaultDelay", 2000);
    }

    @Test
    void defaultDefaultInterlace() {
        var writer = new GifWriter.Builder()
                .out(out)
                .build();
        assertThat(writer).hasFieldOrPropertyWithValue("defaultInterlace", false);
    }

    @Test
    void defaultInterlace() {
        var writer = new GifWriter.Builder()
                .out(out)
                .defaultInterlace(true)
                .build();
        assertThat(writer).hasFieldOrPropertyWithValue("defaultInterlace", true);
    }
}
