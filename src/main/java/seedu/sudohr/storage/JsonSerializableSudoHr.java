package seedu.sudohr.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.sudohr.commons.exceptions.IllegalValueException;
import seedu.sudohr.model.ReadOnlySudoHr;
import seedu.sudohr.model.SudoHr;
import seedu.sudohr.model.department.Department;
import seedu.sudohr.model.events.Event;
import seedu.sudohr.model.person.Person;

/**
 * An Immutable SudoHr that is serializable to JSON format.
 */
@JsonRootName(value = "sudohr")
class JsonSerializableSudoHr {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_DEPARTMENTS = "Departments list contains duplicate department(s).";
    public static final String MESSAGE_DUPLICATE_EVENTS = "Events list contains duplicate events(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedDepartment> departments = new ArrayList<>();
    private final List<JsonAdaptedEvent> events = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableSudoHr} with the given persons.
     */
    @JsonCreator
    public JsonSerializableSudoHr(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                  @JsonProperty("departments") List<JsonAdaptedDepartment> departments,
                                  @JsonProperty("events") List<JsonAdaptedEvent> events) {
        this.persons.addAll(persons);
        this.departments.addAll(departments);
        this.events.addAll(events);
    }

    /**
     * Converts a given {@code ReadOnlySudoHr} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableSudoHr}.
     */
    public JsonSerializableSudoHr(ReadOnlySudoHr source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        departments.addAll(source.getDepartmentList().stream().map(JsonAdaptedDepartment::new)
                .collect(Collectors.toList()));
        events.addAll(source.getEventsList().stream().map(JsonAdaptedEvent::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this sudohr book into the model's {@code SudoHr} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public SudoHr toModelType() throws IllegalValueException {
        SudoHr sudoHr = new SudoHr();

        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (sudoHr.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            sudoHr.addPerson(person);
        }

        for (JsonAdaptedDepartment jsonAdaptedDepartment : departments) {
            Department department = jsonAdaptedDepartment.toModelType();
            if (sudoHr.hasDepartment(department)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_DEPARTMENTS);
            }
            sudoHr.addDepartment(department);
        }

        for (JsonAdaptedEvent jsonAdaptedEvent : events) {
            Event event = jsonAdaptedEvent.toModelType();
            if (sudoHr.hasEvent(event)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EVENTS);
            }
            sudoHr.addEvent(event);
        }

        return sudoHr;
    }

}
