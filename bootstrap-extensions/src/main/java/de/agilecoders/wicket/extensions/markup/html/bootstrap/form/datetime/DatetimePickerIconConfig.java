package de.agilecoders.wicket.extensions.markup.html.bootstrap.form.datetime;

import de.agilecoders.wicket.core.markup.html.bootstrap.image.GlyphIconType;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
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

    private static final IKey<String> Up = newKey("up", GlyphIconType.arrowup.cssClassName());
    private static final IKey<String> Down = newKey("down", GlyphIconType.arrowdown.cssClassName());
    private static final IKey<String> Date = newKey("date", GlyphIconType.calendar.cssClassName());
    private static final IKey<String> Time = newKey("time", GlyphIconType.time.cssClassName());

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

}
