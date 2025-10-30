package seedu.address.commons.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents a version with major, minor and patch number
 */
public class Version implements Comparable<Version> {

    public static final String VERSION_REGEX = "V(\\d+)\\.(\\d+)\\.(\\d+)(ea)?";

    private static final String EXCEPTION_STRING_NOT_VERSION = "String is not a valid Version. %s";

    private static final Pattern VERSION_PATTERN = Pattern.compile(VERSION_REGEX);

    private final int major;
    private final int minor;
    private final int patch;
    private final boolean isEarlyAccess;

    /**
     * Constructs a {@code Version} with the given version details.
     */
    public Version(int major, int minor, int patch, boolean isEarlyAccess) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
        this.isEarlyAccess = isEarlyAccess;
    }

    /**
     * Returns the major version number.
     *
     * @return The major version number.
     */
    public int getMajor() {
        return major;
    }

    /**
     * Returns the minor version number.
     *
     * @return The minor version number.
     */
    public int getMinor() {
        return minor;
    }

    /**
     * Returns the patch version number.
     *
     * @return The patch version number.
     */
    public int getPatch() {
        return patch;
    }

    /**
     * Returns whether this is an early access version.
     *
     * @return True if this is an early access version, false otherwise.
     */
    public boolean isEarlyAccess() {
        return isEarlyAccess;
    }

    /**
     * Parses a version number string in the format V1.2.3.
     * @param versionString version number string
     * @return a Version object
     */
    @JsonCreator
    public static Version fromString(String versionString) throws IllegalArgumentException {
        Matcher versionMatcher = VERSION_PATTERN.matcher(versionString);

        if (!versionMatcher.find()) {
            throw new IllegalArgumentException(String.format(EXCEPTION_STRING_NOT_VERSION, versionString));
        }

        return new Version(Integer.parseInt(versionMatcher.group(1)),
                Integer.parseInt(versionMatcher.group(2)),
                Integer.parseInt(versionMatcher.group(3)),
                versionMatcher.group(4) == null ? false : true);
    }

    /**
     * Returns the string representation of this version in the format V[major].[minor].[patch][ea].
     *
     * @return The version string.
     */
    @JsonValue
    public String toString() {
        return String.format("V%d.%d.%d%s", major, minor, patch, isEarlyAccess ? "ea" : "");
    }

    /**
     * Compares this version with another version for ordering.
     * Versions are compared by major, then minor, then patch number.
     * Early access versions are considered less than non-early access versions with the same numbers.
     *
     * @param other The version to compare with.
     * @return A negative integer, zero, or a positive integer as this version is less than,
     *         equal to, or greater than the specified version.
     */
    @Override
    public int compareTo(Version other) {
        if (major != other.major) {
            return major - other.major;
        }
        if (minor != other.minor) {
            return minor - other.minor;
        }
        if (patch != other.patch) {
            return patch - other.patch;
        }
        if (isEarlyAccess == other.isEarlyAccess()) {
            return 0;
        }
        if (isEarlyAccess) {
            return -1;
        }
        return 1;
    }

    /**
     * Returns true if both Version objects have the same version numbers and early access status.
     *
     * @param other The other object to compare with.
     * @return True if both objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Version)) {
            return false;
        }

        Version otherVersion = (Version) other;
        return major == otherVersion.major
                && minor == otherVersion.minor
                && patch == otherVersion.patch
                && isEarlyAccess == otherVersion.isEarlyAccess;
    }

    /**
     * Returns the hash code for this Version object.
     * The hash code is calculated based on the major, minor, and patch numbers,
     * with early access versions having a different hash than non-early access versions.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        String hash = String.format("%03d%03d%03d", major, minor, patch);
        if (!isEarlyAccess) {
            hash = "1" + hash;
        }
        return Integer.parseInt(hash);
    }
}
