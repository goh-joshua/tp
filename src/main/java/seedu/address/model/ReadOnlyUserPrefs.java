package seedu.address.model;

import java.nio.file.Path;

import seedu.address.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    /**
     * Returns the GUI settings.
     *
     * @return the current GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Returns the file path of the AddressBook.
     *
     * @return the path to the AddressBook file.
     */
    Path getAddressBookFilePath();

}
