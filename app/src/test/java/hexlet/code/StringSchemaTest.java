package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StringSchemaTest {
    @Test
    void testStringSchemaWithRequired() {
        Validator v = new Validator();
        var schema = v.string().required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("hello"));
    }

    @Test
    void testMinLength() {
        Validator v = new Validator();
        var schema = v.string().minLength(4);

        assertFalse(schema.isValid("hi"));
        assertTrue(schema.isValid("word"));
        assertTrue(schema.isValid("hello world"));
    }

    @Test
    void testContains() {
        Validator v = new Validator();
        var schema = v.string().contains("hex");

        assertFalse(schema.isValid("hi"));
        assertTrue(schema.isValid("hello hexlet"));
        assertTrue(schema.isValid("hex"));
    }
    @Test
    void testConstraintsWithoutRequired() {
        Validator v = new Validator();
        var schema = v.string()
                .minLength(4)
                .contains("ello");

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
        assertFalse(schema.isValid("hexlet"));
        assertTrue(schema.isValid("hello"));
    }

    @Test
    void testAllConstraints() {
        Validator v = new Validator();
        var schema = v.string()
                .required()
                .minLength(5)
                .contains("hex");

        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid("hi"));
        assertFalse(schema.isValid("hex"));
        assertFalse(schema.isValid("hello"));
        assertTrue(schema.isValid("hexlet"));
    }
    @Test
    void testRepeatedCallsReplaceConstraint() {
        Validator v = new Validator();
        var schema = v.string()
                .contains("hex")
                .contains("hel")
                .minLength(3)
                .minLength(7);

        assertFalse(schema.isValid("hex"));
        assertFalse(schema.isValid("hello"));
        assertTrue(schema.isValid("hello world"));
    }
}
