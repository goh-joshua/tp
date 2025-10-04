package seedu.address.model;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AthleteUserPrefsTest {
    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        AthleteUserPrefs athleteUserPref = new AthleteUserPrefs();
        assertThrows(NullPointerException.class, () -> athleteUserPref.setGuiSettings(null));
    }

    @Test
    public void setAFilePath_nullPath_throwsNullPointerException() {
        AthleteUserPrefs athleteUserPref = new AthleteUserPrefs();
        assertThrows(NullPointerException.class, () -> athleteUserPref.setFilePath(null));
    }
}
