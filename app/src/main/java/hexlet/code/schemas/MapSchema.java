package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<String, ?>> {

    @Override
    public MapSchema required() {
        this.required = true;
        return this;
    }

    public MapSchema sizeof(Integer number) {
        addCheck("sizeof", map -> map.size() == number);
        return this;
    }

    public MapSchema shape(Map<String, ? extends BaseSchema<?>> schemas) {
        addCheck("shape", map -> {
            for (Map.Entry<String, ? extends BaseSchema<?>> entry : schemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<?> schema = entry.getValue();
                Object value = map.get(key);

                if (!isValidSchema(schema, value)) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }

    private <T> boolean isValidSchema(BaseSchema<T> schema, Object value) {
        try {
            return schema.isValid((T) value);
        } catch (ClassCastException e) {
            return false;
        }
    }
}
