package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesomeSettings;
import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;
import org.apache.wicket.Application;

/**
 * datetime picker icon config
 *
 * @author Alexey Volkov
 * @since 07.02.15
 */
public class DatetimePickerIconConfig extends AbstractConfig {

    private static final long serialVersionUID = 1L;

    private static final IKey<String> Up = newKey("up", null);
    private static final IKey<String> Down = newKey("down", null);
    private static final IKey<String> Date = newKey("date", null);
    private static final IKey<String> Time = newKey("time", null);
    private static final IKey<String> Previous = newKey("previous", null);
    private static final IKey<String> Next = newKey("next", null);
    private static final IKey<String> Today = newKey("today", null);
    private static final IKey<String> Clear = newKey("clear", null);
    private static final IKey<String> Close = newKey("close", null);

    public DatetimePickerIconConfig() {
        FontAwesomeSettings fas = FontAwesomeSettings.get(Application.get());
        put(Up, fas.getIconType(FontAwesomeSettings.IconKey.ARROW_UP).cssClassName());
        put(Down, fas.getIconType(FontAwesomeSettings.IconKey.ARROW_DOWN).cssClassName());
        put(Date, fas.getIconType(FontAwesomeSettings.IconKey.CALENDAR).cssClassName());
        put(Time, fas.getIconType(FontAwesomeSettings.IconKey.CLOCK_R).cssClassName());
        put(Previous, fas.getIconType(FontAwesomeSettings.IconKey.ARROW_LEFT).cssClassName());
        put(Next, fas.getIconType(FontAwesomeSettings.IconKey.ARROW_RIGHT).cssClassName());
        put(Today, fas.getIconType(FontAwesomeSettings.IconKey.TODAY).cssClassName());
        put(Clear, fas.getIconType(FontAwesomeSettings.IconKey.CLEAR).cssClassName());
        put(Close, fas.getIconType(FontAwesomeSettings.IconKey.CLOSE).cssClassName());
    }
    /**
     * @param up up icon type
     * @return current instance
     */
    public DatetimePickerIconConfig useUpIcon(IconType up) {
        put(Up, up.cssClassName());
        return this;
    }

    /**
     * @param down down icon type
     * @return current instance
     */
    public DatetimePickerIconConfig useDownIcon(IconType down) {
        put(Down, down.cssClassName());
        return this;
    }

    /**
     * @param date date icon type
     * @return current instance
     */
    public DatetimePickerIconConfig useDateIcon(IconType date) {
        put(Date, date.cssClassName());
        return this;
    }

    /**
     * @param time time icon type
     * @return current instance
     */
    public DatetimePickerIconConfig useTimeIcon(IconType time) {
        put(Time, time.cssClassName());
        return this;
    }

    /**
     * @param previous previous icon type
     * @return current instance
     */
    public DatetimePickerIconConfig usePreviousIcon(IconType previous) {
        put(Previous, previous.cssClassName());
        return this;
    }

    /**
     * @param next next icon type
     * @return current instance
     */
    public DatetimePickerIconConfig useNextIcon(IconType next) {
        put(Next, next.cssClassName());
        return this;
    }

    /**
     * @param today today icon type
     * @return current instance
     */
    public DatetimePickerIconConfig useTodayIcon(IconType today) {
        put(Today, today.cssClassName());
        return this;
    }

    /**
     * @param clear clear icon type
     * @return current instance
     */
    public DatetimePickerIconConfig useClearIcon(IconType clear) {
        put(Clear, clear.cssClassName());
        return this;
    }

    /**
     * @param close close icon type
     * @return current instance
     */
    public DatetimePickerIconConfig useCloseIcon(IconType close) {
        put(Close, close.cssClassName());
        return this;
    }
}
