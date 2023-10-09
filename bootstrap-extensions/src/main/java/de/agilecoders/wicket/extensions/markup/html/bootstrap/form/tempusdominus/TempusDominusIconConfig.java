package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.tempusdominus;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeSettings;
import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;
import org.apache.wicket.Application;

/**
 * datetime picker icon config
 */
public class TempusDominusIconConfig extends AbstractConfig {
    private static final long serialVersionUID = 1L;

    public enum TypeType {
        ICONS("icons"),
        SPRITES("sprites");

        private final String type;

        TypeType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    private static final IKey<String> Type = newKey("time", null);
    private static final IKey<String> Time = newKey("time", null);
    private static final IKey<String> Date = newKey("date", null);
    private static final IKey<String> Up = newKey("up", null);
    private static final IKey<String> Down = newKey("down", null);
    private static final IKey<String> Next = newKey("next", null);
    private static final IKey<String> Previous = newKey("previous", null);
    private static final IKey<String> Today = newKey("today", null);
    private static final IKey<String> Clear = newKey("clear", null);
    private static final IKey<String> Close = newKey("close", null);

    public TempusDominusIconConfig() {
        FontAwesomeSettings fas = FontAwesomeSettings.get(Application.get());
        put(Up, fas.getIconType(FontAwesomeSettings.IconKey.ARROW_UP).cssClassName());
        put(Down, fas.getIconType(FontAwesomeSettings.IconKey.ARROW_DOWN).cssClassName());
        put(Date, fas.getIconType(FontAwesomeSettings.IconKey.CALENDAR).cssClassName());
        put(Time, fas.getIconType(FontAwesomeSettings.IconKey.CLOCK).cssClassName());
        put(Previous, fas.getIconType(FontAwesomeSettings.IconKey.ARROW_LEFT).cssClassName());
        put(Next, fas.getIconType(FontAwesomeSettings.IconKey.ARROW_RIGHT).cssClassName());
        put(Today, fas.getIconType(FontAwesomeSettings.IconKey.TODAY).cssClassName());
        put(Clear, fas.getIconType(FontAwesomeSettings.IconKey.CLEAR).cssClassName());
        put(Close, fas.getIconType(FontAwesomeSettings.IconKey.CLOSE).cssClassName());
    }

    /**
     * @param type Defaults to "icons". If "sprites" is used as the value, the icons will be render with an svg
     *      element instead of an "i" element.
     * @return current instance
     */
    public TempusDominusIconConfig withType(TypeType type) {
        put(Type, type.getType());
        return this;
    }

    /**
     * @param time This icon is used to change the view from the calendar view to the clock view.
     * @return current instance
     */
    public TempusDominusIconConfig withTimeIcon(IconType time) {
        put(Time, time.cssClassName());
        return this;
    }

    /**
     * @param date This icon is used to change the view from the clock view to the calendar view.
     * @return current instance
     */
    public TempusDominusIconConfig withDateIcon(IconType date) {
        put(Date, date.cssClassName());
        return this;
    }

    /**
     * @param up This icon is used to increment hours, minutes and seconds in the clock view.
     * @return current instance
     */
    public TempusDominusIconConfig withUpIcon(IconType up) {
        put(Up, up.cssClassName());
        return this;
    }

    /**
     * @param down This icon is used to decrement hours, minutes and seconds in the clock view.
     * @return current instance
     */
    public TempusDominusIconConfig withDownIcon(IconType down) {
        put(Down, down.cssClassName());
        return this;
    }

    /**
     * @param next This icon is used to navigation forward in the calendar, month, year, and decade views.
     * @return current instance
     */
    public TempusDominusIconConfig withNextIcon(IconType next) {
        put(Next, next.cssClassName());
        return this;
    }

    /**
     * @param previous This icon is used to navigation backwards in the calendar, month, year, and decade views.
     * @return current instance
     */
    public TempusDominusIconConfig withPreviousIcon(IconType previous) {
        put(Previous, previous.cssClassName());
        return this;
    }

    /**
     * @param today This icon is used to change the date and view to now.
     * @return current instance
     */
    public TempusDominusIconConfig withTodayIcon(IconType today) {
        put(Today, today.cssClassName());
        return this;
    }

    /**
     * @param clear This icon is used to clear the currently selected date.
     * @return current instance
     */
    public TempusDominusIconConfig withClearIcon(IconType clear) {
        put(Clear, clear.cssClassName());
        return this;
    }

    /**
     * @param close This icon is used to close the picker.
     * @return current instance
     */
    public TempusDominusIconConfig withCloseIcon(IconType close) {
        put(Close, close.cssClassName());
        return this;
    }
}
