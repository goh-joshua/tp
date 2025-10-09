package seedu.address;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

/**
 * Basic safety tests for {@link Main}.
 * <p>
 * This test intentionally does not invoke {@code Main.main(...)} because it would
 * attempt to launch JavaFX. Instead, it verifies class loading, method signature, and
 * presence of the logger field.
 */
public class MainTest {

    @Test
    public void classCanBeLoaded() {
        assertDoesNotThrow(() -> Class.forName("seedu.address.Main"));
    }

    @Test
    public void mainMethod_hasPublicStaticSignature() throws Exception {
        Method mainMethod = Main.class.getDeclaredMethod("main", String[].class);
        assertNotNull(mainMethod);

        int mods = mainMethod.getModifiers();
        assertTrue(Modifier.isPublic(mods), "main should be public");
        assertTrue(Modifier.isStatic(mods), "main should be static");
    }

    @Test
    public void loggerField_isStaticAndLoggerType() throws Exception {
        Field loggerField = Main.class.getDeclaredField("logger");
        assertNotNull(loggerField);

        int mods = loggerField.getModifiers();
        assertTrue(Modifier.isStatic(mods), "logger should be static");
        assertEquals(Logger.class, loggerField.getType(), "logger should be of type java.util.logging.Logger");
    }
}
