package seedu.sudohr.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.sudohr.commons.util.AppUtil.checkArgument;

/**
 * Represents an employee's assigned identity number in the company.
 * Guarantees: immutable; is valid as declared in {@link #isValidId(String)}
 */
public class Id {
    public static final String MESSAGE_CONSTRAINTS = "IDs can take any (integral) values, and it should not be blank. "
            + "Note that ID is a field for an employee's unique identification in the company";

    /*
     * Must be at least one digit
     */
    public static final String VALIDATION_REGEX = "\\d{1,}";

    private final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param id A valid employee identification number.
     */
    public Id(String id) {
        requireNonNull(id);
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        value = id;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidId(String id) {
        return id.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Id // instanceof handles nulls
                && value.equals(((Id) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
