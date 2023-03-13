package seedu.sudohr.model.events;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.sudohr.model.person.Person;


/**
 * Represents a Event in the sudohr book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Event {

    private final Title title;
    private final Set<Person> attendees;

    /**
     * Every field must be present and not null.
     */
    public Event(Title title) {
        this.title = title;
        this.attendees = new HashSet<Person>();
    }

    /**
     * Creates a new event with the specified title {@code title} and attendees{@code attendees}
     */
    public Event(Title title, Set<Person> attendees) {
        this.title = title;
        this.attendees = attendees;
    }

    public Title getTitle() {
        return title;
    }

    public Set<Person> getAttendees() {
        return attendees;
    }

    /**
     * Returns true if event has a specific person {@code person} as an attendee.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return attendees.contains(person);
    }

    /**
     * Adds a specific person {@code person} to the event.
     */
    public void addPerson(Person person) {
        requireNonNull(person);
        this.attendees.add(person);
    }

    /**
     * Deletes a specific person {@code person} from the event.
     */
    public void deletePerson(Person person) {
        requireNonNull(person);
        this.attendees.remove(person);
    }

    /**
     * Returns true if both event have the same title.
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }
        return otherEvent != null
                && otherEvent.getTitle().equals(getTitle());
    }
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(";Title: ")
                .append(getTitle())
                .append("\n");
        return builder.toString();
    }
}
