package seedu.sudohr.model.events;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.sudohr.model.person.Person;

public class Event {

    private final Title title;
    private final Set<Person> attendees;

    // need update to Title type
    public Event(Title title) {
        this.title = title;
        this.attendees = new HashSet<Person>();
    }

    public Title getTitle() {
        return title;
    }

    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }
        return otherEvent != null
                && otherEvent.getTitle().equals(getTitle());
    }

    public Set<Person> getAttendees() {
        return attendees;
    }

    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return attendees.contains(person);
    }

    public void addPerson(Person person) {
        requireNonNull(person);
        this.attendees.add(person);
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
