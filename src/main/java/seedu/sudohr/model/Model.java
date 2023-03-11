package seedu.sudohr.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.sudohr.commons.core.GuiSettings;
import seedu.sudohr.model.events.Event;
import seedu.sudohr.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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

    // event methods

    void addEvent(Event event);

    boolean hasEvent(Event event);

    ObservableList<Event> getEventList();

    boolean hasEmployeeInEvent(Event eventToAdd, Person personToAdd);

    void addEmployeeToEvent(Event eventToAdd, Person personToAdd);

    ObservableList<Event> getFilteredEventList();

    void deleteEvent(Event eventToDelete);

}
