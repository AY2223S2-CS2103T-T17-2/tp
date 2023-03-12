package seedu.sudohr.model.events;

import static java.util.Objects.requireNonNull;

public class Title {

    public final String value;

    public static final String MESSAGE_CONSTRAINTS = "Titles should only contain alphanumeric characters and spaces, and it should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    /**
     * Constructs a {@code Title}.
     *
     * @param title A event title.
     */
    public Title(String title) {
        requireNonNull(title);
        value = title;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Title // instanceof handles nulls
                && value.equals(((Title) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
    
}
