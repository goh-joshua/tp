package seedu.address.commons.core;

import java.awt.Point;
import java.io.Serializable;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * A Serializable class that contains the GUI settings.
 * Guarantees: immutable.
 */
public class GuiSettings implements Serializable {

    private static final double DEFAULT_HEIGHT = 600;
    private static final double DEFAULT_WIDTH = 740;

    private final double windowWidth;
    private final double windowHeight;
    private final Point windowCoordinates;

    /**
     * Constructs a {@code GuiSettings} with the default height, width and position.
     */
    public GuiSettings() {
        windowWidth = DEFAULT_WIDTH;
        windowHeight = DEFAULT_HEIGHT;
        windowCoordinates = null; // null represent no coordinates
    }

    /**
     * Constructs a {@code GuiSettings} with the specified height, width and position.
     */
    public GuiSettings(double windowWidth, double windowHeight, int xPosition, int yPosition) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        windowCoordinates = new Point(xPosition, yPosition);
    }

    /**
     * Returns the width of the window.
     *
     * @return The window width.
     */
    public double getWindowWidth() {
        return windowWidth;
    }

    /**
     * Returns the height of the window.
     *
     * @return The window height.
     */
    public double getWindowHeight() {
        return windowHeight;
    }

    /**
     * Returns a copy of the window coordinates.
     * Returns null if no coordinates are set.
     *
     * @return A copy of the window coordinates, or null if not set.
     */
    public Point getWindowCoordinates() {
        return windowCoordinates != null ? new Point(windowCoordinates) : null;
    }

    /**
     * Returns true if both GuiSettings objects have the same settings.
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
        if (!(other instanceof GuiSettings)) {
            return false;
        }

        GuiSettings otherGuiSettings = (GuiSettings) other;
        return windowWidth == otherGuiSettings.windowWidth
                && windowHeight == otherGuiSettings.windowHeight
                && Objects.equals(windowCoordinates, otherGuiSettings.windowCoordinates);
    }

    /**
     * Returns the hash code for this GuiSettings object.
     *
     * @return The hash code based on windowWidth, windowHeight and windowCoordinates.
     */
    @Override
    public int hashCode() {
        return Objects.hash(windowWidth, windowHeight, windowCoordinates);
    }

    /**
     * Returns a string representation of this GuiSettings object.
     *
     * @return A formatted string containing the GUI settings.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("windowWidth", windowWidth)
                .add("windowHeight", windowHeight)
                .add("windowCoordinates", windowCoordinates)
                .toString();
    }
}
