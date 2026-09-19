package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<String, ?>> {

    public MapSchema() {
        addCheck("required", value -> value != null);
    }

    public MapSchema required() {
        required = true;
        return this;
    }

    public MapSchema sizeof(Integer number) {
        addCheck("sizeof", map -> map.size() == number);
        return this;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        addCheck("shape", map -> {
            for (Map.Entry<String, BaseSchema<T>> entry : schemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<T> schema = entry.getValue();
                T value = (T) map.get(key);

                if (!schema.isValid(value)) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }
}
