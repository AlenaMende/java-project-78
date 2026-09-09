package hexlet.code.schemas;

import java.util.Map;
import java.util.HashMap;

public class MapSchema extends BaseSchema<Map<String, ?>> {
    private Integer sizeof = null;
    private Map<String, BaseSchema<?>> shape = null;

    @Override
    public MapSchema required() {
        super.required();
        return this;
    }

    public MapSchema sizeof(Integer number) {
        this.sizeof = number;
        return this;
    }

    public MapSchema shape(Map<String, ? extends BaseSchema<?>> schemas) {
        this.shape = new HashMap<>(schemas);
        return this;
    }

    @Override
    public boolean isValid(Map<String, ?> map) {
        if (map == null) {
            if (isRequired() || sizeof != null) {
                return false;
            }
            return true;
        }

        if (sizeof != null && map.size() != sizeof) {
            return false;
        }

        if (shape != null) {
            for (Map.Entry<String, BaseSchema<?>> entry : shape.entrySet()) {
                String key = entry.getKey();
                BaseSchema<?> schema = entry.getValue();
                Object value = map.get(key);

                if (!isValidSchema(schema, value)) {
                    return false;
                }
            }
        }
        return true;
    }

    private <T> boolean isValidSchema(BaseSchema<T> schema, Object value) {
        try {
            return schema.isValid((T) value);
        } catch (ClassCastException e) {
            return false;
        }
    }
}
