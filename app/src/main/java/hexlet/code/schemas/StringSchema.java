package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

    @Override
    public StringSchema required() {
        this.required = true;
        return this;
    }

    @Override
    protected boolean isEmpty(String value) {
        return value.isEmpty();
    }

    public StringSchema minLength(int length) {
        addCheck("minLength", value -> value.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        addCheck("contains", value -> value.contains(substring));
        return this;
    }
}
