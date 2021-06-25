package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome5IconType;
import de.agilecoders.wicket.jquery.AbstractConfig;
import de.agilecoders.wicket.jquery.IKey;

/**
 * datetime picker icon config
 *
 * @author Alexey Volkov
 * @since 07.02.15
 */
public class DatetimePickerIconConfig extends AbstractConfig {

    private static final long serialVersionUID = 1L;

    private static final IKey<String> Up = newKey("up", FontAwesome5IconType.arrow_up_s.cssClassName());
    private static final IKey<String> Down = newKey("down", FontAwesome5IconType.arrow_down_s.cssClassName());
    private static final IKey<String> Date = newKey("date", FontAwesome5IconType.calendar_alt_r.cssClassName());
    private static final IKey<String> Time = newKey("time", FontAwesome5IconType.clock_r.cssClassName());
    private static final IKey<String> Previous = newKey("previous", FontAwesome5IconType.arrow_left_s.cssClassName());
    private static final IKey<String> Next = newKey("next", FontAwesome5IconType.arrow_right_s.cssClassName());
    private static final IKey<String> Today = newKey("today", FontAwesome5IconType.calendar_check_r.cssClassName());
    private static final IKey<String> Clear = newKey("clear", FontAwesome5IconType.eraser_s.cssClassName());
    private static final IKey<String> Close = newKey("close", FontAwesome5IconType.times_s.cssClassName());

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
