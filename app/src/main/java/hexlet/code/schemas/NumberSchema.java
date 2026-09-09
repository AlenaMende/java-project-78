package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {
    private boolean positive = false;
    private Integer min = null;
    private Integer max = null;

    @Override
    public NumberSchema required() {
        super.required();
        return this;
    }

    public NumberSchema positive() {
        this.positive = true;
        return this;
    }

    public NumberSchema range(int num1, int num2) {
        this.min = num1;
        this.max = num2;
        return this;
    }

    @Override
    public boolean isValid(Integer value) {
        if (value == null) {
            return !isRequired();
        }

        if (positive && value <= 0) {
            return false;
        }

        if (min != null && max != null) {
            if (value < min || value > max) {
                return false;
            }
        }

        return true;
    }
}
