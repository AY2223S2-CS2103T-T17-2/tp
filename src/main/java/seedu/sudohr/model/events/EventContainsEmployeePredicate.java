package seedu.sudohr.model.events;

import java.util.Set;
import java.util.function.Predicate;

import seedu.sudohr.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class EventContainsEmployeePredicate implements Predicate<Person> {
    private final Set<Person> personsInEvent;

    public EventContainsEmployeePredicate(Set<Person> personsInEvent) {
        this.personsInEvent = personsInEvent;
    }

    @Override
    public boolean test(Person person) {
        return personsInEvent.stream()
                .anyMatch(personInList -> personInList.equals(person));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventContainsEmployeePredicate // instanceof handles nulls
                        && personsInEvent.equals(((EventContainsEmployeePredicate) other).personsInEvent)); // state check
    }
}
