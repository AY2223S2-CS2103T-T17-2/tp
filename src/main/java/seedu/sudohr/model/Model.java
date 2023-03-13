package seedu.sudohr.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.sudohr.commons.core.GuiSettings;
import seedu.sudohr.model.department.Department;
import seedu.sudohr.model.department.DepartmentName;
import seedu.sudohr.model.events.Event;
import seedu.sudohr.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true for employee */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true for events */
    Predicate<Event> PREDICATE_SHOW_ALL_EVENT = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' sudohr book file path.
     */
    Path getSudoHrFilePath();

    /**
     * Sets the user prefs' sudohr book file path.
     */
    void setSudoHrFilePath(Path sudoHrFilePath);

    /**
     * Replaces sudohr book data with the data in {@code sudoHr}.
     */
    void setSudoHr(ReadOnlySudoHr sudoHr);

    /** Returns the SudoHr */
    ReadOnlySudoHr getSudoHr();

    //=========== Person-Level Operations ==============================================================================

    /**
     * Returns true if a person with the same identity as {@code person} exists in
     * the sudohr book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the sudohr book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the sudohr book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the sudohr book.
     * The person identity of {@code editedPerson} must not be the same as another
     * existing person in the sudohr book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given
     * {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    //=========== Department-Level Operations ==========================================================================

    public Department getDepartment(DepartmentName name);

    /**
     * Returns true if a department with the same identity as {@code department} exists in the address
     * book.
     */
    public boolean hasDepartment(Department department);

    /**
     * Adds a department to the address book.
     * The department must not already exist in the address book.
     */
    void addDepartment(Department d);

    /**
     * Replaces the given department {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in
     * the address book.
     */
    void setDepartment(Department target, Department editedDepartment);

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    void removeDepartment(Department key);

    /**
     * Adds a given employee from a given department
     *
     * @param p The employee to add
     * @param d The department to add the employee to
     */
    void addEmployeeToDepartment(Person p, Department d);

    /**
     * Removes a given employee from a given department
     *
     * @param p The employee to remove
     * @param d The department to remove the employee fro
     */
    void removeEmployeeFromDepartment(Person p, Department d);

    /** Returns an unmodifiable view of the filtered department list */
    ObservableList<Department> getFilteredDepartmentList();

    /**
     * Updates the filter of the filtered department list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredDepartmentList(Predicate<Department> predicate);

    //=========== Event-Level Operations ==========================================================================

    /**
     * Adds the given event.
     * {@code event} must not already exist in the sudohr book.
     */
    void addEvent(Event event);

    /**
     * Deletes the given event.
     * The event must exist in the sudohr book.
     */
    void deleteEvent(Event eventToDelete);

    /**
     * Returns true if a event with the same identity as {@code event} exists in
     * the sudohr book.
     */
    boolean hasEvent(Event event);

    /**
     * Replaces the given department {@code eventToEdit} in the list with {@code editedEvent}.
     * {@code eventToEdit} must exist in the address book.
     * The person identity of {@code editedEvent} must not be the same as another existing person in
     * the address book.
     */
    void setEvent(Event eventToEdit, Event editedEvent);

    /**
     * Returns true if a given event{@code event} has the employee {@code employee} in
     * the sudohr book.
     */
    boolean hasEmployeeInEvent(Event event, Person person);

    /**
     * Adds a employee {@code employee} to a given event{@code event} in
     * the sudohr book.
     */
    void addEmployeeToEvent(Event eventToAdd, Person personToAdd);

    /** Returns an unmodifiable view of the filtered event list */
    ObservableList<Event> getFilteredEventList();

    /** Returns an unmodifiable view of the full event list */
    ObservableList<Event> getEventList();

    /**
     * Deletes a employee {@code employee} from a given event{@code event} in
     * the sudohr book.
     */
    void deleteEmployeeFromEvent(Event eventToDelete, Person personToDelete);

    /**
     * Updates the filter of the filtered event list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEventList(Predicate<Event> predicateShowAllEvent);

    /**
     * Update a person {@code person} with editedPerson {@code person} in all events
     * in the sudohr book.
     */
    void cascadeUpdateUserInEvents(Person personToEdit, Person editedPerson);

    /**
     * Deletes a person {@code person} from all events
     * in the sudohr book.
     */
    void cascadeDeleteUserInEvents(Person personToDelete);
}
