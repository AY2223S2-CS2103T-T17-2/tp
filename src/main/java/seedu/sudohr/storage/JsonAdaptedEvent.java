package seedu.sudohr.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.sudohr.commons.exceptions.IllegalValueException;
import seedu.sudohr.model.events.Event;
import seedu.sudohr.model.events.Title;
import seedu.sudohr.model.person.Person;

/**
 * Jackson-friendly version of {@link Event}.
 */
public class JsonAdaptedEvent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Title's %s field is missing!";

    private final String title;
    private final List<JsonAdaptedPerson> employees = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedDepartment} with the given department details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("title") String title,
            @JsonProperty("employees") List<JsonAdaptedPerson> employees) {
        this.title = title;
        if (employees != null) {
            this.employees.addAll(employees);
        }
    }

    /**
     * Converts a given {@code Department} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        title = source.getTitle().value;
        this.employees.addAll(source.getAttendees().stream()
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted department object into the model's
     * {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *         the adapted person.
     */
    public Event toModelType() throws IllegalValueException {
        final List<Person> eventEmployees = new ArrayList<>();
        for (JsonAdaptedPerson employee : employees) {
            eventEmployees.add(employee.toModelType());
        }

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Title.class.getSimpleName()));
        }
        if (!Title.isValidName(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }

        final Title eventTitle = new Title(title);

        final Set<Person> modelEmployees = new HashSet<>(eventEmployees);

        return new Event(eventTitle, modelEmployees);
    }
}
