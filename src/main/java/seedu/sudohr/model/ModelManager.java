package seedu.sudohr.model;

import static java.util.Objects.requireNonNull;
import static seedu.sudohr.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.sudohr.commons.core.GuiSettings;
import seedu.sudohr.commons.core.LogsCenter;
import seedu.sudohr.model.events.Event;
import seedu.sudohr.model.person.Person;

/**
 * Represents the in-memory model of the sudohr book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final SudoHr sudoHr;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Event> filteredEvents;

    /**
     * Initializes a ModelManager with the given sudoHr and userPrefs.
     */
    public ModelManager(ReadOnlySudoHr sudoHr, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(sudoHr, userPrefs);

        logger.fine("Initializing with sudohr book: " + sudoHr + " and user prefs " + userPrefs);

        this.sudoHr = new SudoHr(sudoHr);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.sudoHr.getPersonList());
        filteredEvents = new FilteredList<>(this.sudoHr.getEventsList());
    }

    public ModelManager() {
        this(new SudoHr(), new UserPrefs());
    }

    // =========== UserPrefs
    // ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getSudoHrFilePath() {
        return userPrefs.getSudoHrFilePath();
    }

    @Override
    public void setSudoHrFilePath(Path sudoHrFilePath) {
        requireNonNull(sudoHrFilePath);
        userPrefs.setSudoHrFilePath(sudoHrFilePath);
    }

    // =========== SudoHr
    // ================================================================================

    @Override
    public void setSudoHr(ReadOnlySudoHr sudoHr) {
        this.sudoHr.resetData(sudoHr);
    }

    @Override
    public ReadOnlySudoHr getSudoHr() {
        return sudoHr;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return sudoHr.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        sudoHr.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        sudoHr.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        sudoHr.setPerson(target, editedPerson);
    }

    // =========== Event Commands
    // =============================================================

    @Override
    public void addEvent(Event event) {
        requireNonNull(event);
        sudoHr.addEvent(event);

    }

    @Override
    public void deleteEvent(Event event) {
        requireNonNull(event);
        sudoHr.deleteEvent(event);
    }

    @Override
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return sudoHr.hasEvent(event);
    }

    @Override
    public ObservableList<Event> getEventList() {
        return filteredEvents;
    }

    @Override
    public boolean hasEmployeeInEvent(Event event, Person person) {
        requireAllNonNull(event, person);
        return sudoHr.hasEmployeeInEvent(event, person);
    }

    @Override
    public void addEmployeeToEvent(Event eventToAdd, Person personToAdd) {
        requireAllNonNull(eventToAdd, personToAdd);

        sudoHr.addEmployeeToEvent(eventToAdd, personToAdd);
    }

    @Override
    public void deleteEmployeeFromEvent(Event eventToDelete, Person personToDelete) {
        requireAllNonNull(eventToDelete, personToDelete);

        sudoHr.deleteEmployeeFromEvent(eventToDelete, personToDelete);
    }

    @Override
    public void setEvent(Event eventToEdit, Event editedEvent) {
        // TODO Auto-generated method stub
        requireAllNonNull(eventToEdit, editedEvent);
        sudoHr.setEvent(eventToEdit, editedEvent);
    }

    // =========== Filtered Event List Accessors
    // =============================================================

    @Override
    public ObservableList<Event> getFilteredEventList() {
        return filteredEvents;
    }

    @Override
    public void updateFilteredEventList(Predicate<Event> predicate) {
        requireNonNull(predicate);
        filteredEvents.setPredicate(predicate);
    }

    // =========== Filtered Person List Accessors
    // =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the
     * internal list of
     * {@code versionedSudoHr}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return sudoHr.equals(other.sudoHr)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

    
}
