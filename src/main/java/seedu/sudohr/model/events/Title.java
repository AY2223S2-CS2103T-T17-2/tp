package seedu.sudohr.model.events;

import static java.util.Objects.requireNonNull;

public class Title {

    public final String value;

    /**
     * Constructs a {@code Title}.
     *
     * @param title A event title.
     */
    public Title(String title) {
        requireNonNull(title);
        value = title;
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
