package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

    @Override
    public StringSchema required() {
        this.required = true;
        addCheck("notEmpty", value -> !value.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        addCheck("minLength", value -> value.isEmpty() || value.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        addCheck("contains", value -> value.isEmpty() || value.contains(substring));
        return this;
    }
}
