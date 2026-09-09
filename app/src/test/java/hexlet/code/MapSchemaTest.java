package hexlet.code;

import java.util.HashMap;
import java.util.Map;

import hexlet.code.schemas.BaseSchema;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MapSchemaTest {
    @Test
    void testMapSchemaWithoutRequired() {
        Validator v = new Validator();
        var schema = v.map();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
    }

    @Test
    void testMapSchemaWithRequired() {
        Validator v = new Validator();
        var schema = v.map().required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
    }

    @Test
    void testMapSchemaSizeOf() {
        Validator v = new Validator();
        var schema = v.map().sizeof(2);

        Map<String, Object> map = new HashMap<>();
        map.put("ключ1", "значение1");
        map.put("ключ2", "значение2");

        assertFalse(schema.isValid(new HashMap<>()));
        assertTrue(schema.isValid(map));
        assertFalse(schema.isValid(null));
    }

    @Test
    void testMapSchemaSizeOfWithDifferentSize() {
        Validator v = new Validator();
        var schema = v.map().sizeof(2);

        Map<String, Object> map1 = new HashMap<>();
        map1.put("ключ1", "значение1");

        Map<String, Object> map2 = new HashMap<>();
        map2.put("ключ1", "значение1");
        map2.put("ключ2", "значение2");
        map2.put("ключ3", "значение3");

        assertFalse(schema.isValid(map1));
        assertFalse(schema.isValid(map2));
    }
    @Test
    void testMapSchemaShape() {
        Validator v = new Validator();
        var schema = v.map();

        Map<String, BaseSchema<?>> schemas = new HashMap<>();

        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");

        assertTrue(schema.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);

        assertFalse(schema.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");

        assertFalse(schema.isValid(human3));
    }

    @Test
    void testMapSchemaShapeWithNumber() {
        Validator v = new Validator();

        var schema = v.map();

        Map<String, BaseSchema<?>> schemas = new HashMap<>();

        schemas.put("name", v.string().required());
        schemas.put("age", v.number().required().positive());

        schema.shape(schemas);

        Map<String, Object> valid = new HashMap<>();
        valid.put("name", "John");
        valid.put("age", 25);

        assertTrue(schema.isValid(valid));

        Map<String, Object> invalid = new HashMap<>();
        invalid.put("name", "John");
        invalid.put("age", -5);

        assertFalse(schema.isValid(invalid));
    }
}
