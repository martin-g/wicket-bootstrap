package de.agilecoders.wicket.extensions.markup.html.bootstrap.references;

import de.agilecoders.wicket.webjars.request.resource.WebjarsJavaScriptResourceReference;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;

/**
 * Eonasdan datetime-picker css reference
 *
 * @author Alexey Volkov
 * @since 01.02.15
 */
public class DatetimePickerCssReference extends WebjarsJavaScriptResourceReference {

    private static final long serialVersionUID = -183841786312223075L;

    /**
     * Singleton instance of this reference
     */
    private static final class Holder {

        private static final DatetimePickerCssReference INSTANCE = new DatetimePickerCssReference();
    }

    /**
     * @return the single instance of the resource reference
     */
    public static DatetimePickerCssReference instance() {
        return Holder.INSTANCE;
    }

    /**
     * Private constructor.
     */
    private DatetimePickerCssReference() {
        super("Eonasdan-bootstrap-datetimepicker/current/bootstrap-datetimepicker.min.css");
    }

    /**
     * @return this resource reference singleton instance as header item
     */
    public static HeaderItem asHeaderItem() {
        return CssHeaderItem.forReference(instance());
    }
}


