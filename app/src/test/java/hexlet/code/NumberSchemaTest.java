package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class NumberSchemaTest {
    @Test
    void testNumberSchemaWithoutRequired() {
        Validator v = new Validator();
        var schema = v.number();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(-5));
        assertTrue(schema.isValid(0));
    }

    @Test
    void testNumberSchemaWithRequired() {
        Validator v = new Validator();
        var schema = v.number().required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(1));
        assertTrue(schema.isValid(-5));
        assertTrue(schema.isValid(0));
    }

    @Test
    void testNumberSchemaPositive() {
        Validator v = new Validator();
        var schema = v.number().positive();

        assertFalse(schema.isValid(-4));
        assertFalse(schema.isValid(0));
        assertTrue(schema.isValid(1));
    }

    @Test
    void testNumberSchemaRange() {
        Validator v = new Validator();
        var schema = v.number().range(2, 7);

        assertFalse(schema.isValid(1));
        assertFalse(schema.isValid(8));
        assertTrue(schema.isValid(2));
        assertTrue(schema.isValid(6));
        assertTrue(schema.isValid(7));
    }

    @Test
    void testAllConstraints() {
        Validator v = new Validator();
        var schema = v.number()
                .required()
                .positive()
                .range(5, 8);

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(-1));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(9));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(7));
        assertTrue(schema.isValid(8));
    }

    @Test
    void testRepeatedCallsReplaceConstraint() {
        Validator v = new Validator();
        var schema = v.number()
                .range(1, 4)
                .range(6, 9);

        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(5));
        assertTrue(schema.isValid(7));
        assertTrue(schema.isValid(6));
        assertTrue(schema.isValid(9));
    }

    @Test
    void testPositiveAndRequiredTogether() {
        Validator v = new Validator();
        var schema = v.number().required().positive();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(-5));
        assertFalse(schema.isValid(0));
        assertTrue(schema.isValid(5));
    }

    @Test
    void testPositiveAfterRange() {
        Validator v = new Validator();
        var schema = v.number().positive().range(3, 7);

        assertFalse(schema.isValid(-5));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(2));
        assertFalse(schema.isValid(8));
        assertTrue(schema.isValid(3));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(7));
    }

    @Test
    void testNullWithAndWithoutRequired() {
        Validator v = new Validator();
        var withoutRequired = v.number().positive().range(1, 5);
        assertTrue(withoutRequired.isValid(null));

        var withRequired = v.number().required().positive().range(1, 5);
        assertFalse(withRequired.isValid(null));
    }
}
